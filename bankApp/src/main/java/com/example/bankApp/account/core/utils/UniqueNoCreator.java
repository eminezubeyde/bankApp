package com.example.bankApp.account.core.utils;

import com.example.bankApp.account.business.abstracts.BaseAccountService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueNoCreator {
    private final BaseAccountService accountService;

    public String createAccountNo() {
        String randomDepositAccountNo = RandomStringUtils.randomNumeric(16);
        if (!(accountService.checkIsAccountNoUnique(randomDepositAccountNo))) {
            return randomDepositAccountNo;
        }
        return createAccountNo();
    }
}
