package com.example.bankApp.account.service;

import com.example.bankApp.account.entity.base.Account;

public interface BaseAccountService {
    boolean checkIsAccountNoUnique(String accountNo);
    boolean checkIsIbanNoUnique(String ibanNo);

    Account getById(int accountId);
    boolean checkIfIbanNoExists(String ibanNo);
    Account getByIbanNo(String ibanNo);
}
