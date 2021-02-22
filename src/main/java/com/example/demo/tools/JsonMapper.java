package com.example.demo.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

    private static ObjectMapper getObjectMapper(){
        ObjectMapper defObjectMapper = new ObjectMapper();

        return defObjectMapper;
    }

    public static JsonNode parse(String source) throws JsonProcessingException {

        return getObjectMapper().readTree(source);
    }

    public static <T> T fromJson(JsonNode node, Class<T> tClass) throws JsonProcessingException {

        return getObjectMapper().treeToValue(node, tClass);
    }
}
