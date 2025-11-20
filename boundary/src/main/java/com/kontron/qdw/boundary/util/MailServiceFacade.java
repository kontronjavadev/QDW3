package com.kontron.qdw.boundary.util;

import com.kontron.common.mail.MailService;
import com.kontron.common.mail.MailServiceIF;
import com.kontron.common.mail.MailServiceMock;

public final class MailServiceFacade {

    private MailServiceFacade() {
        // utility class with static methods
    }

    private static MailServiceIF getMailServiceInstance() {
        if (Constants.IS_LOCAL_ENVIRONMENT) {
            return getMockMailServiceInstance();
        }
        return getRealMailServiceInstance();
    }

    private static MailService getRealMailServiceInstance() {
        String smtpServerAddress = Constants.getMailSmtpServerAddress();
        String smtpAuthUsername = Constants.getMailSmtpAuthUsername();
        String smtpAuthPassword = Constants.getMailSmtpAuthPassword();

        String mailSenderAddress = Constants.getMailSenderAddress();
        String mailSenderName = Constants.getMailSenderName();

        return new MailService(smtpServerAddress, mailSenderAddress, mailSenderName, smtpAuthUsername, smtpAuthPassword);
    }

    private static MailServiceMock getMockMailServiceInstance() {
        return new MailServiceMock();
    }



    public static void sendMail(String to, String subject, String messageText) {
        // Der MailService von jbizmo generiert für "from" einen Absender aus "fromEmail" und "fromName".
        // Sind diese leer, werden die bei der Instanziierung der MailService angegebenen Daten herangezogen, was jedoch völlig ausreicht.
        getMailServiceInstance().sendMail(to, subject, messageText, null /* fromEmail */, null /* fromName */);
    }

    public static void sendMail(String to, String subject) {
        // Der MailService von jbizmo generiert für "from" einen Absender aus "fromEmail" und "fromName".
        // Sind diese leer, werden die bei der Instanziierung der MailService angegebenen Daten herangezogen, was jedoch völlig ausreicht.
        getMailServiceInstance().sendMail(to, subject, null /* fromEmail */, null /* fromName */);
    }

    public static void sendMail(net.sourceforge.jbizmo.commons.server.mail.MailMessage jbizmoMailMessage) {
        com.kontron.common.mail.MailMessage kontronMailMessage = new com.kontron.common.mail.MailMessage() //
                .withFromEmail(jbizmoMailMessage.getFromEmail()) //
                .withFromName(jbizmoMailMessage.getFromName()) //
                .withTo(jbizmoMailMessage.getTo()) //
                .withCc(jbizmoMailMessage.getCc()) //
                .withSubject(jbizmoMailMessage.getSubject()) //
                .withMessage(jbizmoMailMessage.getMessage()) //
                .withAttachmentContent(jbizmoMailMessage.getAttachmentContent()) //
                .withAttachmentName(jbizmoMailMessage.getAttachmentName()) //
        ;
        getMailServiceInstance().sendMail(kontronMailMessage);
    }

    public static void sendMail(com.kontron.common.mail.MailMessage kontronMailMessage) {
        getMailServiceInstance().sendMail(kontronMailMessage);
    }

}
