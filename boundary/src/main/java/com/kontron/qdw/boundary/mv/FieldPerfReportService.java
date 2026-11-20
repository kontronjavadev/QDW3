package com.kontron.qdw.boundary.mv;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.SerialNumberSetItem;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.dto.mv.*;
import com.kontron.qdw.dto.mv.report.FieldPerfReportCauseResultRecord;
import com.kontron.qdw.dto.mv.report.FieldPerfReportSearchObject;
import com.kontron.qdw.dto.service.RepairErrorCodeListDTO;
import com.kontron.qdw.repository.material.*;
import com.kontron.util.datetime.TimeMeasurement;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.search.SearchService;

@Stateless
public class FieldPerfReportService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private enum DateEnumeration {
        REPAIR_START, REPAIR_END, SHIPMENT_START, SHIPMENT_END
    }

    @PersistenceContext
    protected EntityManager em;
    @EJB
    private MaterialNumberSetRepository materialNumberSetRepo;
    @EJB
    private MaterialRepository materialRepo;
    @EJB
    private SerialNumberSetRepository serNrSetRepo;



    /* ***************** *
     * Report Hauptseite *
     * ***************** */

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public HashMap<String, Long> getShipmentsPerMonth(FieldPerfReportSearchObject input) {
        logger.info("start getShipmentsPerMonth()");
        TimeMeasurement tm = new TimeMeasurement("getShipmentsPerMonth()");

        LocalDate startDate = input.getStartDate();
        LocalDate endDate = input.getStartDate().withDayOfMonth(1);

        int startYear = startDate.getYear();
        int startMonth = startDate.getMonthValue();
        int endYear = endDate.getYear();
        int endMonth = endDate.getMonthValue();

        // initialize result map
        // shipmentMap je Monat mit 0 initialisieren
        HashMap<String, Long> shipmentMap = new HashMap<String, Long>();
        for (int l = 0; l < input.getSelectedAnalInterval(); l++) {
            endYear = endDate.getYear();
            endMonth = endDate.getMonthValue();
            shipmentMap.put(String.format("%d-%d", endYear, endMonth), 0L);

            endDate = endDate.plusMonths(1);
        }

        // Abfrage zu Anzahl shipments je Monat/Jahr; je nachdem, welche Tabelle verwendet werden kann
        if (input.getSelectedDistType() == DistributionType.ALL || input.getSelectedDistType() == DistributionType.CUSTOMER) {
            if (!useShipmentQueryOnAggregatedLevel(input)) {
                TimeMeasurement tm2 = tm.createSubMeasurement("select MaterializedArrivalShipment");

                StringBuilder queryStatement = new StringBuilder();
                queryStatement.append("select count(a), extract(year from a.shipmentDate), extract(month from a.shipmentDate) ");
                queryStatement.append("from MaterializedArrivalShipment a ");

                queryStatement.append("where a.shipmentDate >= :startDate and a.shipmentDate < :endDate ");

                // Add standard filter criteria
                setupFilterCriteria(queryStatement, input, true, false);
                queryStatement.append("group by extract(year from a.shipmentDate), extract(month from a.shipmentDate) ");

                TypedQuery<Object[]> query = em.createQuery(queryStatement.toString(), Object[].class);
                query.setParameter("startDate", startDate);
                query.setParameter("endDate", endDate);
                List<Object[]> result = query.getResultList();

                for (Iterator<Object[]> iter = result.iterator(); iter.hasNext();) {
                    Object[] values = iter.next();
                    long shipments = ((Number) values[0]).longValue();
                    int shipmentYear = ((Number) values[1]).intValue();
                    int shipmentMonth = ((Number) values[2]).intValue();

                    shipmentMap.put(String.format("%d-%d", shipmentYear, shipmentMonth), shipments);
                }
                tm2.stopMeasurement();
            }
            else {
                TimeMeasurement tm2 = tm.createSubMeasurement("select AggregatedShipmentArrival");
                /*************** NEW AGGREGATED IMPLEMENTATION **************************/

                StringBuilder queryStatement = new StringBuilder();
                queryStatement.append("select sum(a.shipments), concat(a.year, '-', a.month) shipmentperiod ");
                queryStatement.append("from AggregatedShipmentArrival a ");
                queryStatement.append("where a.year * 12 + a.month >= ").append(startYear * 12 + startMonth).append(" ");
                queryStatement.append("and   a.year * 12 + a.month <= ").append(endYear * 12 + endMonth).append(" ");

                // Add standard filter criteria
                setupFilterCriteria(queryStatement, input, true, true);

                queryStatement.append("group by a.year, a.month ");

                TypedQuery<Object[]> query = em.createQuery(queryStatement.toString(), Object[].class);

                List<Object[]> result = query.getResultList();
                for (Iterator<Object[]> iter = result.iterator(); iter.hasNext();) {
                    Object[] values = iter.next();
                    Long shipments = ((Number) values[0]).longValue();
                    String shipmentPeriod = (String) values[1];

                    shipmentMap.put(shipmentPeriod, shipments);
                }
                /*************** NEW AGGREGATED IMPLEMENTATION **************************/
                tm2.stopMeasurement();
            }
        } // end if (DistributionType.ALL || DistributionType.CUSTOMER)


        // Abfrage zu Anzahl shipments je Monat/Jahr
        if (input.getSelectedDistType() == DistributionType.ALL || input.getSelectedDistType() == DistributionType.INTERNAL) {
            TimeMeasurement tm3 = tm.createSubMeasurement("select MaterializedAssemblyShipment");
            StringBuilder queryStatement = new StringBuilder();
            queryStatement.append("select count(a), extract(year from a.shipmentDate), extract(month from a.shipmentDate) ");
            queryStatement.append("from MaterializedAssemblyShipment a ");

            queryStatement.append("where a.shipmentDate >= :startDate and a.shipmentDate < :endDate ");

            // Add standard filter criteria
            setupFilterCriteria(queryStatement, input, true, false);
            queryStatement.append("group by extract(year from a.shipmentDate), extract(month from a.shipmentDate) ");

            TypedQuery<Object[]> query = em.createQuery(queryStatement.toString(), Object[].class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<Object[]> result = query.getResultList();

            for (Iterator<Object[]> iter = result.iterator(); iter.hasNext();) {
                Object[] values = iter.next();
                long shipments = ((Number) values[0]).longValue();
                int shipmentYear = ((Number) values[1]).intValue();
                int shipmentMonth = ((Number) values[2]).intValue();

                String shipmentPeriod = String.format("%d-%d", shipmentYear, shipmentMonth);
                shipmentMap.put(shipmentPeriod, shipments + shipmentMap.get(shipmentPeriod));
            }
            tm3.stopMeasurement();
        } // end if (DistributionType.ALL || DistributionType.INTERNAL)

        tm.stopMeasurement();
        logger.info("end getShipmentsPerMonth()\n" + tm);
        return shipmentMap;
    }


    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public HashMap<String, Long> getLastYearReturn(FieldPerfReportSearchObject input) {
        logger.info("start getLastYearReturn()");
        TimeMeasurement tm = new TimeMeasurement("getLastYearReturn()");

        String rmaDate;
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE
                || input.getSelectedRmaCntType() == RMACountingType.ALL_BY_ARRIVAL_DATE) {
            rmaDate = "a.internalArrivalDate";
        }
        else {
            rmaDate = "a.internalShipmentDate";
        }

        HashMap<String, Long> returnValuesMap = new HashMap<String, Long>();
        // returnValuesMap je Monat mit 0 initialisieren
        for (int p = 0; p < input.getSelectedAnalInterval() + input.getSelectedReturnInterval() - 1; p++) {
            LocalDate period = input.getStartDate().plusMonths(p);
            int periodYear = period.getYear();
            int periodMonth = period.getMonthValue();
            returnValuesMap.put(String.format("%d-%d", periodYear, periodMonth), 0L);
        }



        LocalDate firstRepairPeriodStart = input.getStartDate();

        LocalDate lastRepairPeriodEnd = input.getStartDate().plusMonths(input.getSelectedAnalInterval() + input.getSelectedReturnInterval() - 1);

        LocalDate firstCustomerShipDateStart = input.getStartDate().minusMonths(input.getSelectedQualityPeriod() - 1);

        LocalDate lastCustomerShipDateEnd = input.getStartDate().plusMonths(input.getSelectedAnalInterval() + input.getSelectedReturnInterval() - 1);


        /*
        Beispiel:
        // select repairperiod, sum(cnt) cnt
        // from (
            select count(*) as cnt,
                concat(year(a.internal_arrival_date), '-', month(a.internal_arrival_date)) repairperiod
            from service_message_mv a
            where a.internal_arrival_date >= '2013-01-01'
            and a.internal_arrival_date < '2018-12-01'
            and a.cust_ship_date >= '2012-02-01'
            and a.cust_ship_date < '2018-12-01'
            and a.internal_shipment_date is not null
            and year(a.cust_ship_date) * 12 + month(a.cust_ship_date) >
                year(a.internal_arrival_date) * 12 + month(a.internal_arrival_date) - 12
            and year(a.cust_ship_date) * 12 + month(a.cust_ship_date) <=
                year(a.internal_arrival_date) * 12 + month(a.internal_arrival_date)
            group by year(a.internal_arrival_date), month(a.internal_arrival_date), year(a.cust_ship_date), month(a.cust_ship_date)
        // ) matrix
        // group by repairperiod;
         */

        TimeMeasurement tm1 = tm.createSubMeasurement("select MaterializedServiceMessage");
        StringBuilder queryStatement = new StringBuilder();
        String repairPeriod = String.format("concat(extract(year from %s), '-', extract(month from %s)) ", rmaDate, rmaDate);

        queryStatement.append("select count(a) as cnt, ");
        queryStatement.append(repairPeriod).append("as repairperiod ");
        queryStatement.append("from MaterializedServiceMessage a ");

        queryStatement.append(String.format("where %s >= :firstRepairPeriodStart ", rmaDate));
        queryStatement.append(String.format("and %s < :lastRepairPeriodEnd ", rmaDate));
        queryStatement.append("and a.customerShipmentDate >= :firstCustomerShipDateStart ");
        queryStatement.append("and a.customerShipmentDate < :lastCustomerShipDateEnd ");

        // customerShipmentDate (nach Jahr und Monat) zwischen betrachteten Zeitraum des rmaDate, also <selectedQRelevantPeriod> Monate in der Vergangenheit
        queryStatement.append("and extract(year from a.customerShipmentDate) * 12 + extract(month from a.customerShipmentDate) > ");
        queryStatement.append(String.format("extract(year from %s) * 12 + extract(month from %s) - :selectedQRelevantPeriod ", rmaDate, rmaDate));

        queryStatement.append("and extract(year from a.customerShipmentDate) * 12 + extract(month from a.customerShipmentDate) <= ");
        queryStatement.append(String.format("extract(year from %s) * 12 + extract(month from %s) ", rmaDate, rmaDate));

        // Add standard filter criteria
        setupFilterCriteria(queryStatement, input, false, false);

        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE) {
            queryStatement.append("and a.internalShipmentDate is not null ");
        }

        queryStatement.append("group by ").append(repairPeriod);

        TypedQuery<Object[]> query = em.createQuery(queryStatement.toString(), Object[].class)
                .setParameter("firstRepairPeriodStart", firstRepairPeriodStart)
                .setParameter("lastRepairPeriodEnd", lastRepairPeriodEnd)
                .setParameter("firstCustomerShipDateStart", firstCustomerShipDateStart)
                .setParameter("lastCustomerShipDateEnd", lastCustomerShipDateEnd)
                .setParameter("selectedQRelevantPeriod", input.getSelectedQualityPeriod());



        List<Object[]> result = query.getResultList();
        for (Iterator<Object[]> i = result.iterator(); i.hasNext();) {
            Object[] values = i.next();
            long cnt = ((Number) values[0]).longValue();
            String iterRepairPeriod = (String) values[1];

            returnValuesMap.put(iterRepairPeriod, cnt);
        }

        tm1.stopMeasurement();
        tm.stopMeasurement();
        logger.info("end getLastYearReturn()\n" + tm);
        return returnValuesMap;
    }


    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public HashMap<String, Long> getFirstYearReturn(FieldPerfReportSearchObject input) {
        logger.info("start getFirstYearReturn()");
        TimeMeasurement tm = new TimeMeasurement("getFirstYearReturn()");

        String rmaDate;
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE
                || input.getSelectedRmaCntType() == RMACountingType.ALL_BY_ARRIVAL_DATE) {
            rmaDate = "a.internalArrivalDate";
        }
        else {
            rmaDate = "a.internalShipmentDate";
        }

        HashMap<String, Long> returnValuesMap = new HashMap<String, Long>();
        // returnValuesMap je Monat mit 0 initialisieren
        for (int p = 0; p < input.getSelectedAnalInterval(); p++) {
            LocalDate period = input.getStartDate().plusMonths(p);
            int periodYear = period.getYear();
            int periodMonth = period.getMonthValue();
            returnValuesMap.put(String.format("%d-%d", periodYear, periodMonth), 0L);
        }



        LocalDate firstCustomerShipDateStart = input.getStartDate();

        LocalDate lastCustomerShipDateEnd = input.getStartDate().plusMonths(input.getSelectedAnalInterval());

        LocalDate firstRepairPeriodStart = input.getStartDate();

        LocalDate lastRepairPeriodEnd = input.getStartDate().plusMonths(input.getSelectedQualityPeriod() + input.getSelectedAnalInterval() - 1);


        /*
        Beispiel:
        // select shipperiod, sum(cnt) cnt
        // from (
            select count(*) as cnt,
                concat(year(a.cust_ship_date), '-', month(a.cust_ship_date)) repairperiod
            from service_message_mv a
            where a.internal_arrival_date >= '2013-01-01'
            and a.internal_arrival_date < '2014-04-01'
            and a.cust_ship_date >= '2013-01-01'
            and a.cust_ship_date < '2013-05-01'
            and a.internal_shipment_date is not null
            and year(a.internal_arrival_date) * 12 + month(a.internal_arrival_date) >=
                year(a.cust_ship_date) * 12 + month(a.cust_ship_date)
            and year(a.internal_arrival_date) * 12 + month(a.internal_arrival_date) <=
                year(a.cust_ship_date) * 12 + month(a.cust_ship_date) + 12
            group by year(a.internal_arrival_date), month(a.internal_arrival_date), year(a.cust_ship_date), month(a.cust_ship_date)
        // ) matrix
        // group by shipperiod;
         */

        TimeMeasurement tm1 = tm.createSubMeasurement("select MaterializedServiceMessage");
        StringBuilder queryStatement = new StringBuilder();
        String shipPeriod = "concat(extract(year from a.customerShipmentDate), '-', extract(month from a.customerShipmentDate)) ";

        queryStatement.append("select count(a) as cnt, ");
        queryStatement.append(shipPeriod + "as shipperiod ");
        queryStatement.append("from MaterializedServiceMessage a ");

        queryStatement.append(String.format("where %s >= :firstRepairPeriodStart ", rmaDate));
        queryStatement.append(String.format("and %s < :lastRepairPeriodEnd ", rmaDate));
        queryStatement.append("and a.customerShipmentDate >= :firstCustomerShipDateStart ");
        queryStatement.append("and a.customerShipmentDate < :lastCustomerShipDateEnd ");

        queryStatement.append(String.format("and extract(year from %s) * 12 + extract(month from %s) >= ", rmaDate, rmaDate));
        queryStatement.append("extract(year from a.customerShipmentDate) * 12 + extract(month from a.customerShipmentDate) ");

        queryStatement.append(String.format("and extract(year from %s) * 12 + extract(month from %s) < ", rmaDate, rmaDate));
        queryStatement
                .append("extract(year from a.customerShipmentDate) * 12 + extract(month from a.customerShipmentDate) + :selectedQRelevantPeriod ");

        // Add standard filter criteria
        setupFilterCriteria(queryStatement, input, false, false);
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE) {
            queryStatement.append("and a.internalShipmentDate is not null ");
        }
        queryStatement.append("group by ").append(shipPeriod);


        TypedQuery<Object[]> query = em.createQuery(queryStatement.toString(), Object[].class)
                .setParameter("firstRepairPeriodStart", firstRepairPeriodStart)
                .setParameter("lastRepairPeriodEnd", lastRepairPeriodEnd)
                .setParameter("firstCustomerShipDateStart", firstCustomerShipDateStart)
                .setParameter("lastCustomerShipDateEnd", lastCustomerShipDateEnd)
                .setParameter("selectedQRelevantPeriod", input.getSelectedQualityPeriod());


        List<Object[]> result = query.getResultList();
        for (Iterator<Object[]> i = result.iterator(); i.hasNext();) {
            Object[] values = i.next();
            long cnt = ((Number) values[0]).longValue();
            String iterShipPeriod = (String) values[1];

            returnValuesMap.put(iterShipPeriod, returnValuesMap.get(iterShipPeriod) + cnt);
        }

        tm1.stopMeasurement();
        tm.stopMeasurement();
        logger.info("end getFirstYearReturn()\n" + tm);
        return returnValuesMap;
    }


    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public HashMap<String, HashMap<String, Long>> getReturnValuesOfAnalysisInterval(FieldPerfReportSearchObject input) {
        logger.info("start getReturnValuesOfAnalysisInterval()");
        TimeMeasurement tm = new TimeMeasurement("getReturnValuesOfAnalysisInterval()");

        String rmaDate;
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE
                || input.getSelectedRmaCntType() == RMACountingType.ALL_BY_ARRIVAL_DATE) {
            rmaDate = "a.internalArrivalDate";
        }
        else {
            rmaDate = "a.internalShipmentDate";
        }

        HashMap<String, HashMap<String, Long>> returnMap = new HashMap<String, HashMap<String, Long>>();
        // returnValuesMap je Analyse-Monat mit Map Return-Monaten mit Anzahl 0 initialisieren
        for (int ap = 0; ap < input.getSelectedAnalInterval(); ap++) {
            LocalDate analysisPeriod = input.getStartDate().plusMonths(ap);
            int analysisPeriodYear = analysisPeriod.getYear();
            int analysisPeriodMonth = analysisPeriod.getMonthValue();

            HashMap<String, Long> returnValuesMap = new HashMap<String, Long>();
            returnMap.put(String.format("%d-%d", analysisPeriodYear, analysisPeriodMonth), returnValuesMap);

            for (int rp = 0; rp < input.getSelectedReturnInterval(); rp++) {
                LocalDate returnPeriod = analysisPeriod.plusMonths(rp);
                int returnPeriodYear = returnPeriod.getYear();
                int returnPeriodMonth = returnPeriod.getMonthValue();
                returnValuesMap.put(String.format("%d-%d", returnPeriodYear, returnPeriodMonth), 0L);
            }
        }

        LocalDate firstRepairPeriodStart = input.getStartDate();

        LocalDate lastRepairPeriodEnd = input.getStartDate().plusMonths(input.getSelectedReturnInterval() + input.getSelectedAnalInterval() - 1);

        LocalDate firstShipDateStart = input.getStartDate();

        LocalDate lastShipDateEnd = input.getStartDate().plusMonths(input.getSelectedAnalInterval());



        TimeMeasurement tm1 = tm.createSubMeasurement("select MaterializedArrivalShipment");
        StringBuilder queryStatement = new StringBuilder();
        String shipPeriod = "concat(extract(year from a.customerShipmentDate), '-', extract(month from a.customerShipmentDate)) ";
        String repairPeriod = String.format("concat(extract(year from %s), '-', extract(month from %s)) ", rmaDate, rmaDate);

        queryStatement.append("select count(a) as cnt, ");
        queryStatement.append(shipPeriod + "as shipperiod, ");
        queryStatement.append(repairPeriod).append("as repairperiod ");
        queryStatement.append("from MaterializedServiceMessage a ");

        queryStatement.append(String.format("where %s >= :firstRepairPeriodStart ", rmaDate));
        queryStatement.append(String.format("and %s < :lastRepairPeriodEnd ", rmaDate));
        queryStatement.append("and a.customerShipmentDate >= :firstShipDateStart  ");
        queryStatement.append("and a.customerShipmentDate < :lastShipDateEnd ");

        queryStatement.append(String.format("and extract(year from %s) * 12 + extract(month from %s) >= ", rmaDate, rmaDate));
        queryStatement.append("extract(year from a.customerShipmentDate) * 12 + extract(month from a.customerShipmentDate) ");

        queryStatement.append(String.format("and extract(year from %s) * 12 + extract(month from %s) < ", rmaDate, rmaDate));
        queryStatement.append("extract(year from a.customerShipmentDate) * 12 + extract(month from a.customerShipmentDate) + :returnInterval ");

        // Add standard filter criteria
        setupFilterCriteria(queryStatement, input, false, false);
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE) {
            queryStatement.append(" and a.internalShipmentDate is not null ");
        }

        queryStatement.append("group by ").append(shipPeriod).append(", ").append(repairPeriod);


        TypedQuery<Object[]> query = em.createQuery(queryStatement.toString(), Object[].class)
                .setParameter("firstRepairPeriodStart", firstRepairPeriodStart)
                .setParameter("lastRepairPeriodEnd", lastRepairPeriodEnd)
                .setParameter("firstShipDateStart", firstShipDateStart)
                .setParameter("lastShipDateEnd", lastShipDateEnd)
                .setParameter("returnInterval", input.getSelectedReturnInterval());

        List<Object[]> result = query.getResultList();
        for (Iterator<Object[]> i = result.iterator(); i.hasNext();) {
            Object[] values = i.next();
            long cnt = ((Number) values[0]).longValue();
            String iterShipPeriod = (String) values[1];
            String iterRepairPeriod = (String) values[2];

            HashMap<String, Long> returnValuesMap = returnMap.get(iterShipPeriod);
            returnValuesMap.put(iterRepairPeriod, returnValuesMap.get(iterRepairPeriod) + cnt);
        }


        tm1.stopMeasurement();
        tm.stopMeasurement();
        logger.info("end getReturnValuesOfAnalysisInterval()\n" + tm);
        return returnMap;
    }



    /* ****************** *
     * Report Detailseite *
     * ****************** */

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedServiceMessageSearchDTO> getAllDetailData(FieldPerfReportSearchObject input) {
        LocalDate startShipDate = input.getStartDate().minusMonths(input.getSelectedQualityPeriod() - 1);
        LocalDate endShipDate = input.getStartDate().plusMonths(1);

        LocalDate startRepairDate = input.getStartDate();
        LocalDate endRepairDate = input.getStartDate().plusMonths(1);


        List<MaterializedServiceMessageSearchDTO> repairs = new ArrayList<>();

        // Iterate over all analysis periods
        for (int k = 0; k < (input.getSelectedAnalInterval() + input.getSelectedReturnInterval() - 1); k++) {
            List<MaterializedServiceMessageSearchDTO> stepDetail = performDetailStep(input,
                    startShipDate, endShipDate, startRepairDate, endRepairDate);
            repairs.addAll(stepDetail);

            startShipDate = startShipDate.plusMonths(1);
            endShipDate = endShipDate.plusMonths(1);

            startRepairDate = startRepairDate.plusMonths(1);
            endRepairDate = endRepairDate.plusMonths(1);
        }

        return repairs;
    }

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedServiceMessageSearchDTO> getFieldPerformanceReportDetailData(FieldPerfReportSearchObject input) {
        LocalDate startShipDate = calculateDate(input, DateEnumeration.SHIPMENT_START);
        LocalDate endShipDate = calculateDate(input, DateEnumeration.SHIPMENT_END);

        LocalDate startRepairDate = calculateDate(input, DateEnumeration.REPAIR_START);
        LocalDate endRepairDate = calculateDate(input, DateEnumeration.REPAIR_END);

        if (input.getReturnDate() == null && input.getShipDate() == null) {
            List<MaterializedServiceMessageSearchDTO> repairs = new ArrayList<MaterializedServiceMessageSearchDTO>();

            for (int k = 0; k < input.getSelectedAnalInterval(); k++) {
                List<MaterializedServiceMessageSearchDTO> stepDetail = performDetailStep(input,
                        startShipDate, endShipDate, startRepairDate, endRepairDate);
                repairs.addAll(stepDetail);

                startShipDate = startShipDate.plusMonths(1);
                endShipDate = endShipDate.plusMonths(1);

                startRepairDate = startRepairDate.plusMonths(1);
                endRepairDate = endRepairDate.plusMonths(1);
            }

            return repairs;
        }

        return performDetailStep(input, startShipDate, endShipDate, startRepairDate, endRepairDate);
    }



    /* ******************* *
     * Report Causes-Seite *
     * ******************* */

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<FieldPerfReportCauseResultRecord> getAllErrorCodes(FieldPerfReportSearchObject input) {
        LocalDate startShipDate = input.getStartDate().minusMonths(input.getSelectedQualityPeriod() - 1);
        LocalDate endShipDate = input.getStartDate().plusMonths(1);

        LocalDate startRepairDate = input.getStartDate();
        LocalDate endRepairDate = input.getStartDate().plusMonths(1);


        Map<String, Integer> returnValuesMap = new HashMap<>();

        for (int k = 0; k < (input.getSelectedAnalInterval() + input.getSelectedReturnInterval() - 1); k++) {
            Map<String, Integer> tempMap = performErrorCodeStep(input, startShipDate, endShipDate, startRepairDate, endRepairDate);
            tempMap.forEach((key, value) -> returnValuesMap.merge(key, value, Integer::sum));

            startShipDate = startShipDate.plusMonths(1);
            endShipDate = endShipDate.plusMonths(1);

            startRepairDate = startRepairDate.plusMonths(1);
            endRepairDate = endRepairDate.plusMonths(1);
        }

        return convertToRecordList(returnValuesMap);
    }

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<FieldPerfReportCauseResultRecord> getErrorCodeReport(FieldPerfReportSearchObject input) {
        LocalDate startShipDate = calculateDate(input, DateEnumeration.SHIPMENT_START);
        LocalDate endShipDate = calculateDate(input, DateEnumeration.SHIPMENT_END);

        LocalDate startRepairDate = calculateDate(input, DateEnumeration.REPAIR_START);
        LocalDate endRepairDate = calculateDate(input, DateEnumeration.REPAIR_END);

        if (input.getReturnDate() == null && input.getShipDate() == null) {
            Map<String, Integer> returnValuesMap = new HashMap<>();

            for (int k = 0; k < input.getSelectedAnalInterval(); k++) {
                Map<String, Integer> tempMap = performErrorCodeStep(input, startShipDate, endShipDate, startRepairDate, endRepairDate);
                tempMap.forEach((key, value) -> returnValuesMap.merge(key, value, Integer::sum));

                startShipDate = startShipDate.plusMonths(1);
                endShipDate = endShipDate.plusMonths(1);

                startRepairDate = startRepairDate.plusMonths(1);
                endRepairDate = endRepairDate.plusMonths(1);
            }

            return convertToRecordList(returnValuesMap);
        }

        return convertToRecordList(performErrorCodeStep(input, startShipDate, endShipDate, startRepairDate, endRepairDate));
    }



    /* ************* *
     * Hilfsmethoden *
     * ************* */

    /**
     * Add standard filter criteria for all queries this bean provides
     * Aufrufe zu Tabellen von getShipmentsPerMonth():
     * - MaterializedArrivalShipment (arrival_shipment_mv)
     * - AggregatedShipmentArrival (aggregated_shipment_arrival_tab)
     * - MaterializedAssemblyShipment (assembly_shipment_mv)
     * von getLastYearReturn(), getFirstYearReturn():
     * - MaterializedServiceMessage (service_message_mv)
     * 
     * @param queryStatement
     * @param input
     * @param queryOnShipmentOnly a flag that indicates if further filter criteria should be added
     */
    private void setupFilterCriteria(StringBuilder queryStatement, FieldPerfReportSearchObject input,
            boolean queryOnShipmentOnly, boolean queryOnAggregatedLevel) {
        // Suche über MaterializedArrivalShipment
        String parentSerialObjectIdAttribute = "a.parentSerialObjectId";
        String materialAttribute = "a.material.id";
        String parentMaterialAttribute = "a.parentMaterialNumber";
        String revisionAttribute = "a.revisionNumber";
        String customerAttribute = "a.customerCode";
        String supplierAttribute = "a.supplierCode";
        String plantAttribute = "a.plant";
        String serialNumberAttribute = "a.serialObject.serialNumber";
        String materialTypeAttribute = "a.material.materialType.code";
        String materialClassAttribute = "a.material.materialType.code";

        if (queryOnAggregatedLevel) {
            // Suche über AggregatedShipmentArrival
            materialAttribute = "a.materialRevision.material.id";
            revisionAttribute = "a.materialRevision.revisionNumber";
            customerAttribute = "a.customer.code";
            supplierAttribute = "a.supplier.code";
            plantAttribute = "a.plant.code";
            materialTypeAttribute = "a.materialRevision.material.materialType.code";
            materialClassAttribute = "a.materialRevision.material.materialType.code";
        }



        if (input.getSelectedDistType() == DistributionType.CUSTOMER) {
            queryStatement.append("and (").append(parentSerialObjectIdAttribute).append(" is null ")
                    .append("or ").append(parentSerialObjectIdAttribute).append(" <= 0) ");
        }
        else if (input.getSelectedDistType() == DistributionType.INTERNAL) {
            queryStatement.append("and (").append(parentSerialObjectIdAttribute).append(" is not null ")
                    .append("and ").append(parentSerialObjectIdAttribute).append(" > 0) ");
        }


        // either material numbers or material number set are allowed - both does not make any sense
        if (!input.getMatNrs().isEmpty() && input.getSelectedMatNrSet() != null) {
            throw new RuntimeException("You must not specify both material numbers and a set of material numbers!");
        }

        // eines der beiden ist gesetzt
        if (!input.getMatNrs().isEmpty() || input.getSelectedMatNrSet() != null) {
            List<Material> materials;
            if (input.getSelectedMatNrSet() != null) {
                // es wurde ein Set an Materialnummern verwendet
                materials = materialNumberSetRepo.getMaterials(input.getSelectedMatNrSet().getId());
            }
            else {
                // es wurde direkt eine Menge an Materialnummern angegeben
                materials = materialRepo.findByMaterialNumbersPlain(input.getMatNrs(), false);
            }
            Set<Long> materialIds = materials.stream()
                    .map(Material::getId)
                    .collect(Collectors.toSet());

            // make SQL-String
            queryStatement.append("and ").append(materialAttribute).append(" ");
            if (materialIds.isEmpty()) {
                // Die angegebene Materialnummer wurde nicht gefunden, daher die Suche invalidieren,
                // um entsprechend kein Ergebnis zu erhalten!
                queryStatement.append("= -1 ");
            }
            else {
                queryStatement.append("in (").append(StringUtil.collectionToSqlWhereInString(materialIds, false)).append(") ");
            }

            // add revision filter if mentionend
            if (StringUtils.isNotEmpty(input.getRevNo())) {
                queryStatement.append(parseCriterion(revisionAttribute, input.getRevNo()));
            }
        }


        if (!input.getpMatNrs().isEmpty()) {
            queryStatement.append("and ").append(parentMaterialAttribute).append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getpMatNrs(), true)).append(") ");
        }

        if (!input.getCustomers().isEmpty()) {
            queryStatement.append("and ").append(customerAttribute).append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getCustomers(), CustomerDTO::getCode, true))
                    .append(") ");
        }

        if (!input.getSupplier().isEmpty()) {
            queryStatement.append(" and ").append(supplierAttribute).append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getSupplier(), SupplierDTO::getCode, true))
                    .append(") ");
        }

        if (!input.getSelectedPlants().isEmpty()) {
            queryStatement.append(" and ").append(plantAttribute).append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getSelectedPlants(), PlantSearchDTO::getCode, true))
                    .append(") ");
        }

        if (StringUtils.isNotEmpty(input.getMatHier())) {
            queryStatement.append(parseCriterion("a.materialHierarchy", input.getMatHier()));
        }


        if (!queryOnShipmentOnly) {
            queryStatement.append(parseCriterion("a.errorId", input.getErrorId()));
            queryStatement.append(parseCriterion("a.defectComponent", input.getDefCmpt()));

            if (!input.getSelectedRmaTypes().isEmpty()) {
                queryStatement.append("and ").append("a.rmaType").append(" in (")
                        .append(StringUtil.collectionToSqlWhereInString(input.getSelectedRmaTypes(), true))
                        .append(") ");
            }

            if (!input.getSelectedErrGrps().isEmpty()) {
                queryStatement.append("and ").append("a.errorCodeGroup").append(" in (")
                        .append(StringUtil.collectionToSqlWhereInString(input.getSelectedErrGrps(), true))
                        .append(") ");
            }

            if (!input.getSelectedOrigins().isEmpty()) {
                queryStatement.append("and ").append("a.origin").append(" in (")
                        .append(StringUtil.collectionToSqlWhereInString(input.getSelectedOrigins(), true))
                        .append(") ");
            }

            if (!input.getEpidemic()) {
                queryStatement.append(" and a.epidemicFailure = false ");
            }

            // im Oiginal mit Bedingung, jedoch ist der Wert stets false, die Bedingung also stets erfüllt
            // if (!input.isCMReport()) {
            // queryStatement.append(parseCriterion("a.errorCodeName", input.getErrorCode()));
            // }
            // else {
            // queryStatement
            // .append("and a.errorCodeName in ('ZWW01-110 - Solder issue','ZWW01-105 - Defective component / hardware','ZWW02-201 - Assembly error') ");
            // }
            if (!input.getSelectedErr().isEmpty()) {
                queryStatement.append(" and ").append("a.errorCodeName").append(" in (")
                        .append(StringUtil.collectionToSqlWhereInString(input.getSelectedErr(), RepairErrorCodeListDTO::getName, true))
                        .append(") ");
            }
        }

        if (input.getSelectedSerNrSet() != null) {
            List<SerialNumberSetItem> serNrSetItems = serNrSetRepo.getSerialNumbers(input.getSelectedSerNrSet().getCode());
            List<String> serialNumbers = serNrSetItems.stream()
                    .map(SerialNumberSetItem::getSerialNumber)
                    .collect(Collectors.toList());
            queryStatement.append("and ").append(StringUtil.collectionToSqlLikeString(serialNumberAttribute, serialNumbers)).append(" ");
        }

        if (!input.getSelectedMatTypes().isEmpty()) {
            queryStatement.append("and ").append(materialTypeAttribute).append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getSelectedMatTypes(), true))
                    .append(") ");
        }


        if (!input.getSelectedMatClasses().isEmpty()) {
            queryStatement.append("and ").append(materialClassAttribute).append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getSelectedMatClasses(), true))
                    .append(") ");
        }
    }


    /**
     * @param name Name des jpql-Attributs, etwa "a.id"
     * @param filter Suchkriterium
     * @return jpql-Schnipsel für diesen Teil der Filterung
     */
    private String parseCriterion(String name, String filter) {
        if (StringUtils.isEmpty(filter)) {
            return "";
        }

        boolean not = false;

        if (Strings.CI.startsWith(filter, "not(")) {
            not = true;
            filter = filter.substring(4);

            if (filter.endsWith(")")) {
                filter = filter.substring(0, filter.length() - 1);
            }
        }

        String[] items = filter.split(SearchService.TOKEN_DELIMITER_IN);
        StringBuilder queryFragment = new StringBuilder();

        queryFragment.append("and (");
        boolean isFirstItem = true;

        for (String item : items) {
            if (isFirstItem) {
                isFirstItem = false;
            }
            else {
                if (not) {
                    queryFragment.append("and ");
                }
                else {
                    queryFragment.append("or ");
                }
            }

            // MySql sucht standardmäßig caseinsensitiv, ansonsten kann man das konfigurieren!
            // queryFragment.append("UPPER(").append(name).append(") ");
            queryFragment.append(name).append(" ");

            if (not) {
                queryFragment.append("not ");
            }

            // MySql sucht standardmäßig caseinsensitiv, ansonsten kann man das konfigurieren!
            // queryFragment.append("like '").append(item.toUpperCase()).append("'");
            queryFragment.append("like '").append(item).append("' ");
        }

        queryFragment.append(") ");

        return queryFragment.toString();
    }


    /**
     * @return <code>true</code> wenn die Abfrage auf die aggregierte materialized view angewandt werden kann, sonst <code>false</code>
     */
    private boolean useShipmentQueryOnAggregatedLevel(FieldPerfReportSearchObject input) {
        // Die Abfrage kann nicht auf die bereits aggregierte Tabelle angewandt werden,
        // sondern es wird die materialized view rangezogen, wenn...
        // ... nicht DistributionTpe "ALL" zur Filterung angegeben ist
        if (input.getSelectedDistType() != DistributionType.ALL) {
            return false;
        }

        // ... Parent Material zur Filterung angegeben ist
        if (!input.getpMatNrs().isEmpty()) {
            return false;
        }

        // ... Material hierarchy zur Filterung angegeben ist
        if (StringUtils.isNotEmpty(input.getMatHier())) {
            return false;
        }

        // ... ein Satz Seriennummern zur Filterung angegeben ist
        if (input.getSelectedSerNrSet() != null) {
            return false;
        }

        // Andernfalls kann die Abfrage auf die aggregierte materialized view angewandt werden.
        return true;
    }



    private List<MaterializedServiceMessageSearchDTO> performDetailStep(FieldPerfReportSearchObject input,
            LocalDate startShipDate, LocalDate endShipDate, LocalDate startRepairDate, LocalDate endRepairDate) {
        String rmaDate;
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE
                || input.getSelectedRmaCntType() == RMACountingType.ALL_BY_ARRIVAL_DATE) {
            rmaDate = "a.internalArrivalDate";
        }
        else {
            rmaDate = "a.internalShipmentDate";
        }

        StringBuilder queryStatement = new StringBuilder("select distinct ").append(getMaterializedServiceMessageSearchDTOConstructor())
                .append(" from MaterializedServiceMessage a ");

        queryStatement.append("where ").append(rmaDate).append(" >= :startRepairDate and ").append(rmaDate).append(" < :endRepairDate ");
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE) {
            queryStatement.append("and a.internalShipmentDate is not null ");
        }

        queryStatement.append("and a.customerShipmentDate >= :startShipDate and a.customerShipmentDate < :endShipDate ");

        // Add standard filter criteria
        setupFilterCriteria(queryStatement, input, false, false);

        TypedQuery<MaterializedServiceMessageSearchDTO> query = em.createQuery(queryStatement.toString(), MaterializedServiceMessageSearchDTO.class);
        query.setParameter("startShipDate", startShipDate);
        query.setParameter("endShipDate", endShipDate);
        query.setParameter("startRepairDate", startRepairDate);
        query.setParameter("endRepairDate", endRepairDate);

        return query.getResultList();
    }

    private String getMaterializedServiceMessageSearchDTOConstructor() {
        StringBuilder statementBuilder = new StringBuilder();

        statementBuilder.append("new MaterializedServiceMessageSearchDTO");
        statementBuilder.append("(").append(MaterializedServiceMessageSearchDTO.SELECT_SERVICEORDER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SERVICEORDERTYPE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_RMATYPE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SERVICENAME);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_TASKNAME);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_STATENAME);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_INTERNALARRIVALDATE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_INTERNALSHIPMENTDATE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_BASICSTARTDATE);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_BASICENDDATE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_DESIGNATOR);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_DEFECTCOMPONENT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ANALYSISTEXT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_INTERNALREPORT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_EXTERNALREPORT);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERREPORT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_EPIDEMICFAILURE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ERRORID);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ORIGIN);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERFAILURE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERCODE);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERNAME);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERGROUP);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_COUNTRYCODE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_COUNTRYNAME);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERCODE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERNAME);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERARRIVALDATE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERSHIPMENTDATE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_FAULTANALYSISCODE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ERRORCODENAME);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_EXTERNALSUPPLIERCODE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_EXTERNALSUPPLIERNAME);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_DELIVERYNOTENUMBER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ERRORCODEGROUP);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SYMPTOMSHORTTEXT);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_REPAIRTASKSHORTTEXT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_REPAIRDESCRIPTION);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_OWNERLOCATION);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ERRORSHORTTEXT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ID);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_MESERIALOBJECTID);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTSERIALOBJECTID);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SERIALNUMBER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTSERIALNUMBER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_MATERIALNUMBER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALNUMBER);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_MATERIALTYPE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALTYPE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_MATERIALSHORTTEXT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALSHORTTEXT);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SAPNUMBER);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTSAPNUMBER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_MATERIALHIERARCHY);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALHIERARCHY);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_REVISIONID);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTREVISIONID);

        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_REVISIONNUMBER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PARENTREVISIONNUMBER);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ASSEMBLYDATE);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_ASSEMBLYPO);
        statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_PLANT);

        statementBuilder.append(",null,0,0");
        // statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_MATERIALMATERIALNUMBER);
        // statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_SERIALOBJECTID);
        // statementBuilder.append(",").append(MaterializedServiceMessageSearchDTO.SELECT_MATERIALID);
        statementBuilder.append(")");

        return statementBuilder.toString();
    }



    private Map<String, Integer> performErrorCodeStep(FieldPerfReportSearchObject input,
            LocalDate startShipDate, LocalDate endShipDate, LocalDate startRepairDate, LocalDate endRepairDate) {
        String rmaDate;
        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE
                || input.getSelectedRmaCntType() == RMACountingType.ALL_BY_ARRIVAL_DATE) {
            rmaDate = "a.internalArrivalDate";
        }
        else {
            rmaDate = "a.internalShipmentDate";
        }

        // HashMap<String, Long> returnValuesMap = new HashMap<String, Long>();

        StringBuilder queryStatement = new StringBuilder("select a.errorCodeName, count(a) from MaterializedServiceMessage a ");
        queryStatement.append("where ").append(rmaDate).append(" >= :startRepairDate and ").append(rmaDate).append(" < :endRepairDate ");

        if (input.getSelectedRmaCntType() == RMACountingType.CLOSED_BY_ARRIVAL_DATE) {
            queryStatement.append(" and a.internalShipmentDate is not null ");
        }

        queryStatement.append("and a.customerShipmentDate >= :startShipDate and a.customerShipmentDate < :endShipDate ");

        // Add standard filter criteria
        setupFilterCriteria(queryStatement, input, false, false);

        queryStatement.append("group by a.errorCodeName ");

        TypedQuery<Object[]> query = em.createQuery(queryStatement.toString(), Object[].class);
        query.setParameter("startShipDate", startShipDate);
        query.setParameter("endShipDate", endShipDate);
        query.setParameter("startRepairDate", startRepairDate);
        query.setParameter("endRepairDate", endRepairDate);

        List<Object[]> result = query.getResultList();
        Map<String, Integer> returnValuesMap = new HashMap<>();
        result.stream()
                .forEach(values -> {
                    String errorCode = (String) values[0];
                    int counter = ((Number) values[1]).intValue();
                    returnValuesMap.put(errorCode, counter);
                });

        return returnValuesMap;
    }

    private List<FieldPerfReportCauseResultRecord> convertToRecordList(Map<String, Integer> map) {
        return map.entrySet().stream()
                .map(entry -> new FieldPerfReportCauseResultRecord(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(FieldPerfReportCauseResultRecord::cause, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }


    /**
     * Calculate date value by given input and selected interval boundary
     * @param input Such-DTO mit allen nötigen Daten
     * @param date Typ des erfragten Datums
     * @return a date value to be used within queries
     */
    private LocalDate calculateDate(FieldPerfReportSearchObject input, DateEnumeration date) {
        if (input.getReturnDate() != null && input.getShipDate() != null) {
            switch (date){
            case SHIPMENT_START:
                return input.getShipDate();
            case SHIPMENT_END:
                return input.getShipDate().plusMonths(1);
            case REPAIR_START:
                return input.getReturnDate();
            case REPAIR_END:
                return input.getReturnDate().plusMonths(1);
            default:
                return null;
            }

        }
        else if (input.getReturnDate() != null && input.getShipDate() == null) {
            switch (date){
            case SHIPMENT_START:
                return input.getReturnDate().plusMonths(-11);
            case SHIPMENT_END:
                return input.getReturnDate().plusMonths(1);
            case REPAIR_START:
                return input.getReturnDate();
            case REPAIR_END:
                return input.getReturnDate().plusMonths(1);
            default:
                return null;
            }

        }
        else if (input.getReturnDate() == null && input.getShipDate() != null) {
            switch (date){
            case SHIPMENT_START:
                return input.getShipDate();
            case SHIPMENT_END:
                return input.getShipDate().plusMonths(1);
            case REPAIR_START:
                return input.getShipDate();
            case REPAIR_END:
                return input.getShipDate().plusMonths(input.getSelectedReturnInterval());
            default:
                return null;
            }

        }
        else {
            switch (date){
            case SHIPMENT_START:
                return input.getStartDate();
            case SHIPMENT_END:
                return input.getStartDate().plusMonths(1);
            case REPAIR_START:
                return input.getStartDate();
            case REPAIR_END:
                return input.getStartDate().plusMonths(input.getSelectedReturnInterval());
            default:
                return null;
            }

        }
    }

}
