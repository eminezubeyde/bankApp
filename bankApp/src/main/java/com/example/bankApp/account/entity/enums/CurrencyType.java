package com.example.bankApp.account.entity.enums;

public enum CurrencyType {
    USD("USD"), EUR("EUR"), TRY("TRY");

    private String value;

    CurrencyType(String value){
        this.value=value;
    }


    public String getValue(){
        return value;
    }
    @Override
    public String toString() {
        return value;
    }
}
