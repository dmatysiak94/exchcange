package com.example.demo.currencyExchangeDao;

import java.math.BigDecimal;

public interface ICurrencyExchangeDao {

    void saveCurrencyExchangeOperation(String appEndpoint, String nbpEndpoint,
                                       String currencyFrom, String currencyTo);

}
