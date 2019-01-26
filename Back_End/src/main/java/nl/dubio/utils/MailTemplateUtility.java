package nl.dubio.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MailTemplateUtility {

    private static final String UNREGISTER_PATH = "uitschrijven/";
    private static final String DILEMMA_LINK_PATH = "dilemma/beantwoord/";

    private static final String NAME_REPLACER = "REPLACENAMEHERE";
    private static final String PARTNER_NAME_REPLACER = "REPLACEPARTNERNAMEHERE";
    private static final String DILEMMA_LINK_REPLACER = "REPLACEDILEMMALINKHERE";

    private static final String SUBJECT_REPLACER = "REPLACESUBJECTHERE";
    private static final String ANSWER_REPLACER = "REPLACEANSWERHERE";
    private static final String PARTNER_ANSWER_REPLACER = "REPLACEANSWERPARTNERHERE";

    private static final String FEEDBACK_REPLACER = "REPLACEFEEDBACKHERE";

    private static final String UNREGISTER_REPLACER = "REPLACEUNREGISTERHERE";

    private static final String ADMIN_PASSWORD = "REPLACERANDOMIZEDPASSWORDHERE";

    private static final String PASSWORD_LINK_REPLACER = "REPLACELINKHERE";

    public static String getWelcomeMail(String websiteUrl, String name, String unregisterToken) {
        InputStream input = MailTemplateUtility.class.getResourceAsStream("/welcome.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(NAME_REPLACER)) {
                    line = line.replace(NAME_REPLACER, name);
                }
                if (line.contains(UNREGISTER_REPLACER)) {
                    line = line.replace(UNREGISTER_REPLACER, websiteUrl + UNREGISTER_PATH + unregisterToken);
                }
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String getFeedbackMail(String websiteUrl, String name, String partnerName, String dilemma, String answer, String partnerAnswer, String feedback, String unregisterToken) {
        InputStream input = MailTemplateUtility.class.getResourceAsStream("/feedback.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(NAME_REPLACER)) {
                    line = line.replace(NAME_REPLACER, name);
                }
                if (line.contains(PARTNER_NAME_REPLACER)) {
                    line = line.replace(PARTNER_NAME_REPLACER, partnerName);
                }
                if (line.contains(SUBJECT_REPLACER)) {
                    line = line.replace(SUBJECT_REPLACER, dilemma);
                }
                if (line.contains(ANSWER_REPLACER)) {
                    line = line.replace(ANSWER_REPLACER, answer);
                }
                if (line.contains(PARTNER_ANSWER_REPLACER)) {
                    line = line.replace(PARTNER_ANSWER_REPLACER, partnerAnswer);
                }
                if (line.contains(FEEDBACK_REPLACER)) {
                    line = line.replace(FEEDBACK_REPLACER, feedback);
                }
                if (line.contains(UNREGISTER_REPLACER)) {
                    line = line.replace(UNREGISTER_REPLACER, websiteUrl + UNREGISTER_PATH + unregisterToken);
                }
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String getResetPasswordMail(String name, String link) {
        InputStream input = MailTemplateUtility.class.getResourceAsStream("/password_reset.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(NAME_REPLACER)) {
                    line = line.replace(NAME_REPLACER, name);
                }
                if (line.contains(PASSWORD_LINK_REPLACER)) {
                    line = line.replace(PASSWORD_LINK_REPLACER, link);
                }
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String getNewAdminMail(String password) {
        InputStream input = MailTemplateUtility.class.getResourceAsStream("/new_admin.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(ADMIN_PASSWORD)) {
                    line = line.replace(ADMIN_PASSWORD, password);
                }
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    public static String getDilemmaReadymail(String websiteUrl, String name, String dilemma, String token, String unregisterToken) {
        InputStream input = MailTemplateUtility.class.getResourceAsStream("/dilemma_ready.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(NAME_REPLACER)) {
                    line = line.replace(NAME_REPLACER, name);
                }
                if (line.contains(SUBJECT_REPLACER)) {
                    line = line.replace(SUBJECT_REPLACER, dilemma);
                }
                if (line.contains(DILEMMA_LINK_REPLACER)) {
                    line = line.replace(DILEMMA_LINK_REPLACER, websiteUrl + DILEMMA_LINK_PATH + token);
                }
                if (line.contains(UNREGISTER_REPLACER)) {
                    line = line.replace(UNREGISTER_REPLACER, websiteUrl + UNREGISTER_PATH + unregisterToken);
                }
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
