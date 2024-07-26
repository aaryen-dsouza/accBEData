package com.example.demoACC.service;

import com.example.demoACC.helper.DateHelper;
import com.example.demoACC.helper.PhoneNumberHelper;
import com.example.demoACC.helper.UrlHelper;
import com.example.demoACC.model.EmailValidationResult;
import com.example.demoACC.model.ProcessedData;
import com.example.demoACC.model.ValidPhoneNumber;

import org.springframework.stereotype.Service;
import com.example.demoACC.helper.EmailHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service

public class ProcessService {

    public ProcessedData processFile(String filePath, String domain) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        List<EmailValidationResult> emailResults = EmailHelper.extractAndValidateEmailAddresses(text.toString(), domain);
        List<ValidPhoneNumber> phoneNumbers = PhoneNumberHelper.extractPhoneNumbers(text.toString());
        List<String> urls = UrlHelper.extractUrls(text.toString());
        List<String> dates = DateHelper.extractDates(text.toString());

        return new ProcessedData(emailResults, phoneNumbers, urls, dates);

    } catch (IOException e) {
        // Handle file reading errors
        System.err.println("Error reading file: " + e.getMessage());
        e.printStackTrace(); // For debugging; remove in production
    } catch (Exception e) {
        // Handle other unexpected exceptions
        System.err.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace(); // For debugging; remove in production
    }
    return null;
}
}