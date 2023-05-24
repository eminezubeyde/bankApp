package com.example.bankApp.common.core.message;

public enum CustomerMessage {
    NOT_FOUND("Böyle bir müşteri bulunamadı"),
    ALREADY_EXISTS("Böyle bir müşteri zaten kayıtlı"),
    ALREADY_EXISTS_TC("Bu tc ile kayıtlı müşteri zaten var"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");



    private final String message;

    CustomerMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
