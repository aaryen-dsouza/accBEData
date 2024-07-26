package com.example.demoACC.service;

import com.example.demoACC.helper.DateHelper;
import com.example.demoACC.helper.PhoneNumberHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.*;

import com.example.demoACC.helper.UrlHelper;
import org.springframework.stereotype.Service;

@Service

public class EmailService {
    // Regular expression for validating an Email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    public List<EmailValidationResult> extractAndValidateEmailAddresses(String text, String domain) {
        // Regular expression to match email addresses
        Pattern emailPattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");

        // Matcher to find email addresses in the text
        Matcher emailMatcher = emailPattern.matcher(text);

        // List to store email validation results
        List<EmailValidationResult> emailResults  = new ArrayList<>();

        while (emailMatcher.find()) {
            String email = emailMatcher.group();
            boolean isValid = email.endsWith("@" + domain + ".com") && Pattern.matches(EMAIL_REGEX, email);
            emailResults.add(new EmailValidationResult(email, isValid));
        }

        return emailResults;
    }

    public ProcessedData processFile(String filePath, String domain) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }

        List<EmailValidationResult> emailResults = extractAndValidateEmailAddresses(text.toString(), domain);
        List<String> phoneNumbers = PhoneNumberHelper.extractPhoneNumbers(text.toString());
        List<String> url = UrlHelper.extractUrls(text.toString());
        List<String> date = DateHelper.extractDates(text.toString());

        return new ProcessedData(emailResults, phoneNumbers, url, date);
    }
}
