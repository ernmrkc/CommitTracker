package org.ernmrkc.committracker.Helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String strTarget, Class<T> valueType){
        try{
            return objectMapper.readValue(strTarget, valueType);
        }catch (JsonProcessingException e){
            throw new RuntimeException("JSON Parse Error");
        }
    }
}
