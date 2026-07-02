package com.kontron.qdw.boundary.service.xmlimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.kontron.qdw.boundary.service.XMLDataImportUtils;
import com.kontron.qdw.boundary.service.mapping.material.MaterialXMLElement;
import com.kontron.qdw.boundary.service.mapping.material.MaterialXMLRoot;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Location;
import com.kontron.qdw.domain.base.Plant;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialClass;
import com.kontron.qdw.domain.material.MaterialType;
import com.kontron.qdw.repository.base.CountryRepository;
import com.kontron.qdw.repository.base.LocationRepository;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.qdw.repository.material.MaterialClassRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialTypeRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportProcessedWithErrors;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.xml.bind.Unmarshaller;

/**
 * Import der Material-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLMaterialImportServiceBean extends AbstractXMLImportServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "material";
    private static final String FOLDER_SUB_PATH = "material";
    private static final String SCHEMA_NAME = "Material.xsd";

    private static final String ENCODING = Constants.UTF_8;

    @EJB
    private MaterialRepository materialManager;
    @EJB
    private SupplierRepository supplierManager;
    @EJB
    private MaterialTypeRepository materialTypeManager;
    @EJB
    private MaterialClassRepository materialClassManager;
    @EJB
    private PlantRepository plantManager;
    @EJB
    private CountryRepository countryManager;
    @EJB
    private LocationRepository locationManager;



    /** Perform import */
    @PermitAll
    public ITaskNodeLog runImport() {
        return super.runImport(ENTITY_NAME, FOLDER_SUB_PATH, SCHEMA_NAME, ImportType.QDW_MATERIAL);
    }



    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void importFile(String importFileName, TaskNodeLog tsk, String importDir, Unmarshaller unmarshaller) {
        logger.info("Lese " + ENTITY_NAME + "-Import Datei '{}'", importFileName);

        List<MaterialXMLElement> importedMaterials;
        // parse xml file into list of entities
        try (FileInputStream fis = new FileInputStream(new File(importDir, importFileName));
                InputStreamReader isr = new InputStreamReader(fis, ENCODING)) {
            InputSource isrc = new InputSource(isr);
            isrc.setEncoding(ENCODING);
            MaterialXMLRoot xmlRoot = (MaterialXMLRoot) unmarshaller.unmarshal(isrc);
            importedMaterials = xmlRoot.getMaterialList();
        }
        catch (Exception e) {
            // add error to response and continue with next file
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
            tsk.abortTask();
            return;
        }


        Map<String, Location> locationMap = Location.asMap(locationManager.findAll());
        Map<String, Plant> plantMap = Plant.asMap(plantManager.findAll());
        Map<String, MaterialType> materialTypeMap = MaterialType.asMap(materialTypeManager.findAll());
        Map<String, MaterialClass> materialClassMap = MaterialClass.asMap(materialClassManager.findAll());


        List<String> errorList = new ArrayList<>();
        int progressStep = 5;
        int progress = progressStep;

        int listSize = importedMaterials.size();
        int bulkSize = 2000;
        int bulkFromIdx = 0;
        int bulkToIdx = Math.min(listSize, bulkSize);

        while (bulkToIdx - bulkFromIdx > 0) {
            try {
                if ((float) bulkFromIdx / listSize * 100 > progress) {
                    progress = ((int) ((float) bulkFromIdx / listSize * 100) / progressStep) * progressStep;
                    logger.info(progress + "% done");
                    progress += progressStep;
                }

                // aktuell verarbeiteter Batch
                List<MaterialXMLElement> curBatch = importedMaterials.subList(bulkFromIdx, bulkToIdx);
                importEntryBatch(curBatch, importFileName, errorList, locationMap, plantMap, materialTypeMap, materialClassMap);

                bulkFromIdx = bulkToIdx;
                bulkToIdx = Math.min(listSize, bulkFromIdx + bulkSize);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
                tsk.abortTask();
                return;
            }
        }
        logger.info("100% done");

        // add success to response
        if (errorList.isEmpty()) {
            tsk.addSubTask(new FileImportSuccessfulLog(importFileName, listSize));
        }
        else {
            tsk.addSubTask(new FileImportProcessedWithErrors(importFileName, errorList, listSize));
        }

        try {
            XMLDataImportUtils.moveFileToArchive(FOLDER_SUB_PATH, importFileName);
        }
        catch (Exception e) {
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, "Failed moving file to import archive", importFileName, e));
            tsk.abortTask();
            return;
        }
    }

    private void importEntryBatch(List<MaterialXMLElement> materials, String importFileName, List<String> errorList,
            Map<String, Location> locationMap, Map<String, Plant> plantMap,
            Map<String, MaterialType> materialTypeMap, Map<String, MaterialClass> materialClassMap) {

        // keine Herstellermaterialien importieren
        List<MaterialXMLElement> importedMaterials = materials.stream()
                .filter(mat -> !mat.getMaterialType().equals("HERS"))
                .collect(Collectors.toList());

        createMissingMTypeMClassOLoc(materialTypeMap, materialClassMap, locationMap, importedMaterials);


        // Map zu allen gesuchten Materialien erstellen;
        // key ist SAP-Nummer; Suche nach SAP-Nummer oder ersatzweise nach Materialnummer
        // Es wird eine Map zurück gegeben, in der sämtliche angeforderten SAP-Nummern als key vorhanden sind!
        // Die Map wird mit den so gefundenen Materialien befüllt.

        Map<String, MaterialXMLElement> importedMaterialMap = importedMaterials.stream()
                .collect(Collectors.toMap(mat -> StringUtil.removeLeadingZero(mat.getSapNumber()), v -> v));

        Map<String, Material> existingMaterialMapBySapNr = cacheExistingMaterialOfBatchBySapNr(importedMaterialMap);
        Map<String, Material> existingMaterialMapByMatNr = cacheExistingMaterialOfBatchByMatNr(importedMaterialMap, existingMaterialMapBySapNr);


        for (Entry<String, MaterialXMLElement> entry : importedMaterialMap.entrySet()) {
            String sapNumber = entry.getKey();
            MaterialXMLElement importedMaterial = entry.getValue();
            Material existingMaterial = existingMaterialMapBySapNr.get(sapNumber);
            if (existingMaterial == null) {
                existingMaterial = existingMaterialMapByMatNr.get(importedMaterial.getMaterialNumber());
            }

            if (existingMaterial == null) {
                // Material does not exist!
                Material newMaterial = new Material();
                newMaterial.setMaterialNumber(importedMaterial.getMaterialNumber());
                newMaterial.setSapNumber(sapNumber);

                newMaterial.setShortText(importedMaterial.getShortText());
                newMaterial.setMaterialHierarchy(buildMaterialHierarchy(importedMaterial));

                newMaterial.setMaterialType(materialTypeMap.get(importedMaterial.getMaterialType()));
                newMaterial.setMaterialClass(materialClassMap.get(importedMaterial.getMaterialClass()));
                newMaterial.setOwnerLocation(locationMap.get(importedMaterial.getOwnerLocation()));

                existingMaterial = materialManager.persist(newMaterial, true, true, true);
            }
            else {
                // Update data of existing material
                existingMaterial.setMaterialNumber(importedMaterial.getMaterialNumber());
                existingMaterial.setSapNumber(sapNumber);

                existingMaterial.setShortText(importedMaterial.getShortText());
                existingMaterial.setMaterialHierarchy(buildMaterialHierarchy(importedMaterial));

                existingMaterial.setMaterialType(materialTypeMap.get(importedMaterial.getMaterialType()));
                existingMaterial.setMaterialClass(materialClassMap.get(importedMaterial.getMaterialClass()));
                existingMaterial.setOwnerLocation(locationMap.get(importedMaterial.getOwnerLocation()));
            }
        } // end for importedMaterialMap
    }



    private void createMissingMTypeMClassOLoc(Map<String, MaterialType> materialTypeMap, Map<String, MaterialClass> materialClassMap,
            Map<String, Location> locationMap, List<MaterialXMLElement> importedMaterials) {
        importedMaterials.stream()
                .map(MaterialXMLElement::getMaterialType)
                .distinct()
                .filter(Predicate.not(materialTypeMap::containsKey))
                .forEach(matType -> {
                    MaterialType newMatType = materialTypeManager.persist(new MaterialType(matType), false, false);
                    materialTypeMap.put(newMatType.getCode(), newMatType);
                });

        importedMaterials.stream()
                .map(MaterialXMLElement::getMaterialClass)
                .distinct()
                .filter(Predicate.not(materialClassMap::containsKey))
                .forEach(matClass -> {
                    MaterialClass newMatClass = materialClassManager.persist(new MaterialClass(matClass), false, false);
                    materialClassMap.put(newMatClass.getCode(), newMatClass);
                });

        importedMaterials.stream()
                .map(MaterialXMLElement::getOwnerLocation)
                .distinct()
                .filter(Predicate.not(locationMap::containsKey))
                .forEach(oLoc -> {
                    Location newLocation = locationManager.persist(new Location(oLoc), false, false);
                    locationMap.put(newLocation.getCode(), newLocation);
                });
    }

    /**
     * @return the material hierarchy string or null if first level won't be provided by interface
     */
    private String buildMaterialHierarchy(MaterialXMLElement material) {
        StringBuilder matHierarchy = new StringBuilder();

        if (StringUtils.isNotEmpty(material.getLevel1())) {
            matHierarchy.append(material.getLevel1());

            if (StringUtils.isNotEmpty(material.getLevel2())) {
                matHierarchy.append(" ").append(material.getLevel2());

                if (StringUtils.isNotEmpty(material.getLevel3())) {
                    matHierarchy.append(" ").append(material.getLevel3());

                    if (StringUtils.isNotEmpty(material.getLevel4())) {
                        matHierarchy.append(" ").append(material.getLevel4());
                    }
                }
            }
        }

        return matHierarchy.toString();
    }

    /**
     * @return Map mit gefundenen Materialien nach SAP-Nummer
     */
    private Map<String, Material> cacheExistingMaterialOfBatchBySapNr(Map<String, MaterialXMLElement> importedMaterialMap) {
        return materialManager.findBySAPNumbers(importedMaterialMap.keySet(), false);
    }

    /**
     * @return Map mit gefundenen Materialien nach Materialnummer, die bei der Suche nach SAP-Nummer nicht gefunden werden konnten.
     */
    private Map<String, Material> cacheExistingMaterialOfBatchByMatNr(
            Map<String, MaterialXMLElement> importedMaterialMap,
            Map<String, Material> existingMaterialMap) {
        List<String> missingMatNr = existingMaterialMap.entrySet().stream()
                // wurde nach SAP-Nummer nicht gefunden..
                .filter(entry -> entry.getValue() == null)
                // Materialnummer des zu importierenden Eintrags (zu key SAP-Nummer) holen
                .map(entry -> importedMaterialMap.get(entry.getKey()).getMaterialNumber())
                .collect(Collectors.toList());

        return materialManager.findByMaterialNumbers(missingMatNr, false);
    }

}
