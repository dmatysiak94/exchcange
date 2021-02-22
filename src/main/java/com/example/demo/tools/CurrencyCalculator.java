package com.example.demo.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CurrencyCalculator {

    public BigDecimal calculateExchangeRate(Map<String, Double> currentRates, String currencyFrom, String currencyTo, BigDecimal amount){
        BigDecimal from = new BigDecimal(currentRates.get(currencyFrom).toString());
        BigDecimal to = new BigDecimal(currentRates.get(currencyTo).toString());
        if (currencyFrom.equals(currencyTo)){
            return amount;
        }
        BigDecimal convertedCurrnecy = from.divide(to, 4, RoundingMode.HALF_UP);
        BigDecimal timesAmount = convertedCurrnecy.multiply(amount);
        BigDecimal shorter = timesAmount.setScale(2, RoundingMode.HALF_UP);

        return shorter;
    }

}
