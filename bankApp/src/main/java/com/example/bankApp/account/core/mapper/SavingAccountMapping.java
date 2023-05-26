package com.example.bankApp.account.core.mapper;

import com.example.bankApp.account.core.dto.SavingAccountDto;
import com.example.bankApp.account.core.dto.requests.SavingAccountRequest;
import com.example.bankApp.account.entity.SavingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SavingAccountMapping {
    static SavingAccountMapping MAPPER= Mappers.getMapper(SavingAccountMapping.class);
    SavingAccount requestToEntity(SavingAccountRequest request);
    //source kaynak
    //target hedef
    @Mapping(target ="checkingAccountId" ,source ="savingAccount.checkingAccount.id" )
    SavingAccountDto entityToDto(SavingAccount savingAccount);
}
