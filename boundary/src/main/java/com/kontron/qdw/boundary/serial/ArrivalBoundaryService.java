package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import com.kontron.qdw.dto.base.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class ArrivalBoundaryService {
    @Generated
    private final ArrivalRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public ArrivalBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public ArrivalBoundaryService(ArrivalRepository repository) {
        this.repository = repository;
    }

    /**
     * Find arrival by its ID
     * @param id
     * @return the arrival object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrivalDTO findArrivalById(long id) {
        // Find persistent object
        final Arrival arrival = repository.findById(id, true);

        final var dto = new ArrivalDTO();
        dto.setArrivalDate(arrival.getArrivalDate());
        dto.setOrderNumber(arrival.getOrderNumber());
        dto.setId(arrival.getId());
        dto.setVersion(arrival.getVersion());
        dto.setCreationDate(arrival.getCreationDate());
        dto.setLastUpdate(arrival.getLastUpdate());
        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(arrival.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(arrival.getMaterialRevision().getRevisionNumber());
        dto.setMovementType(new MovementTypeListDTO());
        dto.getMovementType().setCode(arrival.getMovementType().getCode());
        dto.setPlant(new PlantListDTO());
        dto.getPlant().setCode(arrival.getPlant().getCode());
        dto.setSerialObject(new SerialObjectListDTO());
        dto.getSerialObject().setId(arrival.getSerialObject().getId());
        dto.setSupplier(new SupplierListDTO());
        dto.getSupplier().setCode(arrival.getSupplier().getCode());
        dto.getSupplier().setName(arrival.getSupplier().getName());
        dto.setSerialObjectSerialNumber(arrival.getSerialObject().getSerialNumber());
        dto.setMaterialRevisionMaterial(new MaterialListDTO());
        dto.getMaterialRevisionMaterial().setId(arrival.getMaterialRevision().getMaterial().getId());
        dto.getMaterialRevisionMaterial().setMaterialNumber(arrival.getMaterialRevision().getMaterial().getMaterialNumber());
        dto.setMaterialSapNumber(arrival.getMaterialRevision().getMaterial().getSapNumber());
        dto.setMaterialRevisionMaterialType(new MaterialTypeListDTO());
        dto.getMaterialRevisionMaterialType().setCode(arrival.getMaterialRevision().getMaterial().getMaterialType().getCode());
        dto.setMaterialRevisionMaterialClass(new MaterialClassListDTO());
        dto.getMaterialRevisionMaterialClass().setCode(arrival.getMaterialRevision().getMaterial().getMaterialClass().getCode());
        dto.setMaterialShortText(arrival.getMaterialRevision().getMaterial().getShortText());

        return dto;
    }

}