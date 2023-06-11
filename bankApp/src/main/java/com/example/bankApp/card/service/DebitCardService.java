package com.example.bankApp.card.service;

import com.example.bankApp.card.core.dto.requests.CreateDebitCardRequest;
import com.example.bankApp.common.core.exception.AlreadyExistsException;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.result.GeneralResult;

public interface DebitCardService {

    GeneralResult create(int checkingAccountId,CreateDebitCardRequest createDebitCardRequest) throws EntityNotFoundException, AlreadyExistsException;

    GeneralResult delete(int debitCardId) throws EntityNotFoundException;
}
