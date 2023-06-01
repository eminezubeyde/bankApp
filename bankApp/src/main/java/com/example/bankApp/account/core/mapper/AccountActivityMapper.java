package com.example.bankApp.account.core.mapper;

import com.example.bankApp.account.core.dto.AccountActivityDto;
import com.example.bankApp.account.core.dto.CheckingAccountDto;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.account.entity.base.AccountActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface AccountActivityMapper {
    static AccountActivityMapper MAPPER= Mappers.getMapper(AccountActivityMapper.class);


    AccountActivityDto entityToDto(AccountActivity accountActivity);
}
