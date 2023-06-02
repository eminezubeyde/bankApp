package com.example.bankApp.transfer.core.exception;

import com.example.bankApp.common.core.exception.GeneralException;

public class TransferOperationException extends GeneralException {
    public TransferOperationException(String message) {
        super(message);
    }
}
