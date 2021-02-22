package com.example.demo.tools;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class CurrencyCalculatorTest {

    @ParameterizedTest
    @MethodSource("parametersForCalculateExchangeRateTest")
    public void calculateExchangeRateTest(Map<String, Double> currentrates, String currencyFrom, String currencyTo, BigDecimal amount, BigDecimal exprctedresult) {

        CurrencyCalculator currencyCalculator = new CurrencyCalculator();
        BigDecimal calcResult = currencyCalculator.calculateExchangeRate(currentrates, currencyFrom, currencyTo, amount);

        Assertions.assertThat(calcResult).isEqualTo(exprctedresult);

    }

    private static Stream<Arguments> parametersForCalculateExchangeRateTest() {

        Map<String, Double> params1 = new HashMap<>();
        params1.put("CHF", 4.1377);
        params1.put("HRK", 0.5924);

        Map<String, Double> params2 = new HashMap<>();
        params2.put("HRK", 0.5924);
        params2.put("CHF", 4.1377);

        Map<String, Double> params3 = new HashMap<>();
        params3.put("HRK", 0.5924);
        params3.put("JPY", 0.035108);

        Map<String, Double> params4 = new HashMap<>();
        params4.put("CHF", 4.1377);
        params4.put("GBP", 5.1755);

        Map<String, Double> params5 = new HashMap<>();
        params5.put("GBP", 5.1755);
        params5.put("GBP", 5.1755);

        return Stream.of(
                Arguments.of(params1, "CHF", "HRK", new BigDecimal("3.0"), new BigDecimal("20.95")),
                Arguments.of(params2, "HRK", "CHF", new BigDecimal("7.0"), new BigDecimal("1.00")),
                Arguments.of(params3, "HRK", "JPY", new BigDecimal("5.54"), new BigDecimal("93.48")),
                Arguments.of(params4, "CHF", "GBP", new BigDecimal("8.20"), new BigDecimal("6.56")),
                Arguments.of(params5, "GBP", "GBP", new BigDecimal("8.20"), new BigDecimal("8.20"))
        );
    }
}
