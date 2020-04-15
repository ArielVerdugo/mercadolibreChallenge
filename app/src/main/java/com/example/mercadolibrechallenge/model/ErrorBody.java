package com.example.mercadolibrechallenge.model;

public class ErrorBody {
    private String debugMessage;
    private String errorType;

    public String debugMessage() {
        return debugMessage;
    }
    public void debugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public String errorType() { return errorType; }

    public void errorType(String errorType) { this.errorType = errorType; }

    public boolean isErrorTypeOneOrTwo() {
        return "1".equals(errorType) || "2".contains(errorType);
    }
}
