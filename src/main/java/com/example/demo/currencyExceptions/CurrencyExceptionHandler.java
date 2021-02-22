package com.example.demo.currencyExceptions;

public class CurrencyExceptionHandler extends RuntimeException {

    public CurrencyExceptionHandler (String s) {
        super(s);
    }
}
