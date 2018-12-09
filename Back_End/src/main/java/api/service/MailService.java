package api.service;

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
public class MailService {

    private static Properties mailServerProperties;
    private static Session mailSession;
    private final String gmailUsername;
    private final String password;

    private final int MAILSPEED = 5000;

    private List<MimeMessage> messageQueue;

    public MailService(String gmailUsername, String password) {
        this.gmailUsername = gmailUsername;
        this.password = password;
        this.messageQueue = new ArrayList<>();
    }

    public void startMailThread() {
        Runnable mailThread = () -> {
            try {
                while (true) {
                    System.out.println("checking for emails in queue");
                    if (!messageQueue.isEmpty()) {
                        System.out.println("sending mail...");
                        sendMailInQueue(messageQueue.get(0));
                        messageQueue.remove(0);
                    }
                    Thread.sleep(MAILSPEED);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                startMailThread();
            } catch (MessagingException e2) {
                moveMessageToBack();
                startMailThread();
                e2.printStackTrace();
            } catch (Exception e) {
                startMailThread();
                e.printStackTrace();
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
        //Send Mail
        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", gmailUsername, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public void addWelcomeMailToQueue(String to, String parentName) throws MessagingException {
        MimeMessage mimeMessage = generateMessage(to, "Welkom bij Dubio!", MailTemplateService.getWelcomeMail(parentName));
        messageQueue.add(mimeMessage);
    }

    /**
     * Generates the properties of the mailing server
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