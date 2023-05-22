package com.example.bankApp.customer.business.concretes;

import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.AddressService;
import com.example.bankApp.customer.core.dto.AddressDto;
import com.example.bankApp.customer.core.dto.request.AddressRequest;
import com.example.bankApp.customer.core.mapper.AddressMapper;
import com.example.bankApp.customer.entity.Address;
import com.example.bankApp.customer.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PrimitiveIterator;

@Service
@RequiredArgsConstructor
public class AddressManager implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public GeneralResult add(AddressRequest request) {
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
    public GeneralResult update(int id, AddressRequest request) {
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
            throw new RuntimeException("böyle bir address bulunamadı");
        }
    }
}
