package com.example.bankApp.customer.business.abstracts;

import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.core.dto.request.CustomerRequest;

public interface CustomerService {
    GeneralResult add(CustomerRequest request);

    void delete(int id);

    GeneralResult update(int id, CustomerRequest request);

    GeneralResult getAll();

}
