package com.example.bankApp.common.core.message;

public enum SavingAccountMessage {
    NOT_FOUND("Böyle bir vadeli hesap bulunamadı"),
    ALREADY_EXISTS("Böyle bir vadeli hesap zaten kayıtlı"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ"),
    INSUFFICIENT_BALANCE("Yetersiz bakiye"),
    CURRENCY_TYPE_DONT_MATCH("Para birimleri vadesiz hesap ile aynı olmalıdır.");



    private final String message;

    SavingAccountMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
