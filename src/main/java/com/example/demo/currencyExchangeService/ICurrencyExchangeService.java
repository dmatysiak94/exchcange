package com.example.demo.currencyExchangeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ICurrencyExchangeService {

    List<String> listAll();

    Map<String, Double> listActualExchangeRates();

    BigDecimal calculateExchangeRate(BigDecimal amount, String currencyFrom, String currencyTo);
}
