package com.example.bankApp.account.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SavingAccountMapping {
    static SavingAccountMapping MAPPER= Mappers.getMapper(SavingAccountMapping.class);
}
