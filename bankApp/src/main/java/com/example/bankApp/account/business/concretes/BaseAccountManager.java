package com.example.bankApp.account.business.concretes;

import com.example.bankApp.account.business.abstracts.BaseAccountService;
import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.account.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BaseAccountManager implements BaseAccountService {
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
