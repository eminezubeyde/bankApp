package com.example.bankApp.transfer.repository;

import com.example.bankApp.transfer.entitiy.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
