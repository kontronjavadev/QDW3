package com.kontron.qdw.repository.material;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kontron.qdw.domain.material.*;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class MaterialRevisionRepository extends AbstractRepository<MaterialRevision, Long> {
    @Generated
    private static final String PARAM_ID = "id";
    @Generated
    private final BoMItemRepository boMItemManager;

    public record MatRevKey(String materialNumber, String plantCode, String revisionNumber) {
    }

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionRepository() {
        this.boMItemManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param boMItemManager
     */
    @Inject
    @Generated
    public MaterialRevisionRepository(BoMItemRepository boMItemManager) {
        this.boMItemManager = boMItemManager;
    }



    /**
     * Find last material revision
     * 
     * @param materialNumber
     * @return the latest material revision if one has bean found
     */
    public MaterialRevision getLastMaterialRevision(String materialNumber, String plantCode) {
        StringBuilder statement = new StringBuilder();
        statement.append("select a from MaterialRevision a ");
        statement.append("where a.material.materialNumber = :paramMat ");
        statement.append("and a.plant.code = :paramPlant ");
        statement.append("order by a.creationDate desc");

        @SuppressWarnings("resource")
        TypedQuery<MaterialRevision> query = getEntityManager().createQuery(statement.toString(), MaterialRevision.class);
        query.setParameter("paramMat", materialNumber);
        query.setParameter("paramPlant", plantCode);

        List<MaterialRevision> revisionList = query.getResultList();

        if (revisionList.size() == 0) {
            return null;
        }

        return revisionList.get(0);
    }

    /**
     * Holt die zuletzt angelegte Revision anhand Materialnummer, Werk und Revisionsnummer
     * 
     * @param materialNumber
     * @param revisionNumber
     * @return a material revision if one has been found
     */
    public MaterialRevision getLastMaterialRevisionByMatNr(String materialNumber, String plantCode, String revisionNumber) {
        // alle Revisionen anhand Materialnummer, Werk und Revisionsnummer, inkl. Revisionen mit Zeitstempel (umgekehrt chronologisch)
        StringBuilder statement = new StringBuilder();
        statement.append("select a from MaterialRevision a ");
        statement.append("where a.material.materialNumber = :paramMat ");
        statement.append("and a.plant.code = :paramPlant ");
        statement.append("and (a.revisionNumber = :paramRev or a.revisionNumber like :paramRevLike) ");
        // <revnr> or <revnr>-ttmmjj_ssmmss => like <revnr>-<6Zeichen>_<6Zeichen>
        statement.append("order by a.creationDate desc ");

        @SuppressWarnings("resource")
        TypedQuery<MaterialRevision> query = getEntityManager().createQuery(statement.toString(), MaterialRevision.class);
        query.setParameter("paramMat", materialNumber);
        query.setParameter("paramPlant", plantCode);
        query.setParameter("paramRev", revisionNumber);
        query.setParameter("paramRevLike", revisionNumber + "-______\\_______");

        List<MaterialRevision> revisionList = query.getResultList();

        if (revisionList.size() == 0) {
            return null;
        }

        return revisionList.getFirst();
    }

    /**
     * Holt die zuletzt angelegte Revision anhand Materialnummer, Werk und Revisionsnummer
     * 
     * @param materialNumber
     * @param revisionNumber
     * @return a material revision if one has been found
     */
    public Map<MatRevKey, MaterialRevision> getLastMaterialRevisionByMatNr(Collection<MatRevKey> keys) {
        if (keys == null || keys.isEmpty()) {
            return Collections.emptyMap();
        }

        List<String> matNrs = keys.stream().map(MatRevKey::materialNumber).distinct().toList();
        List<String> plants = keys.stream().map(MatRevKey::plantCode).distinct().toList();

        // Mit einer Abfrage alle Revisionen anhand Materialnummer und Werk. Filterung nach Revisionsnummer erfolgt im Anschluss in Java
        String statement = "select a from MaterialRevision a "
                + "join a.material m " // Join mit Alias 'm' für das Filtern; muss wohl in Kombination mit fetch sein
                + "join fetch a.material " // Fetch Join (strikt ohne Alias) für den Heap
                + "where m.materialNumber in :mats "
                + "and a.plant.code in :plants ";

        @SuppressWarnings("resource")
        TypedQuery<MaterialRevision> query = getEntityManager().createQuery(statement, MaterialRevision.class);
        query.setParameter("mats", matNrs);
        query.setParameter("plants", plants);

        List<MaterialRevision> bulkResults = query.getResultList();

        Set<MatRevKey> requestedKeys = new HashSet<>(keys);
        Map<MatRevKey, MaterialRevision> resultMap = new HashMap<>();
        // Pattern für Postfix zu <revnr>-ttmmjj_ssmmss mit Vereinfachung auf bloße Ziffern
        Pattern timestampSuffix = Pattern.compile("-\\d{6}_\\d{6}$");

        // Zuordnung und Filterung komplett im RAM
        for (MaterialRevision candidate : bulkResults) {
            String fullRev = candidate.getRevisionNumber();

            // Basis-Revision ermitteln, indem ein passendes Suffix abgeschnitten wird
            Matcher matcher = timestampSuffix.matcher(fullRev);
            String baseRev = matcher.find() ? fullRev.substring(0, matcher.start()) : fullRev;

            MatRevKey candidateKey = new MatRevKey(
                    candidate.getMaterial().getMaterialNumber(),
                    candidate.getPlant().getCode(),
                    baseRev);

            // Nur verarbeiten, wenn wir diesen Key auch wirklich gesucht haben (filtert Beifang raus)
            if (requestedKeys.contains(candidateKey)) {
                // Merge behält bei Kollisionen den Eintrag mit dem jüngsten Datum
                resultMap.merge(candidateKey, candidate,
                        (existing, current) -> current.getCreationDate().compareTo(existing.getCreationDate()) > 0 ? current : existing);
            }
        }

        return resultMap;
    }



    /**
     * Find a persistent material revision by using the primary key of the provided object
     * @param materialRevision
     * @return the material revision or null if the object could not be found
     */
    @Generated
    public MaterialRevision findById(MaterialRevision materialRevision) {
        return findById(materialRevision.getId());
    }

    /**
     * Create a deep copy of the given material revision
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new material revision
     */
    @Generated
    public MaterialRevision copy(MaterialRevision sourceObject, MaterialRevision targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new MaterialRevision();
            targetObject.setMaterial(sourceObject.getMaterial());
        }

        targetObject.setRevisionNumber(sourceObject.getRevisionNumber());
        targetObject.setRev2(sourceObject.getRev2());
        targetObject.setRev6(sourceObject.getRev6());
        targetObject.setAlternativeNumber(sourceObject.getAlternativeNumber());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setPlant(sourceObject.getPlant());

        targetObject = persist(targetObject, false, false);

        for (final BoMItem boMItem : sourceObject.getBoMItems()) {
            var newBoMItem = new BoMItem();
            newBoMItem.setMaterialRevision(targetObject);

            newBoMItem = boMItemManager.copy(boMItem, newBoMItem, loggedOnUserId);
            targetObject.getBoMItems().add(newBoMItem);
        }

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Get the material of this material revision
     * @param id
     * @return the material of this material revision, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(MaterialRevision.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this material revision
     * @param id
     * @return the plant of this material revision, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(MaterialRevision.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all bom items of this material revision
     * @param id
     * @return a list of bom items of this material revision
     */
    @Generated
    public List<BoMItem> getBoMItems(long id) {
        final TypedQuery<BoMItem> query = em.createNamedQuery(MaterialRevision.NQ_GET_BOMITEMS, BoMItem.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'material' attribute of this material revision
     * @param id
     * @param material
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final MaterialRevision bean = findById(id, true);

        bean.setMaterial(material);
    }

    /**
     * Change the 'plant' attribute of this material revision
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final MaterialRevision bean = findById(id, true);

        bean.setPlant(plant);
    }

}
