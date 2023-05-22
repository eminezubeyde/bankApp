package com.example.bankApp.customer.business.abstracts;

import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.core.dto.request.AddressRequest;
import com.example.bankApp.customer.core.dto.request.CustomerRequest;

public interface AddressService {
    GeneralResult add(AddressRequest request);

    void delete(int id);

    GeneralResult update(int id, AddressRequest request);

    GeneralResult getAll();
}
