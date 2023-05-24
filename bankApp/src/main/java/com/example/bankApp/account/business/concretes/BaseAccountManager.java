package com.example.bankApp.account.business.concretes;

import com.example.bankApp.account.business.abstracts.BaseAccountService;
import com.example.bankApp.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseAccountManager implements BaseAccountService {
    private final AccountRepository accountRepository;

    @Override
    public boolean checkIsAccountNoUnique(String accountNo) {
        return accountRepository.existsByAccountNo(accountNo);
    }
}
