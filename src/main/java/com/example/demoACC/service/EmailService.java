package com.example.demoACC.service;

import java.util.regex.*;
import java.util.*;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

    // Method to extract email addresses from a given text
    public List<String> extractEmailAddresses(String text) {
        // Regular expression to match email addresses
        Pattern patternTemplateForEmailFinder = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");

        // Matcher to find email addresses in the text
        Matcher matcherAgainstTheSampleText = patternTemplateForEmailFinder.matcher(text);

        // List to store found email addresses
        List<String> emailListAddedFromSText = new ArrayList<>();

        // Find and add all email addresses to the list
        while (matcherAgainstTheSampleText.find()) {
            emailListAddedFromSText.add(matcherAgainstTheSampleText.group());
        }
        return emailListAddedFromSText;
    }
}
