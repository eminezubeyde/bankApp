package com.example.bankApp.customer.business.abstracts;

import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.core.dto.request.CreateAddressRequest;

public interface AddressService {
    GeneralResult add(CreateAddressRequest request);

    void delete(int id);

    GeneralResult update(int id, CreateAddressRequest request);

    GeneralResult getAll();
}
