package com.example.demoACC.service;

import java.util.List;

public class ProcessedData {
    private List<EmailValidationResult> emails;
    private List<String> phoneNumbers;
    private List<String> url;
    private List<String> date;



    public ProcessedData(List<EmailValidationResult> emails, List<String> phoneNumbers, List<String> url, List<String> date) {
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.url = url;
        this.date = date;
    }

    public List<EmailValidationResult> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailValidationResult> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

}