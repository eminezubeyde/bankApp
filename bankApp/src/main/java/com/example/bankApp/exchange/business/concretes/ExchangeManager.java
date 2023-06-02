package com.example.bankApp.exchange.business.concretes;

import com.example.bankApp.common.core.exception.AmountNotValidException;
import com.example.bankApp.exchange.business.abstracts.ExchangeService;
import com.example.bankApp.exchange.core.constant.ExchangeConstant;
import com.example.bankApp.exchange.core.model.Exchange;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExchangeManager implements ExchangeService {
    private final RestTemplate restTemplate;

    @Override
    public Exchange getExchangeAmount(String toCurrency, String fromCurrency, BigDecimal amount) throws AmountNotValidException {
        if(amount.compareTo(BigDecimal.ZERO)<0){
            throw new AmountNotValidException("Amount Not Valid");
        }

        String url = ExchangeConstant.API_LAYER_URL
                + "?to=" + toCurrency + "&from=" + fromCurrency + "&amount=" + amount;

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey",ExchangeConstant.API_LAYER_KEY);
        HttpEntity<String> entity=new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET,entity, Exchange.class).getBody();
    }
}
