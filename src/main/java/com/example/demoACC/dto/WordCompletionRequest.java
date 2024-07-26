package com.example.demoACC.dto;

public class WordCompletionRequest {
    private String prefix;
    private int limit;

    // Getters and setters
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}