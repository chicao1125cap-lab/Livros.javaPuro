package com.exemplo.livros.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() {
    }

    public static <T> T fromJson(String json, Class<T> tipo) throws IOException {
        return MAPPER.readValue(json, tipo);
    }

    public static String toJson(Object objeto) throws IOException {
        return MAPPER.writeValueAsString(objeto);
    }
}
