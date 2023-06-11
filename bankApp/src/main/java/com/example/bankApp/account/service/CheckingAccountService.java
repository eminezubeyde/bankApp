package com.example.bankApp.account.service;

import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.result.GeneralResult;

public interface CheckingAccountService {
    GeneralResult add(int customerId, CreateCheckingAccountRequest request) throws EntityNotFoundException;

    void delete(int id) throws GeneralException;

    GeneralResult getAll();
    GeneralResult getById(int id) throws EntityNotFoundException;
    CheckingAccount findById(int id) throws EntityNotFoundException;
    GeneralResult getAllCheckingAccountsByCustomerId(int customerId) throws EntityNotFoundException;

    GeneralResult getCheckingAccountsByCurrencyType(String currencyType);

    GeneralResult getAllAccountActivitiesByCheckingAccountId(int checkingAccountId) throws EntityNotFoundException;
}
