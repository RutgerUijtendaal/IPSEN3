package api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MailTemplateService {

    private static final String NAME_REPLACER = "REPLACENAMEHERE";
    private static final String SIGNOUT_REPLACER = "REPLACESIGNOUTHERE";

    public static String getWelcomeMail(String name) {
        InputStream input = MailTemplateService.class.getResourceAsStream("/welcome.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(NAME_REPLACER)) {
                    line = line.replace(NAME_REPLACER, name);
                }
                if (line.contains(SIGNOUT_REPLACER)) {
                    /*
                    TODO: parents also need to exit the program, PASTESIGNOUTHERE needs to be filled
                     */
                }
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
