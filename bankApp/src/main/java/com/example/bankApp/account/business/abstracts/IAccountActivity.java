package com.example.bankApp.account.business.abstracts;

import com.example.bankApp.common.core.entity.ActionStatus;

import java.math.BigDecimal;

public interface IAccountActivity {

    void addActivity(int accountId, String decription, BigDecimal amount, String crossAccountIbanNo, ActionStatus actionStatus);
}
