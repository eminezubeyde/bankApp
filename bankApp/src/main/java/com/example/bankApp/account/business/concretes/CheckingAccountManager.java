package com.example.bankApp.account.business.concretes;

import com.example.bankApp.account.business.abstracts.CheckingAccountService;
import com.example.bankApp.account.core.dto.AccountActivityDto;
import com.example.bankApp.account.core.dto.CheckingAccountDto;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.account.core.mapper.AccountActivityMapper;
import com.example.bankApp.account.core.mapper.CheckingAccountMapper;
import com.example.bankApp.account.entity.SavingAccount;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.message.SavingAccountMessage;
import com.example.bankApp.common.core.utils.UniqueNoCreator;
import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.account.entity.enums.AccountType;
import com.example.bankApp.account.repository.CheckingAccountRepository;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.message.CheckingAccountMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.CustomerService;
import com.example.bankApp.customer.entity.Customer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckingAccountManager implements CheckingAccountService {
    private final CheckingAccountRepository checkingAccountRepository;
    private final CustomerService customerService;
    private final UniqueNoCreator uniqueNoCreator;


    @Override
    @Transactional
    public GeneralResult add(int customerId, CreateCheckingAccountRequest request) throws EntityNotFoundException {
        Customer customer = customerService.findByCustomerId(customerId);
        CheckingAccount checkingAccount = CheckingAccountMapper.MAPPER.requestToEntity(request);
        checkingAccount.setCustomer(customer);
        checkingAccount.setCreatedAt(LocalDateTime.now());
        checkingAccount.setAccountNo(uniqueNoCreator.createAccountNo());
        checkingAccount.setIbanNo(uniqueNoCreator.createIbanNo());
        checkingAccount.setAccountType(AccountType.CHECKING);
        customer.getCheckingAccounts().add(checkingAccount);
        checkingAccountRepository.save(checkingAccount);
        CheckingAccountDto dto = CheckingAccountMapper.MAPPER.entityToDto(checkingAccount);
        return new DataResult<>(dto);
    }
    //TODO hesabı blocklama ekle


    @Override
    public void delete(int id) throws GeneralException {
        CheckingAccount checkingAccount = checkingAccountRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CheckingAccountMessage.NOT_FOUND.toString()));
        if (checkingAccount.getSavingAccount() != null) {
            throw new GeneralException("bu hesaba bağlı bir vadeli hesap olduğu için silinemez");
        }
        checkingAccountRepository.deleteById(id);

    }

    @Override
    public GeneralResult getAll() {
        List<CheckingAccount> customerList = checkingAccountRepository.findAll();
        List<CheckingAccountDto> dtoList = customerList
                .stream()
                .map(CheckingAccountMapper.MAPPER::entityToDto)
                .toList();
        return new DataResult<>(dtoList);
    }

    @Override
    public GeneralResult getById(int id) throws EntityNotFoundException {
        CheckingAccount checkingAccount = checkingAccountRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CheckingAccountMessage.NOT_FOUND.toString()));
        CheckingAccountDto dto = CheckingAccountMapper.MAPPER.entityToDto(checkingAccount);
        return new DataResult<>(dto);
    }

    @Override
    public CheckingAccount findById(int id) throws com.example.bankApp.common.core.exception.EntityNotFoundException {
        return checkingAccountRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(CheckingAccountMessage.NOT_FOUND.toString()));
    }

    @Override
    public GeneralResult getAllCheckingAccountsByCustomerId(int customerId) throws EntityNotFoundException {
        Customer customer = customerService.findByCustomerId(customerId);
        List<CheckingAccount> checkingAccounts = customer.getCheckingAccounts();
        List<CheckingAccountDto> dtoList = checkingAccounts
                .stream()
                .map(CheckingAccountMapper.MAPPER::entityToDto)
                .toList();
        return new DataResult<>(dtoList);
    }

    @Override
    public GeneralResult getCheckingAccountsByCurrencyType(String currencyType) {
        List<CheckingAccount> checkingAccounts = checkingAccountRepository
                .findAll()
                .stream()
                .filter(account -> account.getCurrencyType().getValue().equals(currencyType))
                .toList();
        List<CheckingAccountDto> dtoList = checkingAccounts
                .stream()
                .map(CheckingAccountMapper.MAPPER::entityToDto)
                .toList();

        return new DataResult<>(dtoList);
    }

    @Override
    public GeneralResult getAllAccountActivitiesByCheckingAccountId(int checkingAccountId) throws EntityNotFoundException {
        CheckingAccount checkingAccount = checkingAccountRepository
                .findById(checkingAccountId)
                .orElseThrow(() -> new EntityNotFoundException(CheckingAccountMessage.NOT_FOUND.toString()));
        List<AccountActivityDto> accountActivityDtoList = checkingAccount.getActivities().stream().map(AccountActivityMapper.MAPPER::entityToDto).toList();
        return new DataResult<>(accountActivityDtoList);
    }
}
