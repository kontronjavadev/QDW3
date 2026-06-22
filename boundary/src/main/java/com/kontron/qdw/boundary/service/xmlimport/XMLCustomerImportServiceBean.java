package com.kontron.qdw.boundary.service.xmlimport;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.kontron.constants.file.FileType;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Country;
import com.kontron.qdw.domain.base.Customer;
import com.kontron.qdw.domain.base.VerticalSector;
import com.kontron.qdw.repository.base.CountryRepository;
import com.kontron.qdw.repository.base.CustomerRepository;
import com.kontron.qdw.repository.base.VerticalSectorRepository;
import com.kontron.util.collection.CollectionUtil;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportProcessedWithErrors;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.ExceptionUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Import der Customer-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLCustomerImportServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String FOLDER_SUB_PATH = "customer";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";

    private static final String ENCODING = Constants.UTF_8;
    private static final String UNDEF_SECTOR = "UNDEF";

    // Definition of simple filter to get only *.xml files
    private static final FilenameFilter SIMPLE_XML_FILTER = FileType.XML.getFilenameFilterAllWithExtension();

    @EJB
    private CustomerRepository customerManager;
    @EJB
    private VerticalSectorRepository verticalSectorManager;
    @EJB
    private CountryRepository countryManager;

    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);



    /** Perform customer import */
    @PermitAll
    public ITaskNodeLog runImport() {
        String importDir = exchangePath + FOLDER_SUB_PATH;

        TaskNodeLog tsk = new TaskNodeLog("import customer", "import customer in folder " + importDir);

        String[] importFileNames = new File(importDir).list(SIMPLE_XML_FILTER);
        if (importFileNames.length == 0) {
            tsk.finishTask();
            return tsk;
        }



        List<String> orderedImportFileNames = com.kontron.util.file.FileUtil.getOrderedSAPImportFileNames(importFileNames, ImportType.FC_CUS);

        for (String importFileName : orderedImportFileNames) {
            importFile(importFileName, tsk, importDir);
        }

        tsk.finishTask();
        return tsk;
    }



    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void importFile(String importFileName, TaskNodeLog tsk, String customerDir) {
        logger.info("Lese Customer-Import Datei '{}'", importFileName);
        File importFile = new File(customerDir, importFileName);

        Collection<Customer> importedCustomers;
        // parse xml file into list of entities
        try {
            String f = FileUtils.readFileToString(importFile, ENCODING).replaceAll(">\\s*<", "><");
            InputSource is = new InputSource(new ByteArrayInputStream(f.getBytes(ENCODING)));

            // XMLReader reader = XMLReaderFactory.createXMLReader();
            XMLReader reader = SAXParserFactory.newDefaultInstance().newSAXParser().getXMLReader();

            CustomerHandler handler = new CustomerHandler();

            // Register event handler
            reader.setContentHandler(handler);

            // Parse document
            reader.parse(is);

            importedCustomers = handler.getCustomers();
        }
        catch (Exception e) {
            // add error to response and continue with next file
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
            return;
        }


        Map<String, Customer> existingCustomerMap = CollectionUtil.convertCollectionToMap(customerManager.findAll(), Customer::getCode);
        Map<String, VerticalSector> sectorMap = CollectionUtil.convertCollectionToMap(verticalSectorManager.findAll(),
                v -> v.getCode().toUpperCase());
        Map<String, Country> countryMap = Country.asMap(countryManager.findAll());

        VerticalSector defaultVerticalSector = sectorMap.get(UNDEF_SECTOR);

        List<String> errorList = new ArrayList<>();

        for (Customer customer : importedCustomers) {
            try {
                // Default VerticalSector verwenden, wenn keiner angegeben ist,
                // prüfen, ob der angegebene Sektor existiert. Falls nicht, Import abbrechen.
                VerticalSector vs;
                if (customer.getVerticalSector() == null || customer.getVerticalSector().getCode().isEmpty()) {
                    vs = defaultVerticalSector;
                }
                else {
                    vs = sectorMap.get(customer.getVerticalSector().getCode().toUpperCase());
                }

                if (vs == null) {
                    throw new RuntimeException(String.format("Unknown vertical sector '%s'", customer.getVerticalSector().getCode()));
                }
                customer.setVerticalSector(vs);

                if (!existingCustomerMap.containsKey(customer.getCode())) {
                    // create new entity
                    Customer newCustomer = new Customer();

                    newCustomer.setCode(customer.getCode());
                    newCustomer.setCountry(countryMap.get(customer.getCountry().getCode()));
                    newCustomer.setVerticalSector(vs);
                    newCustomer.setName(customer.getName());
                    newCustomer.setShortText(customer.getShortText());
                    newCustomer.setZipCode(customer.getZipCode());
                    newCustomer.setStreet(customer.getStreet());
                    newCustomer.setCity(customer.getCity());

                    newCustomer = customerManager.persist(newCustomer, true, false, false);
                    existingCustomerMap.put(newCustomer.getCode(), newCustomer);
                }
                else {
                    // update existing entity
                    Customer existingCustomer = existingCustomerMap.get(customer.getCode());

                    existingCustomer.setVerticalSector(vs);
                    existingCustomer.setZipCode(customer.getZipCode());
                    existingCustomer.setStreet(customer.getStreet());
                    existingCustomer.setCity(customer.getCity());
                    existingCustomer.setCountry(countryMap.get(customer.getCountry().getCode()));
                    existingCustomer.setShortText(customer.getShortText());
                }
            }
            catch (Throwable t) {
                errorList.add(ExceptionUtil.getMoreUsefulExceptionMessage(t));
            }
        } // end for customers


        if (errorList.isEmpty()) {
            tsk.addSubTask(new FileImportSuccessfulLog(importFileName, importedCustomers.size()));
        }
        else {
            tsk.addSubTask(new FileImportProcessedWithErrors(importFileName, errorList, importedCustomers.size()));
        }

        importFile.delete();
    }

}
