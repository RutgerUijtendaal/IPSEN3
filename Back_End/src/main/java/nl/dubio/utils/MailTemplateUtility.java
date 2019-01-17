package nl.dubio.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MailTemplateUtility {

    private static final String UNREGISTER_PATH = "uitschrijven/";

    private static final String NAME_REPLACER = "REPLACENAMEHERE";
    private static final String PARTNER_NAME_REPLACER = "REPLACEPARTNERNAME";

    private static final String ANSWER_REPLACER = "REPLACEANSWER";
    private static final String PARTNER_ANSWER_REPLACER = "REPLACEANSWERPARTNER";

    private static final String UNREGISTER_REPLACER = "REPLACEUNREGISTERHERE";

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

    public static String getFeedbackMail(String websiteUrl, String name, String partnerName, String answer, String partnerAnswer, String unregisterToken) {
        InputStream input = MailTemplateUtility.class.getResourceAsStream("/feedback.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(NAME_REPLACER)) {
                    line = line.replace(NAME_REPLACER, name);
                }
                if (line.contains(PARTNER_ANSWER_REPLACER)) {
                    line = line.replace(PARTNER_ANSWER_REPLACER, partnerAnswer);
                }
                if (line.contains(ANSWER_REPLACER)) {
                    line = line.replace(ANSWER_REPLACER, answer);
                }
                if (line.contains(PARTNER_ANSWER_REPLACER)) {
                    line = line.replace(PARTNER_ANSWER_REPLACER, partnerAnswer);
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
