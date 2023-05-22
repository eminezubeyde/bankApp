package com.example.bankApp.customer.core.mapper;

import com.example.bankApp.customer.core.dto.CustomerDto;
import com.example.bankApp.customer.core.dto.request.CustomerRequest;
import com.example.bankApp.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper MAPPER= Mappers.getMapper(CustomerMapper.class);

    Customer customerRequestToCustomer(CustomerRequest request);
    CustomerDto customerToCustomerDto(Customer customer);
}
