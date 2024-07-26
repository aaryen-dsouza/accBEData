package com.example.demoACC.helper;

import com.example.demoACC.model.ValidPhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberHelper {
    private static final String PHONE_REGEX = "\\+?1?[\\s-]?\\(?[2-9][0-9]{2}\\)?[\\s-]?[2-9][0-9]{2}[\\s-]?[0-9]{4}"; // regex to validate USA and CANADA phone numbers

    public static List<ValidPhoneNumber> extractPhoneNumbers(String text) {
        List<ValidPhoneNumber> validPhoneNumbers = new ArrayList<>();
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        Matcher phoneMatcher = phonePattern.matcher(text);

        while (phoneMatcher.find()) {
            String rawNumber = phoneMatcher.group();
            String cleanedNumber = rawNumber.replaceAll("[^0-9]", "");
            boolean isValid = validatePhoneNumber(cleanedNumber); // Add validation logic here
            validPhoneNumbers.add(new ValidPhoneNumber(cleanedNumber, isValid));
        }

        return validPhoneNumbers;
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        // Add additional validation logic if needed
        // For example, checking length or format
        return phoneNumber.length() == 10; // Assuming 10 digits is valid
    }
}
