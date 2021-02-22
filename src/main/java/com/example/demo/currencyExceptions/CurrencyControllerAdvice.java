package com.example.demo.currencyExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CurrencyControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<CurrencyException> noCurrencyExeption(CurrencyExceptionHandler ex) {

        CurrencyException currencyException = new CurrencyException();

        currencyException.setStatus(HttpStatus.BAD_REQUEST.value());
        currencyException.setMessage(ex.getMessage());
        currencyException.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(currencyException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CurrencyException> noCurrencyExeption(Exception ex) {

        CurrencyException currencyException = new CurrencyException();

        currencyException.setStatus(HttpStatus.BAD_REQUEST.value());
        currencyException.setMessage(ex.getMessage());
        currencyException.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(currencyException, HttpStatus.BAD_REQUEST);
    }
}
