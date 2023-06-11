package com.example.bankApp.card.repository;

import com.example.bankApp.card.entity.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitCardRepository extends JpaRepository<DebitCard, Integer> {


}
