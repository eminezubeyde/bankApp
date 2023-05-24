package com.example.bankApp.common.core.message;

public enum AddressMessage {
        NOT_FOUND("Böyle bir address bulunamadı"),
        ALREADY_EXISTS("Böyle bir address zaten kayıtlı"),
        SUCCESSFUL("BAŞARIYLA EKLENDİ");


        private final String message;

        AddressMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
}
