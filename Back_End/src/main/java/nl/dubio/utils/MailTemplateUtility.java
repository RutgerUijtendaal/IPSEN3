package nl.dubio.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MailTemplateUtility {

    private static final String UNREGISTER_LINK = "http://localhost:4200/uitschrijven/";

    private static final String NAME_REPLACER = "REPLACENAMEHERE";
    private static final String UNREGISTER_REPLACER = "REPLACEUNREGISTERHERE";

    public static String getWelcomeMail(String name, String unregisterToken) {
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
                    line = line.replace(UNREGISTER_REPLACER, UNREGISTER_LINK + unregisterToken);
                }
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
