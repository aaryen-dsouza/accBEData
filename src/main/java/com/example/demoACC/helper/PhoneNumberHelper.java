package com.example.demoACC.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberHelper {
    private static final String PHONE_REGEX = "\\+?1?[\\s-]?\\(?[2-9][0-9]{2}\\)?[\\s-]?[2-9][0-9]{2}[\\s-]?[0-9]{4}"; //  regex to validate USA and CANADA phone numbers


    public static List<String> extractPhoneNumbers(String text) {
        List<String> phoneNumbers = new ArrayList<>();
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        Matcher phoneMatcher = phonePattern.matcher(text);

        while (phoneMatcher.find()) {
            phoneNumbers.add(phoneMatcher.group().replaceAll("[^0-9]", ""));
        }

        return phoneNumbers;
    }
}
