package com.example.bankApp.exchange.business.abstracts;

import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.exchange.business.concretes.ExchangeManager;
import com.example.bankApp.exchange.core.model.Exchange;

import java.math.BigDecimal;
import java.security.spec.ECField;

public interface ExchangeService {
     Exchange getExchangeAmount(String toCurrency, String fromCurrency, BigDecimal amount) throws AmountNotValidException;
}
