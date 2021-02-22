package com.example.demo.currencyExchangeEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "currency_api_ussage", schema = "public")
public class CurrencyApiUssage {

    public CurrencyApiUssage() {
    }

    public CurrencyApiUssage(String applicationEndpoint, String nbpEndpoint, String currencyFrom, String currencyto) {
        this.applicationEndpoint = applicationEndpoint;
        this.nbpEndpoint = nbpEndpoint;
        this.currencyFrom = currencyFrom;
        this.currencyto = currencyto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "application_endpoint")
    private String applicationEndpoint;

    @Column(name = "nbp_endpoint")
    private String nbpEndpoint;

    @Column(name = "currency_from")
    private String currencyFrom;

    @Column(name = "currency_to")
    private String currencyto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplicationEndpoint() {
        return applicationEndpoint;
    }

    public void setApplicationEndpoint(String applicationEndpoint) {
        this.applicationEndpoint = applicationEndpoint;
    }

    public String getNbpEndpoint() {
        return nbpEndpoint;
    }

    public void setNbpEndpoint(String nbpEndpoint) {
        this.nbpEndpoint = nbpEndpoint;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyto() {
        return currencyto;
    }

    public void setCurrencyto(String currencyto) {
        this.currencyto = currencyto;
    }
}


