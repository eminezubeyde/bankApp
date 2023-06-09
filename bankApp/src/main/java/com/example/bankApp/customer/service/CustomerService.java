package com.example.bankApp.customer.service;

import com.example.bankApp.common.core.exception.AlreadyExistsException;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.core.dto.request.CreateCustomerRequest;
import com.example.bankApp.customer.core.dto.request.UpdateCustomerRequest;
import com.example.bankApp.customer.entity.Customer;

public interface CustomerService {
    GeneralResult add(CreateCustomerRequest request) throws  AlreadyExistsException;

    void delete(int id) throws GeneralException;

    GeneralResult update(int id, UpdateCustomerRequest request) throws EntityNotFoundException;

    GeneralResult getAll();
    Customer findByCustomerId(int id) throws EntityNotFoundException;

}
