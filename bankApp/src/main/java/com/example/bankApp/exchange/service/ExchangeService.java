package com.example.bankApp.exchange.service;

import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.exchange.core.model.Exchange;

import java.math.BigDecimal;

public interface ExchangeService {
     Exchange getExchangeAmount(String toCurrency, String fromCurrency, BigDecimal amount) throws AmountNotValidException;
}
