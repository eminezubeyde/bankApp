package com.example.bankApp.transfer.core.mapper;

import com.example.bankApp.transfer.core.dto.TransferDto;
import com.example.bankApp.transfer.core.dto.request.CreateTransferRequest;
import com.example.bankApp.transfer.entitiy.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    static TransferMapper MAPPER= Mappers.getMapper(TransferMapper.class);
    Transfer requestToEntity(CreateTransferRequest request);
    TransferDto entityToDto(Transfer transfer);
}
