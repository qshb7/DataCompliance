package com.example.datacompliance.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    public static String toJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
