package com.alicamlibel.marketexchange.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static int getNumberFromString(String str) throws Exception {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
        Matcher matcher = lastIntPattern.matcher(str);

        if (matcher.find()) {
            String someNumberStr = matcher.group(1);
            return Integer.parseInt(someNumberStr);
        }
        else {
            throw new Exception();
        }
    }
}
