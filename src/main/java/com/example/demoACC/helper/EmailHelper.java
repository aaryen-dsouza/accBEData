package com.example.demoACC.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.example.demoACC.model.EmailValidationResult;

public class EmailHelper {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static List<EmailValidationResult> extractAndValidateEmailAddresses(String text, String domain) {
        // Regular expression to match email addresses
        Pattern emailPattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");

        // Matcher to find email addresses in the text
        Matcher emailMatcher = emailPattern.matcher(text);

        // List to store email validation results
        List<EmailValidationResult> emailResults  = new ArrayList<>();

        while (emailMatcher.find()) {
            String email = emailMatcher.group();
            boolean isValid = email.endsWith("@" + domain + ".com") && Pattern.matches(EMAIL_PATTERN, email);
            emailResults.add(new EmailValidationResult(email, isValid));
        }

        return emailResults;
    }

}
