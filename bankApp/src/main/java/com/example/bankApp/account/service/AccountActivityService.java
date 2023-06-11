package com.example.bankApp.account.service;

import com.example.bankApp.common.core.entity.ActionStatus;

import java.math.BigDecimal;

public interface AccountActivityService {

    void addActivity(int accountId, String decription, BigDecimal amount, String crossAccountIbanNo, ActionStatus actionStatus);
}
