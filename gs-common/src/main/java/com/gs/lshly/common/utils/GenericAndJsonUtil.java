package com.gs.lshly.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.lshly.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author Starry
 * @Date 16:56 2020/10/10
 */
@Component
@Slf4j
public class GenericAndJsonUtil {
    private static ObjectMapper mapper;

    /**
     * 采用set方法自动注入
     * @param mapper
     */
    @Autowired
    public void setMapper(ObjectMapper mapper) {
        GenericAndJsonUtil.mapper = mapper;
    }

    public static<T> String objectToJson(T o){
        try {
            String jsonString = mapper.writeValueAsString(o);
            return jsonString;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new BusinessException("异常");
        }
    }
    public static<T> T jsonToObject(String json, TypeReference<T>typeReference){
        try {
            if (json.isEmpty()){
                return null;
            }
            T t = mapper.readValue(json, typeReference);
            return t;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BusinessException("异常");
        }
    }
}

