package com.kontron.qdw.boundary.base;

import java.util.ArrayList;
import java.util.List;

import com.kontron.qdw.domain.base.User;
import com.kontron.qdw.domain.base.UserProperty;
import com.kontron.qdw.dto.base.UserPropertyDTO;
import com.kontron.qdw.repository.base.UserPropertyRepository;

import jakarta.annotation.Nonnull;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotEmpty;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.search.SearchService;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import net.sourceforge.jbizmo.commons.search.dto.SearchFieldDTO;
import net.sourceforge.jbizmo.commons.search.dto.SearchFieldDataTypeEnum;
import net.sourceforge.jbizmo.commons.search.util.SearchOperatorHelper;

@Stateless
public class UserPropertyBoundaryService {
    @Generated
    private final UserPropertyRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public UserPropertyBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public UserPropertyBoundaryService(UserPropertyRepository repository) {
        this.repository = repository;
    }

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserPropertyDTO findUserProperty(long userId, @Nonnull @NotEmpty String propKey) {
        return findUserProperty(userId, propKey, "");
    }

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserPropertyDTO findUserProperty(long userId, @Nonnull @NotEmpty String propKey, @Nonnull String viewName) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(UserPropertyDTO.SELECT_ID);
        selectTokens.add(UserPropertyDTO.SELECT_PROPKEY);
        selectTokens.add(UserPropertyDTO.SELECT_VIEWNAME);
        selectTokens.add(UserPropertyDTO.SELECT_VALUE);
        selectTokens.add(UserPropertyDTO.SELECT_USERID);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(2);
        searchObj.setFromClause("from UserProperty a");

        SearchFieldDTO userFilter = searchObj.addSearchField(UserPropertyDTO.SELECT_USERID, SearchFieldDataTypeEnum.LONG);
        userFilter.setFilterCriteria(Long.toString(userId));

        SearchFieldDTO propKeyFilter = searchObj.addSearchField(UserPropertyDTO.SELECT_PROPKEY, SearchFieldDataTypeEnum.STRING);
        propKeyFilter.setOperator(SearchOperatorHelper.getOperator(SearchService.OPERATOR_EQUAL));
        propKeyFilter.setFilterCriteria(propKey);

        SearchFieldDTO viewNameFilter = searchObj.addSearchField(UserPropertyDTO.SELECT_VIEWNAME, SearchFieldDataTypeEnum.STRING);
        viewNameFilter.setOperator(SearchOperatorHelper.getOperator(SearchService.OPERATOR_EQUAL));
        viewNameFilter.setFilterCriteria(viewName);


        List<UserPropertyDTO> result = repository.search(searchObj, UserPropertyDTO.class, selectTokens);
        if (result.size() > 1) {
            throw new IllegalStateException("Result is not unique");
        }
        if (result.isEmpty()) {
            return null;
        }
        return result.getFirst();
    }

    /**
     * Save the given user property
     * @param userProperty the user property to be saved
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the saved user property object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserPropertyDTO saveUserProperty(UserPropertyDTO userProperty) {
        boolean createNew = true;
        UserProperty userPropertyToSave = null;

        if (userProperty.getId() != 0) {
            // Find and attach the persistent object
            userPropertyToSave = repository.findById(userProperty.getId());
            createNew = userPropertyToSave == null;
        }

        if (createNew) {
            userPropertyToSave = new UserProperty();
        }

        userPropertyToSave.setPropKey(userProperty.getPropKey() != null ? userProperty.getPropKey().trim() : null);
        userPropertyToSave.setViewName(userProperty.getViewName() != null ? userProperty.getViewName().trim() : null);
        userPropertyToSave.setValue(userProperty.getValue() != null ? userProperty.getValue().trim() : null);
        userPropertyToSave.setUser(repository.getReference(User.class, userProperty.getUserId()));

        if (createNew) {
            // Persist a new object
            userPropertyToSave = repository.persist(userPropertyToSave, true, true);

            userProperty.setId(userPropertyToSave.getId());
        }
        else {
            // Merge the existing object
            repository.merge(userPropertyToSave, true);
        }

        return userProperty;
    }

}
