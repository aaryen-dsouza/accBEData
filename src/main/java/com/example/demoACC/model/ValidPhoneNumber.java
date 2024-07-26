package com.example.demoACC.model;

    public class ValidPhoneNumber {
    private String number;
    private boolean isValid;

    public ValidPhoneNumber(String email, boolean isValid) {
        this.number = email;
        this.isValid = isValid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String email) {
        this.number = email;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
