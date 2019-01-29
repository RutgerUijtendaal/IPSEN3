package nl.dubio.utils;

import java.security.SecureRandom;
import java.util.Locale;

public class TokenGenerator {

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String digits = "0123456789";

    private static final String alphanum = upper + lower + digits;

    private static SecureRandom random = new SecureRandom();

    public static String getToken() {
        return randomString(32);
    }

    public static String randomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            stringBuilder.append(alphanum.charAt(random.nextInt(alphanum.length())));
        }

        return stringBuilder.toString();
    }
}
