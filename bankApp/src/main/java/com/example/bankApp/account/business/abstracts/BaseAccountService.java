package com.example.bankApp.account.business.abstracts;

public interface BaseAccountService {
    boolean checkIsAccountNoUnique(String accountNo);
    boolean checkIsIbanNoUnique(String ibanNo);
}
