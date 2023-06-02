package com.example.bankApp.common.core.message;

public enum TransferMessage {
    NOT_FOUND("Böyle bir iban bulunamadı"),
    INSUFFICIENT_BALANCE("YETERSİZ BAKİYE");

    private final String message;

    TransferMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
