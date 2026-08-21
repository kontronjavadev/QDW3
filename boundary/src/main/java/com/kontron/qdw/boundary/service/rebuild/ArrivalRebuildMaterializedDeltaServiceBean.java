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
 * Rebuild der Arrival-"Materialized table".
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Interface implementiert wird und sonst keine No-Interface-View bereit gestellt wird
public class ArrivalRebuildMaterializedDeltaServiceBean extends AbstractArrivalRebuildMaterializedServiceBean implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        // Es wird das Delta berechnet, also nur die Werte mit rebuildflag=1
        // Die ServiceMessages werden aktualisiert
        // Die Delta-Daten werden in die Materialized Table übernommen
        // Das rebuild-flag wird wieder zurück gesetzt
        // Gecancelte Daten werden gelöscht
        // Dauer: wenige Minuten
        return new TaskNodeLog("arrival rebuild materialized");
    }

    /** Perform rebuild */
    @Override
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void execTask(TaskNodeLog ownTask) {
        execDrop(ownTask, "materialized_arrival_mv_tmp_delta");
        execCreate(ownTask, "materialized_arrival_mv_tmp_delta", true);

        execUpdateSvcMsg(ownTask);
        execCopyData(ownTask);
        execResetRebuild(ownTask);

        execRemoveCanceled(ownTask);
        ownTask.finishTask();
    }



    private void execUpdateSvcMsg(TaskNodeLog ownTask) {
        String executionSection = "update sup_arrival_date, supplier_code, supplier_name in service_message_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder sql = new StringBuilder();
        sql.append("update service_message_mv smmv ");

        // 1. filter für relevante service messages
        sql.append("inner join ( ");
        sql.append("    select distinct serial_object ");
        sql.append("    from arrival_tab ");
        sql.append("    where rebuild_flag = 1 ");
        sql.append(") delta_filter on (smmv.serial_object_id = delta_filter.serial_object) ");

        // 2. lateral join für den aktuellsten Datensatz
        sql.append("left join lateral ( ");
        sql.append("    select a.arrival_date, a.supplier ");
        sql.append("    from arrival_tab a ");
        sql.append("    where a.serial_object = smmv.serial_object_id ");
        sql.append("    and a.arrival_date <= smmv.cust_ship_date ");
        sql.append("    order by a.arrival_date desc, a.id desc ");
        sql.append("    limit 1 ");
        sql.append(") latest_arr on true ");

        // 3. join für den Namen des Suppliers
        sql.append("left join supplier_tab s on (latest_arr.supplier = s.code) ");

        // 4. Werte in einem Durchlauf setzen
        sql.append("set smmv.sup_arrival_date = latest_arr.arrival_date, ");
        sql.append("    smmv.supplier_code = latest_arr.supplier, ");
        sql.append("    smmv.supplier_name = s.name ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execCopyData(TaskNodeLog ownTask) {
        String executionSection = "copy data from materialized_arrival_mv_tmp_delta to materialized_arrival_mv";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        StringBuilder columns_arrival_mv = new StringBuilder();
        columns_arrival_mv.append("id, ");
        columns_arrival_mv.append("serial_object_id, ");
        columns_arrival_mv.append("parent_serial_object_id, ");
        columns_arrival_mv.append("serial_number, ");
        columns_arrival_mv.append("parent_serial_number, ");
        columns_arrival_mv.append("material_number, ");
        columns_arrival_mv.append("parent_material_number, ");
        columns_arrival_mv.append("material_type, ");
        columns_arrival_mv.append("parent_material_type, ");
        columns_arrival_mv.append("material_short_text, ");
        columns_arrival_mv.append("parent_material_short_text, ");
        columns_arrival_mv.append("sap_no, ");
        columns_arrival_mv.append("parent_sap_no, ");
        columns_arrival_mv.append("material_hierarchy, ");
        columns_arrival_mv.append("parent_material_hierarchy, ");
        columns_arrival_mv.append("revision_id, ");
        columns_arrival_mv.append("revision_no, ");
        columns_arrival_mv.append("assembly_date, ");
        columns_arrival_mv.append("assembly_po, ");
        columns_arrival_mv.append("supplier_code, ");
        columns_arrival_mv.append("supplier_name, ");
        columns_arrival_mv.append("country_code, ");
        columns_arrival_mv.append("country_name, ");
        columns_arrival_mv.append("arrival_date, ");
        columns_arrival_mv.append("plant, ");
        columns_arrival_mv.append("movement_type, ");
        columns_arrival_mv.append("order_number, ");
        columns_arrival_mv.append("material, ");
        columns_arrival_mv.append("serial_object, ");
        columns_arrival_mv.append("parent_revision_id, ");
        columns_arrival_mv.append("parent_revision_no ");

        StringBuilder sql = new StringBuilder();
        sql.append("insert into materialized_arrival_mv (");
        sql.append(columns_arrival_mv);
        sql.append(") ");
        sql.append("select ");
        sql.append(columns_arrival_mv);
        sql.append("from materialized_arrival_mv_tmp_delta");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

    private void execResetRebuild(TaskNodeLog ownTask) {
        String executionSection = "reset rebuild flag in arrivals";
        logger.info(executionSection);
        TaskLeafLog subTsk = ownTask.createNewSubTaskLeaf(executionSection);

        // -> Um zu vermeiden, dass das rebuild-flag von zwischenzeitlich geänderten Werten
        // zurück gesetzt wird, das Ganze auf die zuvor eingelesenen Einträge beschränken.
        // Die Einschränkung auf das rebuild-flag wird damit obsolet.
        // -> Für die Filterung auf die temporäre Tabelle materialized_arrival_mv_tmp_delta
        // muss sinnvollerweise ein Index erstellt werden.

        em.createNativeQuery("ALTER TABLE materialized_arrival_mv_tmp_delta ADD INDEX idx_arrvl_mv_tmp_delta(id)").executeUpdate();


        StringBuilder sql = new StringBuilder();
        sql.append("update arrival_tab a ");
        sql.append("inner join materialized_arrival_mv_tmp_delta b on a.id = b.id ");
        sql.append("set a.rebuild_flag = 0 ");

        em.createNativeQuery(sql.toString()).executeUpdate();
        subTsk.finishTaskWithSuccess();
    }

}
