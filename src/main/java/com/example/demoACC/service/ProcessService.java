package com.example.demoACC.service;

import com.example.demoACC.helper.DateHelper;
import com.example.demoACC.helper.PhoneNumberHelper;
import com.example.demoACC.helper.UrlHelper;
import com.example.demoACC.model.EmailValidationResult;
import com.example.demoACC.model.ProcessedData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.example.demoACC.helper.EmailHelper;



@Service

public class ProcessService {
    

    public ProcessedData processFile(String filePath, String domain) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }

        List<EmailValidationResult> emailResults = EmailHelper.extractAndValidateEmailAddresses(text.toString(), domain);
        List<String> phoneNumbers = PhoneNumberHelper.extractPhoneNumbers(text.toString());
        List<String> url = UrlHelper.extractUrls(text.toString());
        List<String> date = DateHelper.extractDates(text.toString());

        return new ProcessedData(emailResults, phoneNumbers, url, date);
    }
}
