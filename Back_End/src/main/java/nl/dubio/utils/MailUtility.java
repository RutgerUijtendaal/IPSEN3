package nl.dubio.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Service to send mail
 *
 * @author Stefan de Keijzer
 * @author Jordi Dorren
 */
public class MailUtility {

    private static Properties mailServerProperties;
    private static Session mailSession;
    private final String username;
    private final String password;
    private final String websiteUrl;
    private final String smtpServer;

    private final int MAILSPEED = 2500;

    private List<MimeMessage> messageQueue;

    @JsonCreator
    public MailUtility(@JsonProperty("username") String username,
                       @JsonProperty("password") String password,
                       @JsonProperty("websiteUrl") String websiteUrl,
                       @JsonProperty("smtpServer") String smtpServer) {
        this.username = username;
        this.password = password;
        this.websiteUrl = websiteUrl;
        this.smtpServer = smtpServer;
        this.messageQueue = new ArrayList<>();
        startMailThread();
    }

    private void startMailThread() {
        Runnable mailThread = () -> {
            try {
                while (true) {
                    if (!messageQueue.isEmpty()) {
                        String info = "[+] Sending mail to: '" + messageQueue.get(0).getAllRecipients()[0] +
                                "' with subject: '" + messageQueue.get(0).getSubject() + "'";
                        sendMailInQueue(messageQueue.get(0));
                        messageQueue.remove(0);
                    }
                    Thread.sleep(MAILSPEED);
                }
            } catch (MessagingException e1) {
                moveMessageToBack();
                startMailThread();
                e1.printStackTrace();
            } catch (Exception e2) {
                startMailThread();
                e2.printStackTrace();
            }
        };
        new Thread(mailThread).start();
    }

    private void moveMessageToBack() {
        MimeMessage failedMessage = messageQueue.get(0);
        messageQueue.remove(0);
        messageQueue.add(failedMessage);
    }

    /**
     * Send an email
     * @throws MessagingException thrown when something is wrong
     */
    private void sendMailInQueue(MimeMessage message) throws MessagingException {
        generateProperties();
        mailSession = Session.getDefaultInstance(mailServerProperties, null);
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(smtpServer, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public void addWelcomeMailToQueue(String to, String parentName, String unregisterToken) throws MessagingException {
        final String subject = "Welkom bij Dubio!";
        MimeMessage mimeMessage = generateMessage(to, subject, MailTemplateUtility.getWelcomeMail(websiteUrl, parentName, unregisterToken));
        messageQueue.add(mimeMessage);
    }

    public void addFeedbackMailToQueue(String to, String parentName, String partnerName, String dilemmaName, String answer, String partnerAnswer, String feedback, String unregisterToken) throws MessagingException {
        final String subject = "Uw feedback!";
        MimeMessage mimeMessage = generateMessage(to, subject, MailTemplateUtility.getFeedbackMail(websiteUrl, parentName, partnerName, dilemmaName, answer, partnerAnswer, feedback, unregisterToken));
        messageQueue.add(mimeMessage);
    }

    public void addDilemmaReadyToQueue(String subject, String to, String parentName, String dilemmaName, String parentToken, String unregisterToken) throws MessagingException {
        MimeMessage mimeMessage = generateMessage(to, subject, MailTemplateUtility.getDilemmaReadymail(websiteUrl, parentName, dilemmaName, parentToken, unregisterToken));
        messageQueue.add(mimeMessage);
    }

    public void addResetPasswordToQueue(String to, String name, String token) throws MessagingException {
        final String subject = "Wachtwoord resetten";
        MimeMessage mimeMessage = generateMessage(to, subject, MailTemplateUtility.getResetPasswordMail(name, token));
        messageQueue.add(mimeMessage);
    }

    public void addNewAdminToQueue(String to, String password) throws MessagingException {
        final String subject = "Uw Dubio beheerders wachtwoord";
        MimeMessage mimeMessage = generateMessage(to, subject, MailTemplateUtility.getNewAdminMail(password));
        messageQueue.add(mimeMessage);
    }

    /**
     * Generates the properties of the utils server
     */
    private void generateProperties() {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
    }

    /**
     * Generates a mail
     * @param to email address
     * @param subject subject of the email
     * @param content content of the email
     * @return
     * @throws MessagingException
     */
    private MimeMessage generateMessage(String to, String subject, String content) throws MessagingException {
        MimeMessage mailMessage = new MimeMessage(mailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mailMessage.setSubject(subject);
        mailMessage.setContent(content, "text/html;charset=UTF-8");
        return mailMessage;
    }

}
