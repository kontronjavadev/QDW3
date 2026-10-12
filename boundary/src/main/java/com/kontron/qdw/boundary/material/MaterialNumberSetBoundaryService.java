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
public class MaterialNumberSetBoundaryService {
    @Generated
    private final MaterialNumberSetRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterialNumberSetBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterialNumberSetBoundaryService(MaterialNumberSetRepository repository) {
        this.repository = repository;
    }

    /**
     * @return a list of material number sets objects
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialNumberSetDTO> findAll() {
        final var resultList = new ArrayList<MaterialNumberSetDTO>();

        for (final MaterialNumberSet materialNumberSet : repository.findAll()) {
            final var dto = new MaterialNumberSetDTO();
            dto.setId(materialNumberSet.getId());
            dto.setLabel(materialNumberSet.getLabel());

            resultList.add(dto);
        }

        return resultList;
    }

    /**
     * @param id
     * @return the material number set object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialNumberSetDTO findById(long id) {
        // Find persistent object
        final MaterialNumberSet materialNumberSet = repository.findById(id, true);

        final var dto = new MaterialNumberSetDTO();
        dto.setId(materialNumberSet.getId());
        dto.setLabel(materialNumberSet.getLabel());

        return dto;
    }

}