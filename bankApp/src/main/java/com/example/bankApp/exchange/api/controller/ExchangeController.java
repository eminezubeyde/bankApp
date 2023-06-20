package com.example.bankApp.exchange.api.controller;

import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.exchange.service.ExchangeService;
import com.example.bankApp.exchange.core.model.Exchange;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;

    @GetMapping
    public Exchange exchange(@RequestParam String toCurrency
            , @RequestParam String fromCurrency
            , @RequestParam BigDecimal amount) throws AmountNotValidException {
        return exchangeService.getExchangeAmount(toCurrency, fromCurrency, amount);
    }
}
