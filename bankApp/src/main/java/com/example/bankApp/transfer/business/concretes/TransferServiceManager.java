package com.example.bankApp.transfer.business.concretes;

import com.example.bankApp.account.business.abstracts.BaseAccountService;
import com.example.bankApp.account.business.abstracts.AccountActivityService;
import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.common.core.entity.ActionStatus;
import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.common.core.message.TransferMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.exchange.business.abstracts.ExchangeService;
import com.example.bankApp.transfer.core.dto.TransferDto;
import com.example.bankApp.transfer.core.exception.TransferOperationException;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.transfer.business.abstracts.TransferService;
import com.example.bankApp.transfer.core.dto.request.CreateTransferRequest;
import com.example.bankApp.transfer.core.mapper.TransferMapper;
import com.example.bankApp.transfer.entitiy.Transfer;
import com.example.bankApp.transfer.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferServiceManager implements TransferService {
    private final TransferRepository transferRepository;
    private final BaseAccountService baseAccountService;
    private final AccountActivityService accountActivity;
    private final ExchangeService exchangeService;

    @Override
    @Transactional
    public GeneralResult transferByIbanNo(CreateTransferRequest createTransferRequest) throws TransferOperationException, AmountNotValidException {
        Account fromAccount = baseAccountService.getByIbanNo(createTransferRequest.getFromIban());
        Account toAccount=baseAccountService.getByIbanNo(createTransferRequest.getToIban());
        Transfer transfer = TransferMapper.MAPPER.requestToEntity(createTransferRequest);

        if (fromAccount == null) {
            throw new TransferOperationException(TransferMessage.NOT_FOUND.toString());
        }
        if (toAccount==null) {
            throw new TransferOperationException(TransferMessage.NOT_FOUND.toString());
        }
        if (fromAccount.getBalance().compareTo(createTransferRequest.getAmount()) < 0) {
            throw new TransferOperationException(TransferMessage.INSUFFICIENT_BALANCE.toString());
        }
        if(fromAccount.getCurrencyType()!=toAccount.getCurrencyType()){
            exchangeService.getExchangeAmount(toAccount.getCurrencyType().toString()
                    ,fromAccount.getCurrencyType().toString()
                    ,createTransferRequest.getAmount());
        }
        transfer.setProcessTime(LocalDateTime.now());
        transferRepository.save(transfer);

        accountActivity.addActivity(fromAccount.getId(),
                "transfer başarılı",
                createTransferRequest.getAmount(),
                createTransferRequest.getToIban(),
                ActionStatus.OUTGOING);
        accountActivity.addActivity((toAccount.getId()),
                "transfer başarılı",
                createTransferRequest.getAmount(),
                createTransferRequest.getFromIban(),
                ActionStatus.INCOMING);
        fromAccount.setBalance(fromAccount.getBalance().subtract(createTransferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(createTransferRequest.getAmount()));

        TransferDto transferDto=TransferMapper.MAPPER.entityToDto(transfer);

        return new DataResult<>(transferDto);
    }
}
