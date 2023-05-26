package com.example.bankApp.account.core.mapper;

import com.example.bankApp.account.core.dto.CheckingAccountDto;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.account.entity.CheckingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CheckingAccountMapper {
    static CheckingAccountMapper MAPPER= Mappers.getMapper(CheckingAccountMapper.class);

    CheckingAccount requestToEntity(CreateCheckingAccountRequest request);

    @Mapping(target = "customerId", source = "checkingAccount.customer.id")
    CheckingAccountDto entityToDto(CheckingAccount checkingAccount);
}
