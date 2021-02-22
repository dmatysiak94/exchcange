package com.example.demo.nbpConnector;

import com.example.demo.dto.AvailableCurrencies;
import com.example.demo.httpConnector.HttpConnector;
import com.example.demo.tools.JsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class NbpConnector implements InbpConnector{

    @Override
    public AvailableCurrencies getAvailableCurrencies(String url) {
        AvailableCurrencies availableCurrency = new AvailableCurrencies();
        try {

            String a = HttpConnector.readJsonToString(url);
            JSONArray arrayOfAvailableCurrencies = new JSONArray(a);

            JsonNode node = JsonMapper.parse((String) arrayOfAvailableCurrencies.getJSONObject(0).toString());
            availableCurrency = JsonMapper.fromJson(node, AvailableCurrencies.class);

        } catch (Exception ex){
            ex.printStackTrace();
        }

        return availableCurrency;
    }
}
