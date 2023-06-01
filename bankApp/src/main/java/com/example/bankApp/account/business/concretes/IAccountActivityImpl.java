package com.example.bankApp.account.business.concretes;

import com.example.bankApp.account.business.abstracts.BaseAccountService;
import com.example.bankApp.account.business.abstracts.IAccountActivity;
import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.account.entity.base.AccountActivity;
import com.example.bankApp.account.repository.AccountActivityRepository;
import com.example.bankApp.common.core.entity.ActionStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class IAccountActivityImpl implements IAccountActivity {
    private final BaseAccountService baseAccountService;
    private final AccountActivityRepository accountActivityRepository;
    @Override
    @Transactional
    public void addActivity(int accountId, String decription, BigDecimal amount, String crossAccountIbanNo, ActionStatus actionStatus) {
        AccountActivity accountActivity=new AccountActivity();
        Account account = baseAccountService.getById(accountId);
        accountActivity.setDescription(decription);
        accountActivity.setAmount(amount);
        accountActivity.setActionStatus(actionStatus);
        accountActivity.setDate(LocalDateTime.now());
        accountActivity.setAccount(account);
        accountActivity.setCrossAccount(crossAccountIbanNo);
        if(accountActivity.getActionStatus()==ActionStatus.INCOMING){
            accountActivity.setAvailableBalance(account.getBalance().add(amount));
        }else{
            accountActivity.setAvailableBalance(account.getBalance().subtract(amount));
        }
        accountActivityRepository.save(accountActivity);
        account.getActivities().add(accountActivity);
    }
}
