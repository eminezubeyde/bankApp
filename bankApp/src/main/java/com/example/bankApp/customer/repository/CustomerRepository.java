package com.example.bankApp.customer.repository;

import com.example.bankApp.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByIdentityNumber(String identityNumber);
}
