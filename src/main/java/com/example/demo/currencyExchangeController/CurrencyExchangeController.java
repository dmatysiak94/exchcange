package com.example.demo.currencyExchangeController;


import com.example.demo.currencyExceptions.CurrencyExceptionHandler;
import com.example.demo.currencyExchangeService.ICurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/exchange")
public class CurrencyExchangeController {

    private ICurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeController (ICurrencyExchangeService currencyExchangeService){
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/listAllCurrencies")
    public List<String> listAll() {

        return currencyExchangeService.listAll();
    }

    @GetMapping("/listActualExchangeRates")
    public Map<String, Double> listActualExchangeRates() {

        return currencyExchangeService.listActualExchangeRates();
    }

    @GetMapping("/calculateExchangeRate")
    public BigDecimal calculateExchangeRate(@RequestParam(value = "amount") BigDecimal amount,
                                          @RequestParam(value = "currencyFrom") String currencyFrom,
                                          @RequestParam(value = "currencyTo") String currencyTo) {

        if((amount.compareTo(BigDecimal.ZERO) < 0) || (amount.compareTo(BigDecimal.ZERO) == 0)){
            throw new CurrencyExceptionHandler("amount must be greater than 0");
        }

        return currencyExchangeService.calculateExchangeRate(amount, currencyFrom.toUpperCase(), currencyTo.toUpperCase());
    }
}
