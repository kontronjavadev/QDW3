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



    public String getMailSenderAddress() {
        return mailSenderAddress;
    }

    public void setMailSenderAddress(String mailSenderAddress) {
        this.mailSenderAddress = mailSenderAddress;
    }

    public String getMailSenderName() {
        return mailSenderName;
    }

    public void setMailSenderName(String mailSenderName) {
        this.mailSenderName = mailSenderName;
    }


    public String getMailRecipientAdmin() {
        return mailRecipientAdmin;
    }

    public String getMailRecipientImportJournal() {
        return mailRecipientImportJournal;
    }


    public String getUrlExternal() {
        return this.urlExternal;
    }

    public String getUrlRelativeProduction() {
        return this.urlRelativeProduction;
    }

    public String getUrlRelativeQuote() {
        return this.urlRelativeQuote;
    }

    public String getUrlRelativeGp() {
        return this.urlRelativeGp;
    }

}
