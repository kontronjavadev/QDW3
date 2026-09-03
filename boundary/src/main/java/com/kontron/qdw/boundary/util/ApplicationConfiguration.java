package com.kontron.qdw.boundary.util;

import com.kontron.config.ApplicationProperty;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ApplicationConfiguration {

    @Inject
    @ApplicationProperty("mail.sender.address")
    private String mailSenderAddress;

    @Inject
    @ApplicationProperty("mail.sender.name")
    private String mailSenderName;


    @Inject
    @ApplicationProperty("mail.recipient.admin")
    private String mailRecipientAdmin;

    @Inject
    @ApplicationProperty("mail.recipient.logistic")
    private String mailRecipientLogistic;

    @Inject
    @ApplicationProperty("mail.recipient.illegalRatioWarning")
    private String mailRecipientIllegalRatioWarning;

    @Inject
    @ApplicationProperty("mail.recipient.import-journal")
    private String mailRecipientImportJournal;



    @Inject
    @ApplicationProperty("url.external")
    private String urlExternal;

    @Inject
    @ApplicationProperty("url.relative.production")
    private String urlRelativeProduction;

    @Inject
    @ApplicationProperty("url.relative.quote")
    private String urlRelativeQuote;

    @Inject
    @ApplicationProperty("url.relative.gp")
    private String urlRelativeGp;



    @Inject
    @ApplicationProperty("tracebom.sftp.host")
    private String traceBoMSftpHost;

    @Inject
    @ApplicationProperty("tracebom.sftp.auth.user")
    private String traceBoMSftpAuthUser;

    @Inject
    @ApplicationProperty("tracebom.sftp.auth.password")
    private String traceBoMSftpAuthPassword;



    @Inject
    @ApplicationProperty("tracebom.local.folder")
    private String traceBoMLocalFolder;

    @Inject
    @ApplicationProperty("tracebom.backup.folder")
    private String traceBoMBackupFolder;

    @Inject
    @ApplicationProperty("tracebom.logistic.folder")
    private String traceBoMLogisticFolder;

    @Inject
    @ApplicationProperty("tracebom.error.folder")
    private String traceBoMErrorFolder;



    public String getMailSenderAddress() {
        return mailSenderAddress;
    }

    public String getMailSenderName() {
        return mailSenderName;
    }

    public String getMailRecipientAdmin() {
        return mailRecipientAdmin;
    }

    public String getMailRecipientLogistic() {
        return mailRecipientLogistic;
    }

    public String getMailRecipientIllegalRatioWarning() {
        return mailRecipientIllegalRatioWarning;
    }

    public String getMailRecipientImportJournal() {
        return mailRecipientImportJournal;
    }



    public String getUrlExternal() {
        return urlExternal;
    }

    public String getUrlRelativeProduction() {
        return urlRelativeProduction;
    }

    public String getUrlRelativeQuote() {
        return urlRelativeQuote;
    }

    public String getUrlRelativeGp() {
        return urlRelativeGp;
    }



    public String getTraceBoMSftpHost() {
        return traceBoMSftpHost;
    }

    public String getTraceBoMSftpAuthUser() {
        return traceBoMSftpAuthUser;
    }

    public String getTraceBoMSftpAuthPassword() {
        return traceBoMSftpAuthPassword;
    }



    public String getTraceBoMLocalFolder() {
        return traceBoMLocalFolder;
    }

    public String getTraceBoMBackupFolder() {
        return traceBoMBackupFolder;
    }

    public String getTraceBoMLogisticFolder() {
        return traceBoMLogisticFolder;
    }

    public String getTraceBoMErrorFolder() {
        return traceBoMErrorFolder;
    }

}
