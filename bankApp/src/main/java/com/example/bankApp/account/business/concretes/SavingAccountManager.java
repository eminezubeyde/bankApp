package com.example.bankApp.account.business.concretes;

import com.example.bankApp.account.business.abstracts.CheckingAccountService;
import com.example.bankApp.account.business.abstracts.SavingAccountService;
import com.example.bankApp.account.core.dto.SavingAccountDto;
import com.example.bankApp.account.core.dto.requests.SavingAccountRequest;
import com.example.bankApp.account.core.mapper.SavingAccountMapping;
import com.example.bankApp.common.core.utils.AccountHelper;
import com.example.bankApp.common.core.utils.UniqueNoCreator;
import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.account.entity.SavingAccount;
import com.example.bankApp.account.entity.enums.AccountType;
import com.example.bankApp.account.repository.SavingAccountRepository;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.message.SavingAccountMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingAccountManager implements SavingAccountService {
    private final SavingAccountRepository savingAccountRepository;
    private final CheckingAccountService checkingAccountService;
    private final CustomerService customerService;
    private final UniqueNoCreator uniqueNoCreator;
    private final AccountHelper accountHelper;

    @Override
    @Transactional
    public GeneralResult create(int checkingAccountId, SavingAccountRequest request) throws GeneralException {
        CheckingAccount checkingAccount = checkingAccountService.findById(checkingAccountId);
        if (checkingAccount.getBalance() < request.getOpeningBalance()) {
            throw new GeneralException(SavingAccountMessage.INSUFFICIENT_BALANCE.toString());
        }

        SavingAccount savingAccount = SavingAccountMapping.MAPPER.requestToEntity(request);
        savingAccount.setCheckingAccount(checkingAccount);
        savingAccount.setAccountNo(uniqueNoCreator.createAccountNo());
        savingAccount.setIbanNo(uniqueNoCreator.createIbanNo());
        savingAccount.setAccountType(AccountType.SAVING);
        savingAccount.setCreatedAt(LocalDateTime.now());
        savingAccount.setBalance(savingAccount.getOpeningBalance());
        savingAccount.setSuccessRate(accountHelper.getSuccessRate(savingAccount.getOpeningBalance(), savingAccount.getTargetAmount()));
        savingAccountRepository.save(savingAccount);
        checkingAccount.setBalance(checkingAccount.getBalance() - savingAccount.getBalance());
        checkingAccount.setSavingAccount(savingAccount);
        SavingAccountDto savingAccountDto = SavingAccountMapping.MAPPER.entityToDto(savingAccount);


        return new DataResult<>(savingAccountDto);
    }
    // müşteri silerken de checking acocunt balanc'0 ve bağlı bir saving account olmaması lazım.testini yap

    // TODO bu işlerden sonra
    // TODO currency service yaratılacak . para değişimi(exchange)
    // bir tane servis tek olacak exchange(from,to,amount)
    // dış gerçek sisteme gidecek.
    // not dış servislere localden istek atmak için restTemplate kullanabilirsin.
    // body - header - content-type -
    // web site bul
    //

    @Override
    public GeneralResult getAll() {
        List<SavingAccount> savingAccounts = savingAccountRepository.findAll();
        List<SavingAccountDto> savingAccountDtos = savingAccounts
                .stream()
                .map(SavingAccountMapping.MAPPER::entityToDto)
                .toList();
        return new DataResult<>(savingAccountDtos);
    }

    @Override
    public GeneralResult getById(int savingAccountId) throws EntityNotFoundException {
        SavingAccount savingAccount = savingAccountRepository
                .findById(savingAccountId)
                .orElseThrow(() ->
                        new EntityNotFoundException(SavingAccountMessage.NOT_FOUND.toString()));
        SavingAccountDto savingAccountDto = SavingAccountMapping.MAPPER.entityToDto(savingAccount);
        return new DataResult<>(savingAccountDto);
    }

    @Override
    @Transactional
    public void deleteById(int savingAccountId) throws EntityNotFoundException {
        if (!savingAccountRepository.existsById(savingAccountId)) {
            throw new EntityNotFoundException(SavingAccountMessage.NOT_FOUND.toString());
        }
        savingAccountRepository.deleteById(savingAccountId);
    }

    @Override
    public GeneralResult getAllSavingAccountsByCustomerId(int customerId) throws EntityNotFoundException {
        List<SavingAccount> savingAccounts = customerService
                .findByCustomerId(customerId)
                .getSavingAccounts()
                .stream()
                .toList();
        List<SavingAccountDto> dtoList = savingAccounts
                .stream()
                .map(SavingAccountMapping.MAPPER::entityToDto)
                .toList();
        return new DataResult<>(dtoList);
    }

    @Override
    @Transactional
    public GeneralResult savingAccountClose(int savingAccountId) throws EntityNotFoundException {
        if (!savingAccountRepository.existsById(savingAccountId)) {
            throw new EntityNotFoundException(SavingAccountMessage.NOT_FOUND.toString());
        }
        SavingAccount savingAccount = savingAccountRepository
                .findById(savingAccountId)
                .orElseThrow(() -> new EntityNotFoundException(SavingAccountMessage.NOT_FOUND.toString()));
        CheckingAccount checkingAccount = savingAccount.getCheckingAccount();
        checkingAccount.setBalance(checkingAccount.getBalance() + savingAccount.getBalance());
        deleteById(savingAccountId);
        return new GeneralResult("başarılı", true);
    }
}
