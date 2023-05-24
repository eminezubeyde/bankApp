package com.example.bankApp.account.api.controllers;

import com.example.bankApp.account.business.abstracts.CheckingAccountService;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.common.core.result.GeneralResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/checkingAccount")
@RequiredArgsConstructor
public class CheckingAccountControllers {
    private final CheckingAccountService checkingAccountService;

    @PostMapping
    public GeneralResult create(@RequestParam int id, @RequestBody CreateCheckingAccountRequest request) {
        return checkingAccountService.add(id, request);
    }
    @DeleteMapping
    public void delete(@RequestParam int id){
        checkingAccountService.delete(id);
    }
    @GetMapping
    public GeneralResult getAll(){
        return  checkingAccountService.getAll();
    }
    @GetMapping("{customerId}")
    public GeneralResult getAllCheckingAccountsByCustomerId(@PathVariable int customerId){
        return checkingAccountService.getAllCheckingAccountsByCustomerId(customerId);
    }
    @GetMapping("{id}")
    public GeneralResult getById(@PathVariable int id) {
        return checkingAccountService.getById(id);
    }
    @GetMapping("/currencyType")
    public GeneralResult getCheckingAccountByCurrencyType(@RequestParam String currencyType){
        return checkingAccountService.getCheckingAccountsByCurrencyType(currencyType);
    }
}
