package com.example.bankApp.account.repository;

import com.example.bankApp.account.entity.base.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountActivityRepository extends JpaRepository<AccountActivity,Integer> {
}
