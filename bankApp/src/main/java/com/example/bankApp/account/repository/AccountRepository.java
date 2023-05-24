package com.example.bankApp.account.repository;

import com.example.bankApp.account.entity.base.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    boolean existsByAccountNo(String accountNo);
}
