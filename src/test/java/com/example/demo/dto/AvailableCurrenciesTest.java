package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static com.example.demo.dto.DtoTestUtils.validateAccessors;


class AvailableCurrenciesTest {
    @Test
    public void testAccessors() {
        validateAccessors(Rates.class);
    }
}
