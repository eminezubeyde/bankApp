package com.example.bankApp.transfer.business.abstracts;

import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.transfer.core.dto.request.CreateTransferRequest;
import com.example.bankApp.transfer.core.exception.TransferOperationException;

public interface TransferService {

    GeneralResult transferByIbanNo(CreateTransferRequest createTransferRequest) throws TransferOperationException, AmountNotValidException;
}
