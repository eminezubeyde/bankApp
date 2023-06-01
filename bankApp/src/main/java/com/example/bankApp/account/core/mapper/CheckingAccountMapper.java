package com.example.bankApp.account.core.mapper;

import com.example.bankApp.account.core.dto.CheckingAccountDto;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.account.entity.CheckingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface CheckingAccountMapper {
    static CheckingAccountMapper MAPPER = Mappers.getMapper(CheckingAccountMapper.class);

    @Mapping(target = "balance", expression = "java(convertBigDecimalToDouble(request.getBalance()))")
    CheckingAccount requestToEntity(CreateCheckingAccountRequest request);


    @Mapping(target = "customerId", source = "checkingAccount.customer.id")
    CheckingAccountDto entityToDto(CheckingAccount checkingAccount);
    default BigDecimal convertBigDecimalToDouble(double value) {
        return BigDecimal.valueOf(value);
    }
}
