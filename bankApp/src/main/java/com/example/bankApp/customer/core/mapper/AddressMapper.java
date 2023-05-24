package com.example.bankApp.customer.core.mapper;

import com.example.bankApp.customer.core.dto.AddressDto;
import com.example.bankApp.customer.core.dto.request.CreateAddressRequest;
import com.example.bankApp.customer.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    static AddressMapper MAPPER= Mappers.getMapper(AddressMapper.class);
    Address addressRequestToAddress(CreateAddressRequest request);
    AddressDto addressToAddressDto(Address address);

}
