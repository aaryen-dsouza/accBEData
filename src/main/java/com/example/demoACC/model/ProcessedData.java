package com.example.demoACC.model;

import java.util.List;

public class ProcessedData {
    private List<EmailValidationResult> emailsValidated;
    private List<ValidPhoneNumber> phoneNumbers;
    private List<String> url;
    private List<String> date;



    public ProcessedData(List<EmailValidationResult> emailsValidated, List<ValidPhoneNumber> phoneNumbers, List<String> url, List<String> date) {
        this.emailsValidated = emailsValidated;
        this.phoneNumbers = phoneNumbers;
        this.url = url;
        this.date = date;
    }

    public List<EmailValidationResult> getEmailsValidated() {
        return emailsValidated;
    }

    public void setEmailsValidated(List<EmailValidationResult> emails) {
        this.emailsValidated = emails;
    }

    public List<ValidPhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<ValidPhoneNumber> phoneNumbers) {
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