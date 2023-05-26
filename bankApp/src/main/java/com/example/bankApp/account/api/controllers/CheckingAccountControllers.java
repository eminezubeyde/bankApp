package com.example.bankApp.account.api.controllers;

import com.example.bankApp.account.business.abstracts.CheckingAccountService;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.result.GeneralResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/checkingAccount")
@RequiredArgsConstructor
public class CheckingAccountControllers {
    private final CheckingAccountService checkingAccountService;

    @PostMapping
    public GeneralResult create(@RequestParam int id, @RequestBody CreateCheckingAccountRequest request) throws EntityNotFoundException {
        return checkingAccountService.add(id, request);
    }
    @DeleteMapping
    public void delete(@RequestParam int id) throws GeneralException {
        checkingAccountService.delete(id);
    }
    @GetMapping
    public GeneralResult getAll(){
        return  checkingAccountService.getAll();
    }
    @GetMapping("/customers/{customerId}")
    public GeneralResult getAllCheckingAccountsByCustomerId(@PathVariable int customerId) throws EntityNotFoundException {
        return checkingAccountService.getAllCheckingAccountsByCustomerId(customerId);
    }
    @GetMapping("/{id}")
    public GeneralResult getById(@PathVariable int id) throws EntityNotFoundException {
        return checkingAccountService.getById(id);
    }
    @GetMapping("currencyType/{currencyType}")
    public GeneralResult getCheckingAccountByCurrencyType(@PathVariable("currencyType") String currencyType){
        return checkingAccountService.getCheckingAccountsByCurrencyType(currencyType);
    }
}
