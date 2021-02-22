package com.example.demo.currencyExchangeService;

import com.example.demo.currencyExceptions.CurrencyExceptionHandler;
import com.example.demo.currencyExchangeDao.ICurrencyExchangeDao;
import com.example.demo.dto.AvailableCurrencies;
import com.example.demo.dto.Rates;
import com.example.demo.nbpConnector.InbpConnector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeServiceImplTest {

    private String SAMPLE_URL = "http://api.nbp.pl/api/exchangerates/tables/a/";

    private AvailableCurrencies availableCurrencies;
    private Rates rates1;
    private Rates rates2;
    private Rates rates3;

    @Mock
    private ICurrencyExchangeDao currencyExchangeDao;

    @Mock
    private InbpConnector nbpConnector;

    private ICurrencyExchangeService currencyExchangeService;

    @BeforeEach
    public void setUp() {
        currencyExchangeService = new CurrencyExchangeServiceImpl(currencyExchangeDao, nbpConnector);

        rates1 = new Rates();
        rates2 = new Rates();
        rates3 = new Rates();
        List<Rates> ratesList = new ArrayList<>();
        availableCurrencies = new AvailableCurrencies();

        rates1.setCode("USD");
        rates2.setCode("EUR");
        rates3.setCode("GBP");
        rates1.setCurrency("United States Dollar");
        rates2.setCurrency("Euro");
        rates3.setCurrency("British Pound");
        rates1.setMid(3.53);
        rates2.setMid(4.54);
        rates3.setMid(6.32);

        ratesList.add(rates1);
        ratesList.add(rates2);
        ratesList.add(rates3);

        availableCurrencies.setRates(ratesList);
    }

    @Test
    public void listAllTest() {

        Mockito.when(nbpConnector.getAvailableCurrencies(SAMPLE_URL)).thenReturn(availableCurrencies);

        Mockito.doNothing().when(currencyExchangeDao).saveCurrencyExchangeOperation("/listAllCurrencies", SAMPLE_URL, null, null);

        List<String> currenciesNames = currencyExchangeService.listAll();

        Assertions.assertThat(currenciesNames.get(0)).isEqualTo("United States Dollar");
    }

    @Test
    public void listActualExchangeRates() {

        Mockito.when(nbpConnector.getAvailableCurrencies(SAMPLE_URL)).thenReturn(availableCurrencies);

        Mockito.doNothing().when(currencyExchangeDao).saveCurrencyExchangeOperation("/listActualExchangeRates", SAMPLE_URL, null, null);

        Map<String, Double> currencyRates = currencyExchangeService.listActualExchangeRates();

        Assertions.assertThat(currencyRates.get("EUR")).isEqualTo(4.54);
    }

    @Test
    public void calculateExchangeRate() {

        Mockito.when(nbpConnector.getAvailableCurrencies(SAMPLE_URL)).thenReturn(availableCurrencies);

        BigDecimal bigDecimalGiven = new BigDecimal("4.50");
        BigDecimal bigDecimalExpected = new BigDecimal("5.79");

        Mockito.doNothing().when(currencyExchangeDao).saveCurrencyExchangeOperation("/listActualExchangeRates", SAMPLE_URL, "EUR", "USD");

        BigDecimal exchangeAmount = currencyExchangeService.calculateExchangeRate(new BigDecimal("4.50"), "EUR", "USD");



        Assertions.assertThat(exchangeAmount).isEqualTo(bigDecimalExpected);
    }

    @Test
    public void calculateExchangeRate_throwException() {

        Mockito.when(nbpConnector.getAvailableCurrencies(SAMPLE_URL)).thenReturn(availableCurrencies);

        BigDecimal bigDecimal = new BigDecimal("4.50");

        assertThrows(CurrencyExceptionHandler.class, () ->
                currencyExchangeService.calculateExchangeRate(bigDecimal, "XXXXXX", "USD"));

    }

}
