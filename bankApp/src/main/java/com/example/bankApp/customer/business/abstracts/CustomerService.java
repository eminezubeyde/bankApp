package com.example.bankApp.customer.business.abstracts;

import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.core.dto.request.CreateCustomerRequest;
import com.example.bankApp.customer.core.dto.request.UpdateCustomerRequest;
import com.example.bankApp.customer.entity.Customer;

public interface CustomerService {
    GeneralResult add(CreateCustomerRequest request) throws EntityNotFoundException;

    void delete(int id);

    GeneralResult update(int id, UpdateCustomerRequest request);

    GeneralResult getAll();
    Customer findByCustomerId(int id);

}
