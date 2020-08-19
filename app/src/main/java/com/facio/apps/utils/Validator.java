package com.facio.apps.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String regex = "^(.+)@(.+)$";

    public static boolean isValidEmail(String emailValue) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailValue);
        return matcher.matches();
    }

    public static boolean isMinimumLength(String value, int length) {
        return value.length() >= length;
    }
}
