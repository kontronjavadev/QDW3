package com.kontron.qdw.boundary.service.xmlimport;

import java.util.ArrayList;
import java.util.Collection;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kontron.qdw.domain.base.Country;
import com.kontron.qdw.domain.base.Customer;
import com.kontron.qdw.domain.base.VerticalSector;

import jakarta.interceptor.ExcludeDefaultInterceptors;

@ExcludeDefaultInterceptors
public class CustomerHandler extends DefaultHandler {

    private static final String ELEMENT_CUSTOMER = "ZFC_CUSTOMERDATA_DETAIL";
    private static final String ATTRIBUTE_ID = "KUNNR";
    private static final String ATTRIBUTE_NAME_1 = "NAME1";
    private static final String ATTRIBUTE_NAME_2 = "NAME2";
    private static final String ATTRIBUTE_VERTICAL_SECTOR = "BRSCH";
    private static final String ATTRIBUTE_COUNTRY = "LAND1";
    private static final String ATTRIBUTE_STREET = "STRAS";
    private static final String ATTRIBUTE_ZIP_CODE = "PSTLZ";
    private static final String ATTRIBUTE_CITY = "ORT01";

    private String currentElement;
    private Customer customer = new Customer();
    private Collection<Customer> customers;
    private String name1;
    private String name2;
    private String street;
    private String city;
    private String id = "";
    private String zipCode;

    /**
     * This function replaces specific characters
     * 
     * @param s string with characters to replace
     * @return a new string without special characters
     */
    public String replaceSpecialCharacters(String s) {
        StringBuilder b = new StringBuilder("");

        s = s.replace("ä", "ae");
        s = s.replace("ö", "ue");
        s = s.replace("ü", "oe");
        s = s.replace("Ä", "Ae");
        s = s.replace("Ö", "Ue");
        s = s.replace("Ü", "Oe");
        s = s.replace("ß", "ss");

        for (int i = 0; i < s.toCharArray().length; i++) {
            int c = s.toCharArray()[i];

            if (c == 195) {
                i++;
                int a = s.toCharArray()[i];

                if (a == 182) {
                    b.append("oe");
                }
                else if (a == 150) {
                    b.append("Oe");
                }
                else if (a == 188) {
                    b.append("ue");
                }
                else if (a == 338) {
                    b.append("ue");
                }
                else if (a == 156) {
                    b.append("Ue");
                }
                else if (a == 164) {
                    b.append("ae");
                }
                else if (a == 8364) {
                    b.append("ae");
                }
                else if (a == 132) {
                    b.append("Ae");
                }
                else if (a == 159) {
                    b.append("ss");
                }
                else {
                    b.append((char) c);
                    b.append((char) a);
                }
            }
            else {
                b.append((char) c);
            }
        }

        return b.toString();
    }

    @Override
    public void startDocument() throws SAXException {
        customers = new ArrayList<Customer>();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElement = qName;

        if (currentElement.equals(ELEMENT_CUSTOMER)) {
            customer.setCode(Integer.toString(Integer.parseInt(id)));
            customer.setName(name1 + " (" + id + ")");
            customer.setShortText(name2);
            customer.setCity(city);
            customer.setStreet(street);
            customer.setZipCode(zipCode);

            customers.add(customer);

            customer = null;
        }
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
        currentElement = name;

        if (currentElement.equals(ELEMENT_CUSTOMER)) {
            Country c = new Country("");

            // Create new customer object
            customer = new Customer();
            customer.setStreet("");
            customer.setZipCode("");
            customer.setCity("");
            customer.setCountry(c);

            name1 = "";
            name2 = "";
            id = "";
            street = "";
            city = "";
            zipCode = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch);
        value = value.substring(start, start + length);

        if (currentElement.equals(ATTRIBUTE_ID)) {
            id = id + value;
        }
        else if (currentElement.equals(ATTRIBUTE_NAME_1)) {
            name1 = name1 + value.trim();
        }
        else if (currentElement.equals(ATTRIBUTE_NAME_2)) {
            name2 = name2 + value.trim();
        }
        else if (currentElement.equals(ATTRIBUTE_STREET)) {
            street = street + value;
        }
        else if (currentElement.equals(ATTRIBUTE_ZIP_CODE)) {
            zipCode = replaceSpecialCharacters(value);
        }
        else if (currentElement.equals(ATTRIBUTE_CITY)) {
            city = city + value;
        }
        else if (currentElement.equals(ATTRIBUTE_VERTICAL_SECTOR)) {
            VerticalSector v = new VerticalSector();
            v.setCode(replaceSpecialCharacters(value));
            customer.setVerticalSector(v);
        }
        else if (currentElement.equals(ATTRIBUTE_COUNTRY)) {
            Country country = new Country();
            country.setCode(value);
            customer.setCountry(country);
        }
    }

    /**
     * Returns all customers in xml file
     * 
     * @return a collection of customer objects.
     */
    public Collection<Customer> getCustomers() {
        return customers;
    }

}
