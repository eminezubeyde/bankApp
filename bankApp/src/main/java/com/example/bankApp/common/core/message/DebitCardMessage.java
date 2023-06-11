package com.example.bankApp.common.core.message;

public enum DebitCardMessage {
    NOT_FOUND("debitcard not found"),
    ALREADY_EXISTS("debitcard already exists"),
    SUCCESSFUL("successful");


    private final String message;

    DebitCardMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}


