package com.example.bankApp.card.core.mapper;

import com.example.bankApp.card.core.dto.DebitCardResponse;
import com.example.bankApp.card.core.dto.requests.CreateDebitCardRequest;
import com.example.bankApp.card.entity.DebitCard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DebitCardMapper {
    DebitCardMapper MAPPER = Mappers.getMapper(DebitCardMapper.class);

    DebitCard requestToEntity(CreateDebitCardRequest debitCardRequest);
    DebitCardResponse entityToResponse(DebitCard debitCard);
}
