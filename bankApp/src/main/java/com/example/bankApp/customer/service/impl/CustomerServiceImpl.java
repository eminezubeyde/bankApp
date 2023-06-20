package com.example.bankApp.customer.service.impl;

import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.common.core.exception.AlreadyExistsException;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.message.CustomerMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.service.CustomerService;
import com.example.bankApp.customer.core.dto.CustomerDto;
import com.example.bankApp.customer.core.dto.request.CreateCustomerRequest;
import com.example.bankApp.customer.core.dto.request.UpdateCustomerRequest;
import com.example.bankApp.customer.core.mapper.CustomerMapper;
import com.example.bankApp.customer.entity.Customer;
import com.example.bankApp.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public GeneralResult add(CreateCustomerRequest request) throws AlreadyExistsException {
        if (customerRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new AlreadyExistsException(CustomerMessage.ALREADY_EXISTS_TC.toString());
        }
        Customer customer = CustomerMapper.MAPPER.customerRequestToCustomer(request);
        customerRepository.save(customer);
        CustomerDto dto = CustomerMapper.MAPPER.customerToCustomerDto(customer);
        return new DataResult<>(dto);
    }

    @Override
    public void delete(int id) throws GeneralException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CustomerMessage.NOT_FOUND.toString()));
        if (customer.getSavingAccounts() != null && !customer.getSavingAccounts().isEmpty()) {
            throw new GeneralException("bu müşterinin vadeli hesabı olduğu için silinemez");
        }
        BigDecimal totalBalance = customer.getCheckingAccounts()
                .stream()
                .map(CheckingAccount::getBalance)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        if (totalBalance.compareTo(BigDecimal.ZERO)!=0) {
            throw new GeneralException("bakiye 0 olmadan silme işlemi yapılamaz");
        }

        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public GeneralResult update(int id, UpdateCustomerRequest request) throws EntityNotFoundException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CustomerMessage.NOT_FOUND.toString()));
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
    public Customer findByCustomerId(int id) throws EntityNotFoundException {
        return customerRepository
                .findById(id).
                orElseThrow(() -> new EntityNotFoundException(CustomerMessage.NOT_FOUND.toString()));
    }

    private static void setUpdateCustomerRequestToEntity(UpdateCustomerRequest request, Customer customer) {
        customer.setPassword(request.getPassword());
        customer.setTelephone(request.getTelephone());
        customer.setSurname(request.getSurname());
    }

}
