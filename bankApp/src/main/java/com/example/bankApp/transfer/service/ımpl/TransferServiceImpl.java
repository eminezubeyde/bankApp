package com.example.bankApp.transfer.service.ımpl;

import com.example.bankApp.account.service.BaseAccountService;
import com.example.bankApp.account.service.AccountActivityService;
import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.common.core.entity.ActionStatus;
import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.common.core.message.TransferMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.exchange.service.ExchangeService;
import com.example.bankApp.exchange.core.model.Exchange;
import com.example.bankApp.transfer.core.dto.TransferDto;
import com.example.bankApp.transfer.core.exception.TransferOperationException;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.transfer.service.TransferService;
import com.example.bankApp.transfer.core.dto.request.CreateTransferRequest;
import com.example.bankApp.transfer.core.mapper.TransferMapper;
import com.example.bankApp.transfer.entitiy.Transfer;
import com.example.bankApp.transfer.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final BaseAccountService baseAccountService;
    private final AccountActivityService accountActivity;
    private final ExchangeService exchangeService;

    @Override
    @Transactional
    public GeneralResult transferByIbanNo(CreateTransferRequest createTransferRequest) throws TransferOperationException, AmountNotValidException {
        BigDecimal transferAmount=createTransferRequest.getAmount();
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
        transfer.setProcessTime(LocalDateTime.now());
        transferRepository.save(transfer);
        if(!fromAccount.getCurrencyType().equals(toAccount.getCurrencyType())){

            Exchange exchangeAmount= exchangeService.getExchangeAmount(toAccount.getCurrencyType().toString()
                    , fromAccount.getCurrencyType().toString()
                    , createTransferRequest.getAmount());
            transferAmount= BigDecimal.valueOf(exchangeAmount.getResult());

        }
        accountActivity.addActivity(fromAccount.getId(),
                "transfer başarılı",
                transferAmount,
                createTransferRequest.getToIban(),
                ActionStatus.OUTGOING);
        accountActivity.addActivity((toAccount.getId()),
                "transfer başarılı",
                transferAmount,
                createTransferRequest.getFromIban(),
                ActionStatus.INCOMING);

        fromAccount.setBalance(fromAccount.getBalance().subtract(createTransferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferAmount));
        TransferDto transferDto=TransferMapper.MAPPER.entityToDto(transfer);

        return new DataResult<>(transferDto);
    }
}
