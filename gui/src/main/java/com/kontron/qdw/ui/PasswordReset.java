package com.kontron.qdw.ui;

import static com.kontron.qdw.ui.TranslationKeys.DIALOG_INIT_FAIL;
import static com.kontron.qdw.ui.TranslationKeys.MSG_PASSWORDS_NOT_EQUAL;
import static com.kontron.qdw.ui.TranslationKeys.OPERATION_SAVE_FAIL;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.PasswordResetBoundaryService;
import com.kontron.qdw.dto.base.PasswordResetDTO;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.MessageUtil;

@Named("passwordReset")
@SessionScoped
public class PasswordReset implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;
    public static final String DEFAULT_BUNDLE_NAME = "translation";
    private Locale locale = Locale.ENGLISH;

    private final transient PasswordResetBoundaryService pwResetService;
    private final transient ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, locale);

    private String uuid;
    private PasswordResetDTO passwordResetDTO;
    private String newPassword = "";
    private String confirmedPassword = "";



    public PasswordReset() {
        pwResetService = null;
    }

    @Inject
    public PasswordReset(PasswordResetBoundaryService pwResetService) {
        this.pwResetService = pwResetService;
    }



    public void initView() {
        logger.debug("Initialize passwort reset dialog");

        try {
            logger.debug("Fetch data for object with uuid '{}'", uuid);

            passwordResetDTO = pwResetService.findByUuid(UUID.fromString(uuid));

            logger.debug("Dialog initialization finished");
        }
        catch (final Exception e) {
            logger.error("Dialog initialization failed!", e);

            final FacesContext facesContext = FacesContext.getCurrentInstance();

            try {
                final String errorMessage = bundle.getString(DIALOG_INIT_FAIL);

                facesContext.getExternalContext().responseSendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
            }
            catch (final Exception ex) {
                logger.error("Failed to send error code!", ex);
            }

            facesContext.responseComplete();
        }
    }

    /**
     * Method to change user password
     * @return the navigation target
     */
    public String changePassword() {
        if (passwordResetDTO == null) {
            return null;
        }
        if (!newPassword.equals(confirmedPassword)) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_WARN, MSG_PASSWORDS_NOT_EQUAL);
            return null;
        }

        try {
            pwResetService.changePassword(passwordResetDTO, newPassword);

            return UserSession.START_PAGE;
        }
        catch (final Exception e) {
            logger.error("Error while saving new password!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_WARN, OPERATION_SAVE_FAIL, e);
            return null;
        }
    }



    public Locale getLocale() {
        return locale;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public PasswordResetDTO getPasswordResetDTO() {
        return passwordResetDTO;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

}
