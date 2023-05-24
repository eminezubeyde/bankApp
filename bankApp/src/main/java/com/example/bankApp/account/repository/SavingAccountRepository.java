package com.example.bankApp.account.repository;

import com.example.bankApp.account.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount,Integer> {
}
