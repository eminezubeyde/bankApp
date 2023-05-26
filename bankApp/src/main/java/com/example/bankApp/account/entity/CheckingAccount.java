package com.example.bankApp.account.entity;

import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CheckingAccount extends Account {
    //vadesiz hesap
    private String bankCode;
    private String branchCode;//şube kodu
    private String branchName;
    private boolean blocked = false;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @OneToOne
    private SavingAccount savingAccount;
}
