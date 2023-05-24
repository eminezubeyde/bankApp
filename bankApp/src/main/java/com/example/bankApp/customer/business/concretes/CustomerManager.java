package com.example.bankApp.customer.business.concretes;

import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.message.CustomerMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.CustomerService;
import com.example.bankApp.customer.core.dto.CustomerDto;
import com.example.bankApp.customer.core.dto.request.CreateCustomerRequest;
import com.example.bankApp.customer.core.dto.request.UpdateCustomerRequest;
import com.example.bankApp.customer.core.mapper.CustomerMapper;
import com.example.bankApp.customer.entity.Customer;
import com.example.bankApp.customer.repository.CustomerRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public GeneralResult add(CreateCustomerRequest request) throws EntityNotFoundException {
        if (customerRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new EntityNotFoundException(CustomerMessage.ALREADY_EXISTS_TC.toString());
        }
        Customer customer = CustomerMapper.MAPPER.customerRequestToCustomer(request);
        customerRepository.save(customer);
        CustomerDto dto = CustomerMapper.MAPPER.customerToCustomerDto(customer);
        return new DataResult<>(dto);
    }

    @Override
    public void delete(int id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException(CustomerMessage.NOT_FOUND.toString());
        }
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public GeneralResult update(int id, UpdateCustomerRequest request) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityExistsException(CustomerMessage.NOT_FOUND.toString()));
        setUpdateCustomerRequestToEntity(request, customer);

        CustomerDto dto = CustomerMapper.MAPPER.customerToCustomerDto(customer);
        return new DataResult<>(dto);
    }

    @Override
    public GeneralResult getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> dtoList = customerList
                .stream()
                .map(CustomerMapper.MAPPER::customerToCustomerDto)
                .toList();
        return new DataResult<>(dtoList);
    }

    @Override
    public Customer findByCustomerId(int id) {
        return customerRepository
                .findById(id).
                orElseThrow(() -> new EntityExistsException(CustomerMessage.NOT_FOUND.toString()));
    }

    private static void setUpdateCustomerRequestToEntity(UpdateCustomerRequest request, Customer customer) {
        customer.setPassword(request.getPassword());
        customer.setTelephone(request.getTelephone());
        customer.setSurname(request.getSurname());
    }

}
