package com.example.demo.currencyExchangeService;


import com.example.demo.currencyExceptions.CurrencyExceptionHandler;
import com.example.demo.currencyExchangeDao.ICurrencyExchangeDao;
import com.example.demo.dto.AvailableCurrencies;
import com.example.demo.nbpConnector.InbpConnector;
import com.example.demo.tools.CurrencyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CurrencyExchangeServiceImpl implements ICurrencyExchangeService {

    private ICurrencyExchangeDao currencyExchangeDto;
    private InbpConnector nbpConnector;

    @Autowired
    public CurrencyExchangeServiceImpl(ICurrencyExchangeDao currencyExchangeDto,
                                       InbpConnector nbpConnector) {
        this.currencyExchangeDto = currencyExchangeDto;
        this.nbpConnector = nbpConnector;
    }

    private String NBP_URL = "http://api.nbp.pl/api/exchangerates/";

    @Override
    @Transactional
    public List<String> listAll() {
        String url = NBP_URL + "tables/a/";

        AvailableCurrencies availableCurrency = nbpConnector.getAvailableCurrencies(url);

        currencyExchangeDto.saveCurrencyExchangeOperation("/listAllCurrencies", url, null, null);

        return fliterAllCurrenciesList(availableCurrency);
    }

    @Override
    @Transactional
    public Map<String, Double> listActualExchangeRates() {
        String url = NBP_URL + "tables/a/";

        AvailableCurrencies availableCurrency = nbpConnector.getAvailableCurrencies(url);

        currencyExchangeDto.saveCurrencyExchangeOperation("/listActualExchangeRates", url, null, null);

        return currentRates.apply(availableCurrency);
    }

    private boolean isPresent(String code, AvailableCurrencies availableCurrencies) {

        List<String> currencies = fliterAllCurrencyCodesList(availableCurrencies);

        return currencies.stream().anyMatch(cur -> cur.equals(code));
    }

    @Override
    @Transactional
    public BigDecimal calculateExchangeRate(BigDecimal amount, String currencyFrom, String currencyTo) {
        String url = NBP_URL + "tables/a/";

        AvailableCurrencies availableCurrency = nbpConnector.getAvailableCurrencies(url);

        if(!isPresent(currencyFrom, availableCurrency)) {
            throw new CurrencyExceptionHandler("no currency found " + currencyFrom);
        } else if (!isPresent(currencyTo, availableCurrency)) {
            throw new CurrencyExceptionHandler("no currency found " + currencyTo);
        }

        currencyExchangeDto.saveCurrencyExchangeOperation("/listActualExchangeRates", url, currencyFrom, currencyTo);

        CurrencyCalculator currencyCalculator = new CurrencyCalculator();

        return currencyCalculator.calculateExchangeRate(currentRates.apply(availableCurrency), currencyFrom, currencyTo, amount);
    }



    private List<String> fliterAllCurrenciesList (AvailableCurrencies availableCurrencies) {

        return availableCurrencies.getRates().stream()
                .map(rates -> rates.getCurrency())
                .collect(Collectors.toList());
    }

    private List<String> fliterAllCurrencyCodesList (AvailableCurrencies availableCurrencies) {

        return availableCurrencies.getRates().stream()
                .map(rates -> rates.getCode())
                .collect(Collectors.toList());
    }

    private Function <AvailableCurrencies, Map<String, Double>> currentRates = (currency) -> {
        Map<String, Double> exchangeRates = new HashMap<>();

        currency.getRates().forEach(rates -> exchangeRates.put(rates.getCode(), rates.getMid()));

        return exchangeRates;
    };
}
