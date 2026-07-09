package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.material.MaterialRevisionBoundaryService;
import com.kontron.qdw.boundary.service.mapping.bom.BoMItemXMLElement;
import com.kontron.qdw.boundary.service.mapping.bom.BoMXMLElement;
import com.kontron.qdw.boundary.service.mapping.bom.BoMXMLRoot;
import com.kontron.qdw.boundary.service.mapping.bom.dto.BoMItemComparingDto;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.material.BoMItem;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.material.BoMItemRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.util.datetime.DateUtil;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.ExceptionUtil;
import com.kontron.util.text.StringUtil;
import com.kontron.util.version.RevisionUtil;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Import der BoM-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
@LocalBean // nötig, weil Superklasse Interface implementiert und sonst keine No-Interface-View bereit gestellt wird
public class XMLBoMImportServiceBean extends AbstractXMLImportServiceBean<BoMXMLRoot, BoMXMLElement> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "BoM";
    private static final String FOLDER_SUB_PATH = "bom";
    private static final String SCHEMA_NAME = "BoM.xsd";

    @EJB
    private BoMItemRepository bomManager;
    @EJB
    private MaterialRevisionRepository materialRevisionManager;
    @EJB
    private MaterialRevisionBoundaryService materialRevisionService;
    @EJB
    private MaterialRepository materialManager;
    @EJB
    private PlantRepository plantManager;


    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    protected String getFolderSubPath() {
        return FOLDER_SUB_PATH;
    }

    @Override
    protected String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override
    protected ImportType getImportType() {
        return ImportType.QDW_BOM;
    }

    @Override
    protected Class<BoMXMLRoot> getXmlRootClazz() {
        return BoMXMLRoot.class;
    }

    @Override
    protected Function<BoMXMLRoot, List<BoMXMLElement>> getGetElementsFunction() {
        return BoMXMLRoot::getBoMs;
    }



    @Override
    protected void importBulk(String importFileName, TaskNodeLog tsk, List<BoMXMLElement> importedBoMs, List<String> errorList,
            BulkProcess bulkProcess) throws Exception {
        // aktuell verarbeiteter Batch
        List<BoMXMLElement> curBatch = importedBoMs.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());

        Set<String> plantSet = plantManager.findAll().stream().map(Plant::getCode).collect(Collectors.toSet());

        Map<String, Material> existingMaterialMap = cacheMaterial(curBatch, BoMXMLElement::getMaterialNumber, true);


        for (BoMXMLElement bom : curBatch) {
            bulkProcess.logProcess(logger);

            try {
                importEntry(bom, importFileName, errorList, existingMaterialMap, plantSet);
            }
            catch (Exception e) {
                logger.error("failed", e);
                String sapNumber = StringUtil.removeLeadingZero(bom.getMaterialNumber());
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, sapNumber, e));
                tsk.abortTask();
                throw e;
            }

            bulkProcess.nextCnt();
        }
    }



    private void importEntry(BoMXMLElement importedBom, String importFileName, List<String> errorList,
            Map<String, Material> existingMaterialMap, Set<String> plantSet) {

        String sapNumber = StringUtil.removeLeadingZero(importedBom.getMaterialNumber());

        // prüfen, ob es das Material, zu der die BoM angelegt wird, überhaupt gibt
        Material existingBoMMaterial = existingMaterialMap.get(sapNumber);

        // Material gibt es nicht. Loggen und weiter mit nächster BoM
        if (existingBoMMaterial == null) {
            String materialNumber = sapNumber.substring(0, 4) + "-" + sapNumber.substring(4);
            String errorMsg = String.format(
                    "Fehler BoM-Import Datei '%s': kein Material zu SAP-Nummer '%s' oder Materialnummer '%s' vorhanden.",
                    importFileName, sapNumber, materialNumber);
            logger.info(errorMsg);
            errorList.add(errorMsg);
            return;
        }


        if (!plantSet.contains(importedBom.getPlant())) {
            String errorMsg = String.format("Fehler BoM-Import Datei '%s': unbekannte plant '%s' zu SAP-Nummer '%s'.",
                    importFileName, importedBom.getPlant(), sapNumber);
            logger.info(errorMsg);
            errorList.add(errorMsg);
            return;
        }

        if (isBoMEmpty(importedBom.getItems())) {
            // fast 6000 bei Vollabzug; zu viele, um das zu loggen, also einfach ignorieren
            // String errorMsg = String.format("Fehler BoM-Import Datei '%s': leere BoM zu SAP-Nummer '%s'.", importFileName, sapNumber);
            // logger.info(errorMsg);
            // errorList.add(errorMsg);
            return;
        }



        try {
            // Revision zu dem Material, zu der die BoM angelegt wird, erzeugen.
            // Gibt es sie bereits, wird geprüft, ob die BoM übereinstimmt. Falls ja, wird mit null zurückgekehrt.
            // Andernfalls wird die Revision zusätzlich mit Zeitstempel versehen.
            MaterialRevision newMaterialRevision = createMatRevIfNeeded(existingBoMMaterial, importedBom);

            if (newMaterialRevision != null) {
                // Revision existiert nicht oder weicht ab, wurde also neu angelegt
                // -> nun auch BoM anlegen

                // Verbautes Material rekursiv zusammenstellen und alle Materialien dazu holen
                List<BoMItemXMLElement> collectedItems = collectItems(importedBom.getItems());
                Map<String, Material> existingBoMMaterialMap = cacheMaterial(collectedItems, BoMItemXMLElement::getItemMaterial, false);

                addItemsToBoM(importFileName, importedBom.getItems(), null, newMaterialRevision, existingBoMMaterialMap);
            }
        }
        catch (IllegalArgumentException iae) {
            // Material in der BoM existiert nicht
            errorList.add(iae.getMessage());
            logger.info(iae.getMessage());
        }
        catch (Throwable t) {
            errorList.add(ExceptionUtil.getMoreUsefulExceptionMessage(t));
        }
    }



    private List<BoMItemXMLElement> collectItems(List<BoMItemXMLElement> bomItems) {
        List<BoMItemXMLElement> collectedItems = new ArrayList<>();
        for (BoMItemXMLElement bomItem : bomItems) {
            collectedItems.add(bomItem);
            if (bomItem.getSubItems() != null) {
                collectedItems.addAll(collectItems(bomItem.getSubItems()));
            }
        }
        return collectedItems;
    }

    private void addItemsToBoM(String importFileName, List<BoMItemXMLElement> items, BoMItem parentItem, MaterialRevision bomItemRevision,
            Map<String, Material> existingBoMMaterialMap) throws IllegalArgumentException {

        if (items == null) {
            return;
        }


        for (BoMItemXMLElement bomItem : items) {
            BoMItem newItem = new BoMItem();

            if (StringUtils.isNotEmpty(bomItem.getItemMaterial())) {
                // remove leading zeros
                String itemMaterialSAPNr = StringUtil.removeLeadingZero(bomItem.getItemMaterial());

                Material bomItemMaterial = existingBoMMaterialMap.get(itemMaterialSAPNr);

                // Material gibt es nicht. Loggen und weiter mit nächster BoM
                if (bomItemMaterial == null) {
                    Material parentMaterial = parentItem == null ? bomItemRevision.getMaterial() /* toplevel */ : parentItem.getMaterial();
                    String itemMaterialMatNr = itemMaterialSAPNr.substring(0, 4) + "-" + itemMaterialSAPNr.substring(4);
                    throw new IllegalArgumentException(String.format(
                            "Fehler BoM-Import Datei '%s': verbautes Material zu SAP-Nummer '%s' oder Materialnummer '%s' nicht vorhanden. "
                                    + "Parent hat SAP-Nummer '%s' / MaterialNummer '%s'",
                            importFileName, itemMaterialSAPNr, itemMaterialMatNr, parentMaterial.getSapNumber(), parentMaterial.getMaterialNumber()));
                }

                newItem.setMaterial(bomItemMaterial);
            }

            newItem.setLabel(bomItem.getLabel());

            // We don't want to have empty items!
            if (newItem.getMaterial() == null && StringUtils.isEmpty(newItem.getLabel())) {
                continue;
            }

            newItem.setMaterialRevision(bomItemRevision);
            newItem.setQuantity(bomItem.getQuantity());
            // newItem.setParentItem(parentItem); // gibt es nicht, daher im Modelweggelassen
            newItem.setPosition(StringUtils.isNotEmpty(bomItem.getLineNumber()) ? bomItem.getLineNumber() : "0000");

            newItem = bomManager.persist(newItem, false, false);

            // add sub items of an item recursively
            addItemsToBoM(importFileName, bomItem.getSubItems(), newItem, bomItemRevision, existingBoMMaterialMap);
        }
    }



    private <T> Map<String, Material> cacheMaterial(List<T> items, Function<T, String> toSapNr, boolean fetchRevisions) {
        // Das angegebene Material in der Datenbank suchen. Die Function gibt an, wie man an die Materialnummer kommt.
        // -> Map zu allen gesuchten Materialien erstellen;
        // key ist SAP-Nummer; Suche nach SAP-Nummer oder ersatzweise nach Materialnummer
        // Die Map wird mit den so gefundenen Materialien befüllt.

        Set<String> importedBoMMaterial = items.stream()
                .map(toSapNr::apply)
                .filter(Objects::nonNull)
                .map(StringUtil::removeLeadingZero)
                .collect(Collectors.toSet());

        // Es wird eine Map zurück gegeben, in der sämtliche angeforderten SAP-Nummern als key vorhanden sind!
        Map<String, Material> existingMaterialMap = materialManager.findBySAPNumbers(importedBoMMaterial, fetchRevisions);

        // Nicht gefundene Materialien zusätzlich nach Materialnummer suchen. Die Rückgabe-Map ist ebenfalls nach SAP-Nummer!
        Set<String> missingMatNr = existingMaterialMap.entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                // Materialnummer aus SAP-Nummer generieren xxxx-xxxx
                .map(entry -> entry.getKey().substring(0, 4) + "-" + entry.getKey().substring(4))
                .collect(Collectors.toSet());

        existingMaterialMap.putAll(materialManager.alternativelyFindByMaterialNumbers(missingMatNr, fetchRevisions));
        return existingMaterialMap;
    }

    /**
     * Search for material revision and create it if it cannot be found!
     * @return the material revision
     * @throws Exception
     */
    private MaterialRevision createMatRevIfNeeded(Material material, BoMXMLElement importedBom) throws Exception {
        String revAlt = StringUtils.trimToEmpty(importedBom.getAlt());
        String rev2 = StringUtils.trimToEmpty(importedBom.getRev2());
        String rev6 = StringUtils.trimToEmpty(importedBom.getRev6());

        String revNumber;
        // If SAP doesn't provide a valid revision number we will create one
        if (revAlt.isEmpty() && rev2.isEmpty() && rev6.isEmpty()) {
            revNumber = "REV " + (material.getRevisions().size() + 1);
        }
        else {
            revNumber = RevisionUtil.calculateRevNumberBySapRevNumber(revAlt, rev2, rev6);

            // lastMatRev ist die letzte Revision des Materials (und Werk)
            // Wir suchen nun die letzte mit übereinstimmender Revisionsnummer, mit oder ohne Zeitstempel.
            // Sollte für aktuelle identisch sein, aber es könnte natürlich eine ältere aktualisiert werden.
            // Search for existing material revision in order to avoid duplicates!
            MaterialRevision existingLastRevision = materialRevisionManager.getLastMaterialRevisionByMatNr(
                    material.getMaterialNumber(), importedBom.getPlant(), revNumber);

            if (existingLastRevision != null) {
                // Compare BoM provided by SAP with existing one
                if (compareBoM(existingLastRevision, importedBom)) {
                    return null;
                }

                // We have to create an intermediate revision as we must not change an existing BoM!
                revNumber += "-" + DateUtil.nowToString("ddMMyy_HHmmss");
            }
        }

        MaterialRevision newMaterialRevision = new MaterialRevision();
        newMaterialRevision.setRevisionNumber(revNumber);
        newMaterialRevision.setAlternativeNumber(revAlt);
        newMaterialRevision.setRev2(rev2);
        newMaterialRevision.setRev6(rev6);
        newMaterialRevision.setComment("");
        newMaterialRevision.setMaterial(material);
        newMaterialRevision.setPlant(plantManager.findById(importedBom.getPlant()));

        newMaterialRevision = materialRevisionManager.persist(newMaterialRevision, false, false);

        return newMaterialRevision;
    }



    /**
     * Compare two BoMs
     * 
     * @return true if both BoMs are "equal"
     */
    private boolean compareBoM(MaterialRevision existingLastRevision, BoMXMLElement importedBomXml) {
        List<BoMItemXMLElement> notEmptyImportedBomXml = importedBomXml.getItems().stream()
                // entweder Material oder Label muss belegt sein, sonst fliegt der Eintrag raus
                .filter(importedBomItemXml -> StringUtils.isNotEmpty(importedBomItemXml.getItemMaterial())
                        || StringUtils.isNotEmpty(importedBomItemXml.getLabel()))
                .collect(Collectors.toList());

        // If both BoMs have a different number of items we can omit further checks!
        if (existingLastRevision.getBoMItems().size() != notEmptyImportedBomXml.size()) {
            return false;
        }


        // Konvertiere Import-BoM zu ComparingDto
        List<BoMItemComparingDto> importedBomItems = notEmptyImportedBomXml.stream()
                .map(importedBomItemXml -> BoMItemComparingDto.newInstance()
                        .withMaterialNumber(StringUtil.removeLeadingZero(StringUtils.defaultString(importedBomItemXml.getItemMaterial())))
                        .withLabel(importedBomItemXml.getLabel())
                        .withQuantity(importedBomItemXml.getQuantity())
                        .withLineNumber(importedBomItemXml.getLineNumber()))
                .collect(Collectors.toList());

        // Konvertiere BoM der existierenden Revision zu ComparingDto
        List<BoMItemComparingDto> existingBoMItems = existingLastRevision.getBoMItems().stream()
                .map(existingBoMItem -> BoMItemComparingDto.newInstance()
                        .withMaterialNumber(existingBoMItem.getMaterial() == null ? "" : existingBoMItem.getMaterial().getSapNumber())
                        .withLabel(existingBoMItem.getLabel())
                        .withQuantity(existingBoMItem.getQuantity())
                        .withLineNumber(existingBoMItem.getPosition()))
                .collect(Collectors.toList());



        // Compare BoM
        for (BoMItemComparingDto existingBomItem : existingBoMItems) {
            boolean itemFound = false;

            for (BoMItemComparingDto importedBomItem : importedBomItems) {
                if (compareBoMItem(importedBomItem, existingBomItem)) {
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                return false;
            }
        }

        return true;
    }

    /**
     * Compare two BoM items
     * 
     * @return true if they are "equal"
     */
    private boolean compareBoMItem(BoMItemComparingDto impBI, BoMItemComparingDto exsBI) {
        if (!equalOrBothNull(impBI.getLineNumber(), exsBI.getLineNumber())) {
            return false;
        }

        if (!equalOrBothNull(impBI.getMaterialNumber(), exsBI.getMaterialNumber())) {
            return false;
        }

        if (!equalOrBothNull(impBI.getLabel(), exsBI.getLabel())) {
            return false;
        }

        if (!equalOrBothNull(impBI.getQuantity(), exsBI.getQuantity())) {
            return false;
        }

        return true;
    }

    private boolean isBoMEmpty(List<BoMItemXMLElement> items) {
        if (CollectionUtils.isEmpty(items)) {
            return true;
        }

        for (BoMItemXMLElement bomItem : items) {
            if (StringUtils.isNotEmpty(bomItem.getItemMaterial()) || StringUtils.isNotEmpty(bomItem.getLabel())) {
                return false;
            }

            // check sub items of an item recursively
            if (!isBoMEmpty(bomItem.getSubItems())) {
                return false;
            }
        }

        return true;
    }

    private boolean equalOrBothNull(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 != null && obj2 != null) {
            return obj1.equals(obj2);
        }
        return false;
    }

}
