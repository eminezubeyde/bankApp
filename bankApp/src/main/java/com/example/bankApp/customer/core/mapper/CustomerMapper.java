package com.example.bankApp.customer.core.mapper;

import com.example.bankApp.customer.core.dto.CustomerDto;
import com.example.bankApp.customer.core.dto.request.CreateCustomerRequest;
import com.example.bankApp.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer customerRequestToCustomer(CreateCustomerRequest request);

    Customer customerDtoToCustomer(CustomerDto dto);

    //@Mapping(target = "checkingAccountId", source = "customer.checkingAccount.id")
    //@Mapping(target = "savingAccountId", source = "customer.savingAccount.id")
    CustomerDto customerToCustomerDto(Customer customer);

}
