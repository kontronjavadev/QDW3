package com.kontron.qdw.ui;

import org.slf4j.*;
import com.kontron.qdw.boundary.base.*;
import jakarta.security.enterprise.*;
import jakarta.security.enterprise.authentication.mechanism.http.*;
import jakarta.faces.context.*;
import jakarta.enterprise.context.*;
import java.net.*;
import java.lang.invoke.*;
import jakarta.faces.event.*;
import static com.kontron.qdw.ui.TranslationKeys.*;
import net.sourceforge.jbizmo.commons.webclient.primefaces.util.*;
import jakarta.servlet.http.*;
import com.kontron.qdw.dto.base.*;
import java.util.*;
import jakarta.faces.application.*;
import jakarta.annotation.PostConstruct;
import net.sourceforge.jbizmo.commons.crypto.*;
import jakarta.inject.*;
import jakarta.security.enterprise.credential.*;
import java.nio.charset.*;
import java.io.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Named("userSession")
@SessionScoped
public class UserSession implements Serializable {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String DEFAULT_BUNDLE_NAME = "translation";
    @Generated
    public static final String USER_NAME_COOKIE = "userName";
    @Generated
    public static final String DATE_FORMAT_COOKIE = "dateFormat";
    @Generated
    public static final String DATE_TIME_FORMAT_COOKIE = "dateTimeFormat";
    @Generated
    public static final String NUMBER_FORMAT_COOKIE = "numberFormat";
    @Generated
    public static final String TIMEZONE_COOKIE = "timeZone";
    @Generated
    public static final String START_PAGE = "/view/index.jsf?faces-redirect=true";
    @Generated
    public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";
    @Generated
    public static final String ROLE_READONLY = "READONLY";
    @Generated
    public static final String ROLE_SUPERUSER = "SUPERUSER";
    @Generated
    public static final String ROLE_USER_ADMINISTRATOR = "USER_ADMINISTRATOR";
    @Generated
    private static final Map<String, Locale> supportedLocales;
    @Generated
    private final transient UserBoundaryService userService;

    static {
        supportedLocales = new LinkedHashMap<>();
        supportedLocales.put("EN", Locale.ENGLISH);
    }

    @Generated
    private ApplicationLogOnDTO principal;
    @Generated
    private String dateStyle = "medium";
    @Generated
    private String dateFormat = "dd.MM.yyyy";
    @Generated
    private String dateTimeFormat = "dd.MM.yyyy HH:mm:ss";
    @Generated
    private String numberFormat = "###,###,##0.0000";
    @Generated
    private String localeCode = "EN";
    @Generated
    private String oldPassword = "";
    @Generated
    private String newPassword = "";
    @Generated
    private String confirmedPassword = "";
    @Generated
    private String timeZone = TimeZone.getDefault().getID();
    @Generated
    private Locale locale = Locale.ENGLISH;
    @Generated
    private final LinkedList<String> history = new LinkedList<>();
    @Generated
    private final transient ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, locale);
    @Generated
    private final transient SecurityContext securityContext;
    private final transient PasswordResetBoundaryService passwordResetService;

    /**
     * Default constructor
     */
    @Generated
    public UserSession() {
        userService = null;
        securityContext = null;
        passwordResetService = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param userService
     * @param securityContext
     */
    @Inject
    @Generated
    public UserSession(UserBoundaryService userService, SecurityContext securityContext, PasswordResetBoundaryService passwordResetService) {
        this.userService = userService;
        this.securityContext = securityContext;
        this.passwordResetService = passwordResetService;
    }

    /**
     * Perform login operation
     */
    @Generated
    public void login() {
        logger.debug("Login user '{}'", principal.getName());

        final var credential = new UsernamePasswordCredential(principal.getName(), new Password(principal.getPassword()));
        final var resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        final var req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            final String enteredPassword = principal.getPassword();
            final String encryptedPassword = HashGenerator.encryptSHA256(enteredPassword);

            // Verify if user exists
            principal = userService.logOn(principal.getName(), encryptedPassword);

            // Due to security reasons we don't want to carry the password along the hole session!
            principal.setPassword(null);

            final AuthenticationStatus status = securityContext.authenticate(req, resp, AuthenticationParameters.withParams().credential(credential));

            if (!status.equals(AuthenticationStatus.SEND_CONTINUE) && !status.equals(AuthenticationStatus.SUCCESS)) {
                logger.info("Login of user '{}' failed! Status: {}", principal.getName(), status);

                MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, TranslationKeys.LOG_ON_FAILED);
                return;
            }

            // Save entered user name in cookie
            final var userNameCookie = new Cookie(USER_NAME_COOKIE, URLEncoder.encode(principal.getName(), StandardCharsets.UTF_8));
            userNameCookie.setPath(req.getContextPath());

            resp.addCookie(userNameCookie);
        }
        catch (final Exception e) {
            logger.error("Error while performing login of user '{}'!", principal.getName(), e);
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, LOG_ON_FAILED, e);
        }
    }

    /**
     * Reset password
     * @return the navigation target
     */
    public void resetPassword() {
        try {
            logger.debug("Perform reset password operation for '{}'", principal.getName());

            UserSearchDTO userByAccountOrEmail = userService.findByAccountOrEmail(principal.getName());                
            if (userByAccountOrEmail == null) {
                MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, OPERATION_RESETPWUSER);
                return;
            }

            PasswordResetDTO passwordResetDTO = new PasswordResetDTO();
            passwordResetDTO.setUuid(UUID.randomUUID());
            passwordResetDTO.setUserId(userByAccountOrEmail.getId());
            passwordResetService.savePasswordResetAndMail(passwordResetDTO);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_INFO, OPERATION_RESETPWUSER);
        }
        catch (final Exception e) {
            logger.error("Error while performing reset password operation!", e);
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_RESETPW_FAIL, e);
        }
    }

    /**
     * @return the current password
     */
    @Generated
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword
     */
    @Generated
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the new password
     */
    @Generated
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword
     */
    @Generated
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the re-entered password
     */
    @Generated
    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    /**
     * @param confirmedPassword
     */
    @Generated
    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    /**
     * @return the time zone
     */
    @Generated
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone
     */
    @Generated
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return the user object
     */
    @Generated
    public ApplicationLogOnDTO getPrincipal() {
        return principal;
    }

    /**
     * @param principal
     */
    @Generated
    public void setPrincipal(ApplicationLogOnDTO principal) {
        this.principal = principal;
    }

    /**
     * @return the date style
     */
    @Generated
    public String getDateStyle() {
        return dateStyle;
    }

    /**
     * @param dateStyle
     */
    @Generated
    public void setDateStyle(String dateStyle) {
        this.dateStyle = dateStyle;
    }

    /**
     * @return the date format
     */
    @Generated
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * @param dateFormat
     */
    @Generated
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @return the date time format
     */
    @Generated
    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    /**
     * @param dateTimeFormat
     */
    @Generated
    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    /**
     * @return the number format
     */
    @Generated
    public String getNumberFormat() {
        return numberFormat;
    }

    /**
     * @param numberFormat
     */
    @Generated
    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    /**
     * @return the locale code
     */
    @Generated
    public String getLocaleCode() {
        return localeCode;
    }

    /**
     * @param localeCode
     */
    @Generated
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    /**
     * @return the locale
     */
    @Generated
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale
     */
    @Generated
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return all supported locales
     */
    @Generated
    public Map<String, Locale> getSupportedLocales() {
        return supportedLocales;
    }

    /**
     * @return all available time zones
     */
    @Generated
    public String[] getTimeZones() {
        return TimeZone.getAvailableIDs();
    }

    /**
     * Initialize session and try to read user settings from cookies
     */
    @Generated
    @PostConstruct
    public void init() {
        logger.debug("Initialize user session");

        final var req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        final Cookie[] cookies = req.getCookies();
        principal = new ApplicationLogOnDTO();

        if (cookies == null)
            return;

        for (final Cookie cookie : cookies) {
            String cookieValue = null;

            if (cookie.getValue() == null || cookie.getValue().isEmpty())
                continue;

            cookieValue = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);

            if (cookie.getName().equals(USER_NAME_COOKIE))
                principal.setName(cookieValue);
            else if (cookie.getName().equals(DATE_FORMAT_COOKIE))
                dateFormat = cookieValue;
            else if (cookie.getName().equals(DATE_TIME_FORMAT_COOKIE))
                dateTimeFormat = cookieValue;
            else if (cookie.getName().equals(NUMBER_FORMAT_COOKIE))
                numberFormat = cookieValue;
            else if (cookie.getName().equals(TIMEZONE_COOKIE))
                timeZone = cookieValue;
        }
    }

    /**
     * Change event if user selects different locale
     * @param e
     */
    @Generated
    public void onLocaleChanged(ValueChangeEvent e) {
        final String newLocaleValue = e.getNewValue().toString().toUpperCase();

        if (supportedLocales.containsKey(newLocaleValue)) {
            locale = supportedLocales.get(newLocaleValue);
            localeCode = newLocaleValue;

            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        }
    }

    /**
     * @return the navigation target
     */
    @Generated
    public String logout() {
        final var req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        req.getSession().invalidate();

        return START_PAGE;
    }

    /**
     * @param roles contains all roles delimited by semicolon
     * @return true if user is supplied with one of given roles
     */
    @Generated
    public boolean checkAuthorizationString(String roles) {
        if (roles == null || roles.isEmpty())
            return false;

        final String[] roleList = roles.split(";");

        return checkAuthorization(false, roleList);
    }

    /**
     * @param sendError
     * @param roleList
     * @return true if user is supplied with one of given roles
     */
    @Generated
    public boolean checkAuthorization(boolean sendError, String... roleList) {
        if (roleList != null)
            for (final String appRole : roleList)
                for (final RoleListDTO role : principal.getRoles())
                    if (role.getName().equals(appRole))
                        return true;

        if (!sendError)
            return false;

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            final String errorMessage = bundle.getString(MSG_ACCESS_DENIED);

            facesContext.getExternalContext().responseSendError(HttpServletResponse.SC_FORBIDDEN, errorMessage);
        }
        catch (final Exception e) {
            logger.error("Failed to send error code!", e);
        }

        facesContext.responseComplete();
        return false;
    }

    /**
     * Method to change user password
     * @return the navigation target
     */
    @Generated
    public String changePassword() {
        if (!newPassword.equals(confirmedPassword)) {
            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_WARN, MSG_PASSWORDS_NOT_EQUAL);
            return null;
        }

        try {
            userService.changePassword(principal.getId(), oldPassword, newPassword, confirmedPassword);

            return START_PAGE;
        }
        catch (final Exception e) {
            logger.error("Error while saving new password!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_WARN, OPERATION_SAVE_FAIL, e);
            return null;
        }
    }

    /**
     * Save last visited URL in history
     * @param navigationCase
     */
    @Generated
    public void setLastPage(String navigationCase) {
        history.push(navigationCase);

        if (history.size() > 20)
            history.pollLast();
    }

    /**
     * @return the last entry from history list. If list is empty an empty String will be returned.
     */
    @Generated
    public String getLastPage() {
        if (!history.isEmpty())
            return history.pop();

        return "";
    }

    /**
     * @param currentPageURL the relative URL of the current page
     * @param targetURL the relative URL of the page to redirect to
     * @return true if the method has redirected the request to the given target URL
     */
    @Generated
    public boolean redirectTo(String currentPageURL, String targetURL) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final String contextPath = context.getExternalContext().getRequestContextPath();

        try {
            if (targetURL != null && !targetURL.isEmpty()) {
                setLastPage(currentPageURL);

                context.getExternalContext().redirect(contextPath + targetURL);

                logger.debug("Redirect to page '{}'", targetURL);

                return true;
            }
        }
        catch (final Exception e) {
            logger.error("Could not redirect to URL '{}'!", targetURL, e);
        }

        return false;
    }

    /**
     * Save user settings in respective cookies
     * @return the navigation target
     */
    @Generated
    public String saveSettings() {
        final var resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        final var req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            final var dateFormatCookie = new Cookie(DATE_FORMAT_COOKIE, URLEncoder.encode(dateFormat, StandardCharsets.UTF_8));
            dateFormatCookie.setPath(req.getContextPath());

            final var dateTimeFormatCookie = new Cookie(DATE_TIME_FORMAT_COOKIE, URLEncoder.encode(dateTimeFormat, StandardCharsets.UTF_8));
            dateTimeFormatCookie.setPath(req.getContextPath());

            final var numberFormatCookie = new Cookie(NUMBER_FORMAT_COOKIE, URLEncoder.encode(numberFormat, StandardCharsets.UTF_8));
            numberFormatCookie.setPath(req.getContextPath());

            final var timezoneCookie = new Cookie(TIMEZONE_COOKIE, URLEncoder.encode(timeZone, StandardCharsets.UTF_8));
            timezoneCookie.setPath(req.getContextPath());

            resp.addCookie(dateFormatCookie);
            resp.addCookie(dateTimeFormatCookie);
            resp.addCookie(numberFormatCookie);
            resp.addCookie(timezoneCookie);
        }
        catch (final Exception e) {
            logger.error("Error while reading format preferences from cookies!", e);

            MessageUtil.sendFacesMessage(bundle, FacesMessage.SEVERITY_ERROR, OPERATION_SAVE_FAIL, e);
            return null;
        }

        return START_PAGE;
    }

}