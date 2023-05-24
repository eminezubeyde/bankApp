package com.example.bankApp.account.repository;

import com.example.bankApp.account.entity.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount,Integer> {

}
