package com.razorpay.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.exception.CustomException;

public class PropertyUtil {
    private PropertyUtil(){}

    public static <I,O> O mapJsonInto(I source, Class<O> destination) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(source.toString(),destination);
        } catch (JsonProcessingException e) {
            throw new CustomException(e.getMessage());
        }
    }
}
