package com.example.bankApp.common.core.utils;

import com.example.bankApp.account.service.BaseAccountService;
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

    public String createIbanNo() {
        String ibanKodu="TR";
        String randomDepositIbanNo = RandomStringUtils.randomNumeric(24);
        if (!(accountService.checkIsIbanNoUnique(randomDepositIbanNo))){
            return ibanKodu+randomDepositIbanNo;
        }
        return createIbanNo();
    }
}

