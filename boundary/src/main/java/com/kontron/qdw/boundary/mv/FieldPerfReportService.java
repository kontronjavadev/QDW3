package com.kontron.qdw.boundary.mv;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Strings;

import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.SerialNumberSetItem;
import com.kontron.qdw.dto.base.CustomerDTO;
import com.kontron.qdw.dto.base.PlantSearchDTO;
import com.kontron.qdw.dto.base.SupplierDTO;
import com.kontron.qdw.dto.mv.DistributionType;
import com.kontron.qdw.dto.mv.FieldPerfReportSearchObject;
import com.kontron.qdw.dto.service.RepairErrorCodeListDTO;
import com.kontron.qdw.repository.material.MaterialNumberSetRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.SerialNumberSetRepository;
import com.kontron.util.datetime.TimeMeasurement;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import net.sourceforge.jbizmo.commons.search.SearchService;

@Stateless
public class FieldPerfReportService {

    @PersistenceContext
    protected EntityManager em;
    @EJB
    private MaterialNumberSetRepository materialNumberSetRepo;
    @EJB
    private MaterialRepository materialRepo;
    @EJB
    private SerialNumberSetRepository serNrSetRepo;


    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public HashMap<String, Long> getShipmentsPerMonth(FieldPerfReportSearchObject input) {
        System.out.println("start getShipmentsPerMonth()");
        TimeMeasurement tm = new TimeMeasurement("getShipmentsPerMonth()");

        LocalDate startDate = input.getStartDate();
        LocalDate endDate = input.getStartDate().withDayOfMonth(1);

        int startYear = startDate.getYear();
        int startMonth = startDate.getMonthValue();
        int endYear = endDate.getYear();
        int endMonth = endDate.getMonthValue();

        // initialize result map
        TimeMeasurement tm1 = tm.createSubMeasurement("for input.getAnalysisInterval()");
        System.out.println("\n" + tm);

        // shipmentMap je Monat mit 0 initialisieren
        HashMap<String, Long> shipmentMap = new HashMap<String, Long>();
        for (int l = 0; l < input.getSelectedAnalInterval(); l++) {
            endYear = endDate.getYear();
            endMonth = endDate.getMonthValue();
            shipmentMap.put(String.format("%d-%d", endYear, endMonth), 0L);

            endDate = endDate.plusMonths(1);
        }
        tm1.stopMeasurement();

        // Abfrage zu Anzahl shipments je Monat/Jahr; je nachdem, welche Tabelle verwendet werden kann
        if (input.getSelectedDistType() == DistributionType.ALL || input.getSelectedDistType() == DistributionType.CUSTOMER) {
            if (!useShipmentQueryOnAggregatedLevel(input)) {
                TimeMeasurement tm2 = tm.createSubMeasurement("select MaterializedArrivalShipment");
                System.out.println("\n" + tm);

                StringBuilder queryStatement = new StringBuilder();
                queryStatement.append("select count(a), function('year', a.shipmentDate), function('month', a.shipmentDate) ");
                queryStatement.append("from MaterializedArrivalShipment a ");

                queryStatement.append("where a.shipmentDate >= :startDate and a.shipmentDate < :endDate ");

                // Add standard filter criteria
                setupFilterCriteria(queryStatement, input, true, false);
                queryStatement.append("group by function('year', a.shipmentDate), function('month', a.shipmentDate) ");

                Query query = em.createQuery(queryStatement.toString());
                query.setParameter("startDate", startDate);
                query.setParameter("endDate", endDate);
                List<?> result = query.getResultList();

                TimeMeasurement tm2_1 = tm2.createSubMeasurement("for result.iterator()");
                System.out.println("\n" + tm);
                for (Iterator<?> iter = result.iterator(); iter.hasNext();) {
                    Object[] values = (Object[]) iter.next();
                    long shipments = (Long) values[0];
                    int shipmentYear = (Integer) values[1];
                    int shipmentMonth = (Integer) values[2];

                    shipmentMap.put(String.format("%d-%d", shipmentYear, shipmentMonth), shipments);
                }
                tm2_1.stopMeasurement();
                tm2.stopMeasurement();
            }
            else {
                TimeMeasurement tm2 = tm.createSubMeasurement("select AggregatedShipmentArrival");
                System.out.println("\n" + tm);
                /*************** NEW AGGREGATED IMPLEMENTATION **************************/

                StringBuilder queryStatement = new StringBuilder();
                queryStatement.append("select sum(a.shipments), concat(a.year, '-', a.month) shipmentperiod ");
                queryStatement.append("from AggregatedShipmentArrival a ");
                queryStatement.append("where a.year * 12 + a.month >= ").append(startYear * 12 + startMonth).append(" ");
                queryStatement.append("and   a.year * 12 + a.month <= ").append(endYear * 12 + endMonth).append(" ");

                // Add standard filter criteria
                setupFilterCriteria(queryStatement, input, true, true);

                queryStatement.append("group by a.year, a.month ");

                Query query = em.createQuery(queryStatement.toString());

                List<?> result = query.getResultList();
                TimeMeasurement tm2_1 = tm2.createSubMeasurement("for result.iterator()");
                System.out.println("\n" + tm);
                for (Iterator<?> iter = result.iterator(); iter.hasNext();) {
                    Object[] values = (Object[]) iter.next();
                    Long shipments = (Long) values[0];
                    String shipmentPeriod = (String) values[1];

                    shipmentMap.put(shipmentPeriod, shipments);
                }
                /*************** NEW AGGREGATED IMPLEMENTATION **************************/
                tm2_1.stopMeasurement();
                tm2.stopMeasurement();
            }
        } // end if (DistributionType.ALL || DistributionType.CUSTOMER)


        // Abfrage zu Anzahl shipments je Monat/Jahr
        if (input.getSelectedDistType() == DistributionType.ALL || input.getSelectedDistType() == DistributionType.INTERNAL) {
            TimeMeasurement tm3 = tm.createSubMeasurement("select MaterializedAssemblyShipment");
            System.out.println("\n" + tm);
            StringBuilder queryStatement = new StringBuilder();
            queryStatement.append("select count(a), function('year', a.shipmentDate), function('month', a.shipmentDate) ");
            queryStatement.append("from MaterializedAssemblyShipment a ");

            queryStatement.append("where a.shipmentDate >= :startDate and a.shipmentDate < :endDate ");

            // Add standard filter criteria
            setupFilterCriteria(queryStatement, input, true, false);
            queryStatement.append("group by function('year', a.shipmentDate), function('month', a.shipmentDate) ");

            Query query = em.createQuery(queryStatement.toString());
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<?> result = query.getResultList();

            TimeMeasurement tm3_1 = tm3.createSubMeasurement("for result.iterator()");
            System.out.println("\n" + tm);
            for (Iterator<?> iter = result.iterator(); iter.hasNext();) {
                Object[] values = (Object[]) iter.next();
                long shipments = (Long) values[0];
                int shipmentYear = (Integer) values[1];
                int shipmentMonth = (Integer) values[2];
                String shipmentPeriod = String.format("%d-%d", shipmentYear, shipmentMonth);

                shipmentMap.put(shipmentPeriod, shipments + shipmentMap.get(shipmentPeriod));
            }
            tm3_1.stopMeasurement();
            tm3.stopMeasurement();
        } // end if (DistributionType.ALL || DistributionType.INTERNAL)

        tm.stopMeasurement();
        System.out.println("end getShipmentsPerMonth()\n" + tm);
        return shipmentMap;
    }



    /**
     * Add standard filter criteria for all queries this bean provides
     * Aufrufe zu Tabellen:
     * MaterializedArrivalShipment (arrival_shipment_mv)
     * AggregatedShipmentArrival (aggregated_shipment_arrival_tab)
     * MaterializedAssemblyShipment (assembly_shipment_mv)
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
            if (!input.getRevNo().isEmpty()) {
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

        if (!input.getMatHier().isEmpty()) {
            queryStatement.append(parseCriterion("a.materialHierarchy", input.getMatHier()));
        }


        if (!queryOnShipmentOnly) {
            queryStatement.append(parseCriterion("a.errorId", input.getErrorId()));
            queryStatement.append(parseCriterion("a.defectComponent", input.getDefCmpt()));

            queryStatement.append("and ").append("a.rmaType").append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getSelectedRmaTypes(), true))
                    .append(") ");

            queryStatement.append("and ").append("a.errorCodeGroup").append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getSelectedErrGrps(), true))
                    .append(") ");

            queryStatement.append("and ").append("a.origin").append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getOrigins(), true))
                    .append(") ");

            if (!input.getEpidemic()) {
                queryStatement.append(" and a.epidemicFailure = false ");
            }

            // im Oiginal mit Bedingung, jedoch ist der Wert stets false, die Bedingung also stets erfüllt
            // if (!input.isCMReport()) {
            queryStatement.append(" and ").append("a.errorCodeName").append(" in (")
                    .append(StringUtil.collectionToSqlWhereInString(input.getSelectedErr(), RepairErrorCodeListDTO::getName, true))
                    .append(") ");
            // }
            // else {
            // queryStatement
            // .append("and a.errorCodeName in ('ZWW01-110 - Solder issue','ZWW01-105 - Defective component / hardware','ZWW02-201 - Assembly error') ");
            // }
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
        if (filter.isEmpty()) {
            return " ";
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
        // sondern es wird die materialized view rangezigen, wenn...
        // ... nicht DistributionTpe "ALL" zur Filterung angegeben ist
        if (input.getSelectedDistType() != DistributionType.ALL) {
            return false;
        }

        // ... Parent Material zur Filterung angegeben ist
        if (!input.getpMatNrs().isEmpty()) {
            return false;
        }

        // ... Material hierarchy zur Filterung angegeben ist
        if (!input.getMatHier().isEmpty()) {
            return false;
        }

        // ... ein Satz Seriennummern zur Filterung angegeben ist
        if (input.getSelectedSerNrSet() != null) {
            return false;
        }

        // Andernfalls kann die Abfrage auf die aggregierte materialized view angewandt werden.
        return true;
    }

}
