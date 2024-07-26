package com.example.demoACC.model;

    public class EmailValidationResult {
    private String email;
    private boolean isValid;

    public EmailValidationResult(String email, boolean isValid) {
        this.email = email;
        this.isValid = isValid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
