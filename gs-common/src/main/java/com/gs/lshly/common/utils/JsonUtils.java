package com.gs.lshly.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {
    private static final ObjectMapper JSON = new ObjectMapper();
    public JsonUtils() {
    }
    public static String toJson(Object obj) {
        try {
            return JSON.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return null;
        }
    }
    static {
        JSON.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
    }
}
