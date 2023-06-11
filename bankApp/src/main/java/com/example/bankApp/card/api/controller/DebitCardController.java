package com.example.bankApp.card.api.controller;

import com.example.bankApp.card.service.DebitCardService;
import com.example.bankApp.card.core.dto.requests.CreateDebitCardRequest;
import com.example.bankApp.common.core.exception.AlreadyExistsException;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.result.GeneralResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/debitCard")
public class DebitCardController {
    private final DebitCardService debitCardService;

    @PostMapping
    public GeneralResult create(@RequestParam int checkingAccountId, @RequestBody CreateDebitCardRequest createDebitCardRequest) throws EntityNotFoundException, AlreadyExistsException {
        return debitCardService.create(checkingAccountId,createDebitCardRequest);
    }

    @DeleteMapping
    public GeneralResult delete(@PathVariable int debitCardId) throws EntityNotFoundException {
        return debitCardService.delete(debitCardId);
    }
}
