package com.example.bankApp.common.core.message;

public enum CheckingAccountMessage {
    NOT_FOUND("Böyle bir vadesiz hesap bulunamadı"),
    ALREADY_EXISTS("Böyle bir vadesiz hesap zaten kayıtlı"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");


    private final String message;

    CheckingAccountMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
