package com.example.bankApp.transfer.api.controller;

import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.transfer.service.TransferService;
import com.example.bankApp.transfer.core.dto.request.CreateTransferRequest;
import com.example.bankApp.transfer.core.exception.TransferOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public GeneralResult transferByIbanNo(@RequestBody CreateTransferRequest createTransferRequest) throws AmountNotValidException, TransferOperationException {
       return transferService.transferByIbanNo(createTransferRequest);
    }
}
