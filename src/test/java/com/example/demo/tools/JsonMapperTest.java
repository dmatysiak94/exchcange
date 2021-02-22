package com.example.demo.tools;

import com.example.demo.dto.AvailableCurrencies;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class JsonMapperTest {

    private String jsonTestCase = "{\"table\":\"A\",\"no\":\"034/A/NBP/2021\",\"effectiveDate\":\"2021-02-19\",\"rates\":[{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1233},{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"mid\":3.6997},{\"currency\":\"dolar australijski\",\"code\":\"AUD\",\"mid\":2.8965}]}";
    private AvailableCurrencies availableCurrency;

    @BeforeEach
    void setUp() {
        this.availableCurrency = new AvailableCurrencies();
    }

    @Test
    void fromJsonTest() throws JsonProcessingException {

        JsonNode node = JsonMapper.parse(jsonTestCase);

        AvailableCurrencies availableCurrency = JsonMapper.fromJson(node, AvailableCurrencies.class);

        Assertions.assertThat(availableCurrency.getRates().size()).isEqualTo(3);
    }

}

