package nl.dubio.utils;

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
    private final String gmailUsername;
    private final String password;

    private final int MAILSPEED = 5000;

    private List<MimeMessage> messageQueue;

    public MailUtility(String gmailUsername, String password) {
        this.gmailUsername = gmailUsername;
        this.password = password;
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
                        System.out.println(info);
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
        transport.connect("smtp.gmail.com", gmailUsername, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public void addWelcomeMailToQueue(String to, String parentName) throws MessagingException {
        final String subject = "Welkom bij Dubio!";
        MimeMessage mimeMessage = generateMessage(to, subject, MailTemplateUtility.getWelcomeMail(parentName));
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