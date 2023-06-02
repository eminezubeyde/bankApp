package com.example.bankApp.transfer.entitiy;

import com.example.bankApp.transfer.entitiy.enums.TransferType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fromIban;//ibandan
    private String toIban;//ibana
    private BigDecimal balance;
    private String description;
    private LocalDateTime processTime;//işlem zamanı
    @Enumerated(value = EnumType.STRING)
    private TransferType transferType;

}
