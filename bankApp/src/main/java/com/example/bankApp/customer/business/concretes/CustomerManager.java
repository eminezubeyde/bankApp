package com.example.bankApp.customer.business.concretes;

import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.CustomerService;
import com.example.bankApp.customer.core.dto.CustomerDto;
import com.example.bankApp.customer.core.dto.request.CustomerRequest;
import com.example.bankApp.customer.core.mapper.CustomerMapper;
import com.example.bankApp.customer.entity.Customer;
import com.example.bankApp.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public GeneralResult add(CustomerRequest request) {
        if (customerRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new RuntimeException("bu tc ile kayıtlı müşteri zaten var");
        }
        Customer customer = CustomerMapper.MAPPER.customerRequestToCustomer(request);
        customerRepository.save(customer);
        CustomerDto dto = CustomerMapper.MAPPER.customerToCustomerDto(customer);
        return new DataResult<>(dto);
    }

    @Override
    public void delete(int id) {
        checkIfCustomerExists(id);
        customerRepository.deleteById(id);
    }

    @Override
    public GeneralResult update(int id, CustomerRequest request) {
        checkIfCustomerExists(id);
        Customer customer=CustomerMapper.MAPPER.customerRequestToCustomer(request);
        customerRepository.save(customer);
        CustomerDto dto=CustomerMapper.MAPPER.customerToCustomerDto(customer);
        return new DataResult<>(dto);
    }

    @Override
    public GeneralResult getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> dtoList = customerList.stream().map(CustomerMapper.MAPPER::customerToCustomerDto).toList();
        return new DataResult<>(dtoList);
    }

    private void checkIfCustomerExists(int id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("bu id ile kayıtlı müşteri bulunamadı");
        }
    }
}
