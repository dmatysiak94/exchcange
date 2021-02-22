package com.example.demo.nbpConnector;


import com.example.demo.dto.AvailableCurrencies;

public interface InbpConnector {

    AvailableCurrencies getAvailableCurrencies(String url);
}
