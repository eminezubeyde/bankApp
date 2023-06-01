package com.example.bankApp.account.api.controllers;

import com.example.bankApp.account.business.abstracts.SavingAccountService;
import com.example.bankApp.account.core.dto.requests.CreateCheckingAccountRequest;
import com.example.bankApp.account.core.dto.requests.SavingAccountRequest;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.exception.GeneralException;
import com.example.bankApp.common.core.result.GeneralResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/savingAccount")
@RequiredArgsConstructor
public class SavingAccountControllers {
    private final SavingAccountService savingAccountService;

    //TODO CRUD işlemler
    @PostMapping
    public GeneralResult create(@RequestParam int checkingAccountId,
                                @RequestBody SavingAccountRequest request) throws GeneralException {
        return savingAccountService.create(checkingAccountId, request);
    }

    @GetMapping
    public GeneralResult getAll() {
        return savingAccountService.getAll();
    }

    @GetMapping("/{id}")
    public GeneralResult getById(@PathVariable int id) throws EntityNotFoundException {
        return savingAccountService.getById(id);
    }

    @GetMapping("/customers/{customerId}")
    public GeneralResult getAllSavingAccountsByCustomerId(@PathVariable int customerId) throws EntityNotFoundException {
        return savingAccountService.getAllSavingAccountsByCustomerId(customerId);
    }
    @GetMapping("{savingAccountId}/accountActivities")// api/savingAccount/2/accountActivities
    public GeneralResult getAllAccountActivitiesBySavingAccountId(@PathVariable int savingAccountId) throws EntityNotFoundException {
        return savingAccountService.getAllAccountActivitiesBySavingAccountId(savingAccountId);
    }

    @DeleteMapping("/close/{savingAccountId}")//http://localhost:8090/api/savingAccount/close/4
    public GeneralResult savingAccountClose(@PathVariable int savingAccountId) throws EntityNotFoundException {
        return savingAccountService.savingAccountClose(savingAccountId);
    }

}
