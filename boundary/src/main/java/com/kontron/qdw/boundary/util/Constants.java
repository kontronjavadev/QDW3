package com.kontron.qdw.boundary.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.kontron.config.ExternalCoreConfiguration;
import com.kontron.util.text.StringUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;

public final class Constants {

    /** Im app server sollte ein SysProp "env" mit "LOCAL", "TEST" oder "PROP" erstellt werden */
    private static final String ENV_SYSPROP = "environment";
    // private static final String ENVERS_SYSPROP = "envers";

    private static ExternalCoreConfiguration externalCoreConfiguration;
    private static ApplicationConfiguration applicationConfiguration;

    public void inject(@Observes @Initialized(ApplicationScoped.class) Object x, ExternalCoreConfiguration externalCoreConfiguration) {
        Constants.externalCoreConfiguration = externalCoreConfiguration;
    }

    public void inject(@Observes @Initialized(ApplicationScoped.class) Object x, ApplicationConfiguration applicationConfiguration) {
        Constants.applicationConfiguration = applicationConfiguration;
    }



    // environment
    public static final String ENVIRONMENT = System.getProperty(ENV_SYSPROP, "LOCAL");
    public static final boolean IS_LOCAL_ENVIRONMENT = "LOCAL".equalsIgnoreCase(ENVIRONMENT);
    public static final boolean IS_TEST_ENVIRONMENT = "TEST".equalsIgnoreCase(ENVIRONMENT);
    public static final boolean IS_PROD_ENVIRONMENT = "PROD".equalsIgnoreCase(ENVIRONMENT);

    // public static final String ENVERS = System.getProperty(ENVERS_SYSPROP, "V2" /* default */);

    public static final String APP_ENV = "QDW 3 " + (IS_PROD_ENVIRONMENT ? "" : "(" + ENVIRONMENT + ") ");
    public static final String APP_SERVER = "https://"
            + (IS_PROD_ENVIRONMENT
                    ? "gpe.kontron.com"
                    : (IS_TEST_ENVIRONMENT
                            ? "sdemucap10.kontron.local"
                            : "localhost"))
            + ":8443/qdw/";



    // Global constants for sending emails
    public static final String MAIL_SMTP_HOST = "10.8.32.99";
    public static final String MAIL_SMTP_AUTH_USER = "kontron\\s_GPE";
    public static final String MAIL_SMTP_AUTH_PASSWORD = "V7dx92Axp5";

    public static final String MAIL_SENDER_ADDRESS = "gpe@kontron.com";
    public static final String MAIL_SENDER_NAME = "GPE";

    private static final String MAIL_RECIPIENT = "javainhouseapps@kontron.com";
    private static final String IMPORT_JOURNAL_EMAIL = "GL.GO.GPERevsion@kontron.com";

    public static final String MAIL_COMPANY_NAME = "KONTRON";
    public static final String EXCELSHEET_COMPANY_NAME = "Kontron";



    // Global constants for LDAP
    private static final String LDAP_AD_DC_ADDRESS = "kontron.local";
    private static final String LDAP_AD_AUTH_USERNAME = "ad-reader";
    private static final String LDAP_AD_AUTH_PASSWORD = "Wertz789";
    private static final String LDAP_AD_CONTEXT = "kontron.local";



    // Properties für externe URLs, wie sie in den Benachrichtigungsmails verschickt werden
    private static final String GPE_URL_EXTERNAL = "https://gpe.kontron.com:8443/gpesupplier";
    private static final String GPE_URL_RELATIVE_PROD = "/dialog/ViewProductionInfoSetDialog.jsf?selectedObjectId=";
    private static final String GPE_URL_RELATIVE_QUOTE = "/dialog/ViewQuoteSessionDialog.jsf?selectedObjectId=";
    private static final String GPE_URL_RELATIVE_GP = "/tree/GPSessionTreeView.jsf?sessionNo=";



    // Properties aus DB für scheduler
    public static final String PROPERTY_NAME_HOST = "HOSTNAME";
    public static final String PROPERTY_NAME_CONF_DIR = "CONFDIR";
    public static final String PROPERTY_NAME_DOWNLOAD_IMPORT_FILES = "DOWNLOAD_IMPORT_FILES";
    public static final String PROPERTY_NAME_ARCHIVE_IMPORT_FILES = "ARCHIVE_IMPORT_FILES";



    // sonstige Konstanten
    public static final String MPN_DEF_DESC_TEXT = "Automatically created by quote session!";
    public static final String MPN_DEF_MANUFACTURER = "500097";

    public static final String NPI_MATERIAL_NO_PREFIX = "NPI-";

    public static final String DEFAULT_UNIT_OF_TRADING = "PC";

    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    public static final Charset CHARSET_ASCII = StandardCharsets.US_ASCII;

    public static final String UTF_8 = CHARSET_UTF_8.name();
    public static final String ASCII = CHARSET_ASCII.name();

    /**
     * Ein User_Administrator darf keine Testaccounts anlegen oder ändern (etwa aktivieren)
     */
    public static final String[] MAILDOMAIN_BLACK_LIST = { "web", "gmx", "freenet", "t-online", "gmail", "googlemail", "hot-mail", "yahoo", "arcor",
            "1und1", "alice-dsl", "o2online" };



    public static String getMailSmtpServerAddress() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getExternalCoreConfiguration(),
                ExternalCoreConfiguration::getMailSmtpServerAddress, Constants.MAIL_SMTP_HOST);
    }

    public static String getMailSmtpAuthUsername() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getExternalCoreConfiguration(),
                ExternalCoreConfiguration::getMailSmtpAuthUsername, Constants.MAIL_SMTP_AUTH_USER);
    }

    public static String getMailSmtpAuthPassword() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getExternalCoreConfiguration(),
                ExternalCoreConfiguration::getMailSmtpAuthPassword, Constants.MAIL_SMTP_AUTH_PASSWORD);
    }

    public static String getMailSenderAddress() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getMailSenderAddress, Constants.MAIL_SENDER_ADDRESS);
    }

    public static String getMailSenderName() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getMailSenderName, Constants.MAIL_SENDER_NAME);
    }



    public static String getMailRecipient() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getMailRecipientAdmin, Constants.MAIL_RECIPIENT);
    }

    public static String getImportJournalMailRecipient() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getMailRecipientImportJournal, Constants.IMPORT_JOURNAL_EMAIL);
    }



    public static String getLdapAdDcAddress() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getExternalCoreConfiguration(),
                ExternalCoreConfiguration::getLdapAdDcAddress, Constants.LDAP_AD_DC_ADDRESS);
    }

    public static String getLdapAdAuthUsername() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getExternalCoreConfiguration(),
                ExternalCoreConfiguration::getLdapAdAuthUsername, Constants.LDAP_AD_AUTH_USERNAME);
    }

    public static String getLdapAdAuthPassword() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getExternalCoreConfiguration(),
                ExternalCoreConfiguration::getLdapAdAuthPassword, Constants.LDAP_AD_AUTH_PASSWORD);
    }

    public static String getLdapAdContext() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getExternalCoreConfiguration(),
                ExternalCoreConfiguration::getLdapAdContext, Constants.LDAP_AD_CONTEXT);
    }



    public static String getUrlExternal() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getUrlExternal, Constants.GPE_URL_EXTERNAL);
    }

    public static String getUrlRelativeProduction() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getUrlRelativeProduction, Constants.GPE_URL_RELATIVE_PROD);
    }

    public static String getUrlRelativeQuote() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getUrlRelativeQuote, Constants.GPE_URL_RELATIVE_QUOTE);
    }

    public static String getUrlRelativeGp() {
        return StringUtil.getNonEmptyStringFromObjectOrDefault(getApplicationConfiguration(),
                ApplicationConfiguration::getUrlRelativeGp, Constants.GPE_URL_RELATIVE_GP);
    }

    public static String getUrlProduction() {
        return getUrlExternal() + getUrlRelativeProduction();
    }

    public static String getUrlQuote() {
        return getUrlExternal() + getUrlRelativeQuote();
    }

    public static String getUrlGp() {
        return getUrlExternal() + getUrlRelativeGp();
    }



    public static ExternalCoreConfiguration getExternalCoreConfiguration() {
        return externalCoreConfiguration;
    }

    public static ApplicationConfiguration getApplicationConfiguration() {
        return applicationConfiguration;
    }

}
