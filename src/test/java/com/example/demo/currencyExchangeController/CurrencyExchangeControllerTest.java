package com.example.demo.currencyExchangeController;

import com.example.demo.currencyExchangeService.ICurrencyExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeControllerTest {

    private String AMOUNT = String.valueOf(3.54);
    private String CURRENCY_FROM = "EUR";
    private String CURRENCY_TO = "USD";

    private MockMvc mockMvc;

    @Mock
    private ICurrencyExchangeService currencyExchangeService;

    @InjectMocks
    private CurrencyExchangeController currencyExchangeController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(currencyExchangeController).build();
    }

    @Test
    public void listAllTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/app/exchange/listAllCurrencies")
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    public void listActualExchangeRatesTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/app/exchange/listActualExchangeRates")
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    public void calculateExchangeRate() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/app/exchange/calculateExchangeRate")
                        .param("amount", AMOUNT)
                        .param("currencyFrom", CURRENCY_FROM)
                        .param("currencyTo", CURRENCY_TO)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

}
