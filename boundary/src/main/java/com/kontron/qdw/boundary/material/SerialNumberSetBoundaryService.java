package com.kontron.qdw.boundary.material;

import java.util.*;
import com.kontron.qdw.repository.material.*;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class SerialNumberSetBoundaryService {
    @Generated
    private final SerialNumberSetRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public SerialNumberSetBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public SerialNumberSetBoundaryService(SerialNumberSetRepository repository) {
        this.repository = repository;
    }

    /**
     * @param code
     * @return the serial number set object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SerialNumberSetDTO findById(String code) {
        // Find persistent object
        final SerialNumberSet serialNumberSet = repository.findById(code, true);

        final var dto = new SerialNumberSetDTO();
        dto.setCode(serialNumberSet.getCode());
        dto.setShortText(serialNumberSet.getShortText());

        return dto;
    }

    /**
     * @return a list of serial number sets objects
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<SerialNumberSetDTO> findAll() {
        final var resultList = new ArrayList<SerialNumberSetDTO>();

        for (final SerialNumberSet serialNumberSet : repository.findAll()) {
            final var dto = new SerialNumberSetDTO();
            dto.setCode(serialNumberSet.getCode());
            dto.setShortText(serialNumberSet.getShortText());

            resultList.add(dto);
        }

        return resultList;
    }

}