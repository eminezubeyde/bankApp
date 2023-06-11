package com.example.bankApp.account.service;

import com.example.bankApp.account.core.dto.requests.SavingAccountRequest;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.result.GeneralResult;

public interface SavingAccountService {
    GeneralResult create(int checkingAccountId, SavingAccountRequest request) throws GeneralException;

    GeneralResult getAll();

    GeneralResult getById(int savingAccountId) throws EntityNotFoundException;

    void deleteById(int savingAccountId) throws EntityNotFoundException;

    GeneralResult getAllSavingAccountsByCustomerId(int customerId) throws EntityNotFoundException;

    GeneralResult savingAccountClose(int savingAccountId) throws EntityNotFoundException;

    GeneralResult getAllAccountActivitiesBySavingAccountId(int savingAccountId) throws EntityNotFoundException;
}
