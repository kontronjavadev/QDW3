package com.kontron.qdw.boundary.service.rebuild;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.service.process.TaskCall;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Rebuild der Shipment-Arrival-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class ShipmentArrivalRebuildMaterializedDeltaServiceBean extends AbstractShipmentArrivalRebuildMaterializedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("shipment arrival rebuild materialized");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask, "arrival_shipment_mv_tmp_delta");
        execCreate(ownTask, "arrival_shipment_mv_tmp_delta", true);

        execUpdateSvcMsg(ownTask);
        execCopyData(ownTask);
        execResetRebuild(ownTask);

        execRemoveCanceled(ownTask);
        ownTask.finishTask();
    }



    private void execUpdateSvcMsg(TaskNodeLog ownTask) {
        String executionSection = "update cust_ship_date in service_message_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update service_message_mv smmv ");
        sql.append("set smmv.cust_ship_date = COALESCE( ");

        // 1. Versuch: Datum aus shipment_tab
        sql.append("    (select a.shipment_date ");
        sql.append("     from shipment_tab a ");
        sql.append("     where a.serial_object = smmv.serial_object_id ");
        sql.append("     and a.shipment_date <= smmv.internal_arrival_date ");
        sql.append("     order by a.shipment_date desc limit 1), ");

        // 2. Versuch (nur ausgeführt, wenn 1. Versuch NULL ergab): Datum aus assembly_shipment_mv
        sql.append("    (select b.shipment_date ");
        sql.append("     from assembly_shipment_mv b ");
        sql.append("     where b.serial_object = smmv.serial_object_id ");
        sql.append("     and b.shipment_date <= smmv.internal_arrival_date ");
        sql.append("     order by b.shipment_date desc limit 1) ");

        sql.append(") ");

        // Sauberer Filter statt implizitem Join
        sql.append("where exists ( ");
        sql.append("    select 1 from shipment_tab f ");
        sql.append("    where f.serial_object = smmv.serial_object_id ");
        sql.append("    and f.rebuild_flag = 1 ");
        sql.append(") ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCopyData(TaskNodeLog ownTask) {
        String executionSection = "copy data from arrival_shipment_mv_tmp_delta to arrival_shipment_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder columns_arrival_shipment_mv = new StringBuilder();
        columns_arrival_shipment_mv.append("id, ");
        columns_arrival_shipment_mv.append("serial_object_id, ");
        columns_arrival_shipment_mv.append("parent_serial_object_id, ");
        columns_arrival_shipment_mv.append("serial_number, ");
        columns_arrival_shipment_mv.append("parent_serial_number, ");
        columns_arrival_shipment_mv.append("material_number, ");
        columns_arrival_shipment_mv.append("parent_material_number, ");
        columns_arrival_shipment_mv.append("material_type, ");
        columns_arrival_shipment_mv.append("parent_material_type, ");
        columns_arrival_shipment_mv.append("material_short_text, ");
        columns_arrival_shipment_mv.append("parent_material_short_text, ");
        columns_arrival_shipment_mv.append("sap_no, ");
        columns_arrival_shipment_mv.append("parent_sap_no,");
        columns_arrival_shipment_mv.append("material_hierarchy, ");
        columns_arrival_shipment_mv.append("parent_material_hierarchy, ");
        columns_arrival_shipment_mv.append("revision_id, ");
        columns_arrival_shipment_mv.append("revision_no, ");
        columns_arrival_shipment_mv.append("assembly_date, ");
        columns_arrival_shipment_mv.append("assembly_po, ");
        columns_arrival_shipment_mv.append("customer_code, ");
        columns_arrival_shipment_mv.append("customer_name, ");
        columns_arrival_shipment_mv.append("country_code, ");
        columns_arrival_shipment_mv.append("country_name, ");
        columns_arrival_shipment_mv.append("shipment_date, ");
        columns_arrival_shipment_mv.append("plant, ");
        columns_arrival_shipment_mv.append("shipment_movement_type, ");
        columns_arrival_shipment_mv.append("customer_order_number, ");
        columns_arrival_shipment_mv.append("shipment_id, ");
        columns_arrival_shipment_mv.append("material, ");
        columns_arrival_shipment_mv.append("serial_object, ");
        columns_arrival_shipment_mv.append("owner_location, ");
        columns_arrival_shipment_mv.append("parent_revision_id, ");
        columns_arrival_shipment_mv.append("parent_revision_no, ");
        columns_arrival_shipment_mv.append("arrival_date, ");
        columns_arrival_shipment_mv.append("supplier_code, ");
        columns_arrival_shipment_mv.append("supplier_name, ");
        columns_arrival_shipment_mv.append("arrival_movement_type, ");
        columns_arrival_shipment_mv.append("purchase_order_number, ");
        columns_arrival_shipment_mv.append("arrival_id ");

        StringBuilder sql = new StringBuilder();
        sql.append("insert into arrival_shipment_mv (");
        sql.append(columns_arrival_shipment_mv);
        sql.append(") ");
        sql.append("select ");
        sql.append(columns_arrival_shipment_mv);
        sql.append("from arrival_shipment_mv_tmp_delta");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execResetRebuild(TaskNodeLog ownTask) {
        String executionSection = "reset rebuild flag in shipments";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        // -> Um zu vermeiden, dass das rebuild-flag von zwischenzeitlich geänderten Werten
        // zurück gesetzt wird, das Ganze auf die zuvor eingelesenen Einträge beschränken.
        // Die Einschränkung auf das rebuild-flag wird damit obsolet.
        // -> Für die Filterung auf die temporäre Tabelle arrival_shipment_mv_tmp_delta
        // muss sinnvollerweise ein Index erstellt werden.

        em.createNativeQuery("ALTER TABLE arrival_shipment_mv_tmp_delta ADD INDEX idx_shpt_mv_tmp_delta(id)").executeUpdate();


        StringBuilder sql = new StringBuilder();
        sql.append("update shipment_tab a ");
        sql.append("inner join arrival_shipment_mv_tmp_delta b on a.id = b.id ");
        sql.append("set a.rebuild_flag = 0 ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
