package com.example.bankApp.account.business.abstracts;

import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.common.core.result.GeneralResult;

public interface CheckingAccountService {
    GeneralResult add(int customerId, CreateCheckingAccountRequest request);

    void delete(int id);

    GeneralResult getAll();
    GeneralResult getById(int id);
    GeneralResult getAllCheckingAccountsByCustomerId(int customerId);

    GeneralResult getCheckingAccountsByCurrencyType(String currencyType);
}
