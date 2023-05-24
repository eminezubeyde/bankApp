package com.example.bankApp.account.business.concretes;

import com.example.bankApp.account.business.abstracts.CheckingAccountService;
import com.example.bankApp.account.core.dto.CheckingAccountDto;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.account.core.mapper.CheckingAccountMapper;
import com.example.bankApp.account.core.utils.UniqueNoCreator;
import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.account.entity.enums.AccountType;
import com.example.bankApp.account.repository.CheckingAccountRepository;
import com.example.bankApp.common.core.message.CheckingAccountMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.CustomerService;
import com.example.bankApp.customer.entity.Customer;
import jakarta.persistence.EntityNotFoundException;
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
    public GeneralResult add(int customerId, CreateCheckingAccountRequest request) {
        Customer customer = customerService.findByCustomerId(customerId);
        CheckingAccount checkingAccount = CheckingAccountMapper.MAPPER.requestToEntity(request);
        checkingAccount.setCustomer(customer);
        checkingAccount.setCreatedAt(LocalDateTime.now());
        checkingAccount.setAccountNo(uniqueNoCreator.createAccountNo());
        checkingAccount.setIbanNo(null);
        checkingAccount.setAccountType(AccountType.CHECKING);
        customer.getCheckingAccounts().add(checkingAccount);
        checkingAccountRepository.save(checkingAccount);
        CheckingAccountDto dto = CheckingAccountMapper.MAPPER.entityToDto(checkingAccount);
        return new DataResult<>(dto);
    }
    // TODO bir kullanıcının tüm vadesiz hesaplarını getiren web servis.
    // TODO bir kullanıcının  X currencyType'ına göre tüm vadesiz hesaplarını getiren web servis.
    // TODO bir kullanıcının id'li vadesiz hesaplarını getiren web servis.


    @Override
    @Transactional
    public void delete(int id) {
        if (checkingAccountRepository.existsById(id)) {
            throw new EntityNotFoundException(CheckingAccountMessage.NOT_FOUND.toString());
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
    public GeneralResult getById(int id) {
        CheckingAccount checkingAccount = checkingAccountRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CheckingAccountMessage.NOT_FOUND.toString()));
        CheckingAccountDto dto = CheckingAccountMapper.MAPPER.entityToDto(checkingAccount);
        return new DataResult<>(dto);
    }

    @Override
    public GeneralResult getAllCheckingAccountsByCustomerId(int customerId) {
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
                .filter(checkingAccount -> false)
                .toList();
        List<CheckingAccountDto> dtoList = checkingAccounts
                .stream()
                .map(CheckingAccountMapper.MAPPER::entityToDto)
                .toList();

        return new DataResult<>(dtoList);
    }
}
