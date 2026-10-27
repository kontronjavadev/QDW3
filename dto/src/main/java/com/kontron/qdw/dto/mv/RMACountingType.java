package com.kontron.qdw.dto.mv;

/**
 * Enumeration for evaluating serial number, that are not shipped to customer, but processed internally
 */
public enum RMACountingType {
    CLOSED_BY_ARRIVAL_DATE, CLOSED_BY_SHIPMENT_DATE, ALL_BY_ARRIVAL_DATE
}
