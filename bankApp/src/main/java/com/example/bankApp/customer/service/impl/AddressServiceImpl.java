package com.example.bankApp.customer.service.impl;

import com.example.bankApp.common.core.message.AddressMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.service.AddressService;
import com.example.bankApp.customer.core.dto.AddressDto;
import com.example.bankApp.customer.core.dto.request.CreateAddressRequest;
import com.example.bankApp.customer.core.mapper.AddressMapper;
import com.example.bankApp.customer.entity.Address;
import com.example.bankApp.customer.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public GeneralResult add(CreateAddressRequest request) {
        Address address = AddressMapper.MAPPER.addressRequestToAddress(request);
        addressRepository.save(address);
        AddressDto dto = AddressMapper.MAPPER.addressToAddressDto(address);
        return new DataResult<>(dto);
    }

    @Override
    public void delete(int id) {
        checkIfAddressExists(id);
        addressRepository.deleteById(id);
    }


    @Override
    public GeneralResult update(int id, CreateAddressRequest request) {
        checkIfAddressExists(id);
        Address address = AddressMapper.MAPPER.addressRequestToAddress(request);
        addressRepository.save(address);
        AddressDto dto = AddressMapper.MAPPER.addressToAddressDto(address);
        return new DataResult<>(dto);
    }

    @Override
    public GeneralResult getAll() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDto> dtoList = addresses.stream().map(AddressMapper.MAPPER::addressToAddressDto).toList();
        return new DataResult<>(dtoList);
    }

    private void checkIfAddressExists(int id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException(AddressMessage.NOT_FOUND.toString());
        }
    }
}
