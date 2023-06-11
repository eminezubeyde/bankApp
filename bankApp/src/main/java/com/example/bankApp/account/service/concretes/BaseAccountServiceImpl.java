package com.example.bankApp.account.service.concretes;

import com.example.bankApp.account.service.BaseAccountService;
import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.account.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseAccountServiceImpl implements BaseAccountService {
    private final AccountRepository accountRepository;

    @Override
    public boolean checkIsAccountNoUnique(String accountNo) {
        return accountRepository.existsByAccountNo(accountNo);
    }

    @Override
    public boolean checkIsIbanNoUnique(String ibanNo) {
        return accountRepository.existsByIbanNo(ibanNo);
    }

    @Override
    public Account getById(int accountId) {
        return accountRepository.findById(accountId).orElseThrow(()->
                new EntityNotFoundException("hesap bulunamadı"));
    }

    @Override
    public boolean checkIfIbanNoExists(String ibanNo) {
        return accountRepository.existsByIbanNo(ibanNo);
    }

    @Override
    public Account getByIbanNo(String ibanNo) {
        return accountRepository.findByIbanNo(ibanNo);
    }
}
