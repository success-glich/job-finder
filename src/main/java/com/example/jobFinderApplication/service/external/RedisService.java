package com.example.jobFinderApplication.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    private final RedisTemplate<String,String>  redisTemplate;

    public  RedisService(RedisTemplate<String,String>  redisTemplate){
        this.redisTemplate = redisTemplate;

    }

//    public <T> T get(String key, Class<T> entityClass) {
//        Object o = redisTemplate.opsForValue().get(key);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            return objectMapper.readValue(Objects.requireNonNull(o).toString(), entityClass);
//        } catch (JsonProcessingException e) {
//
//            log.error("Error while converting object to JSON: {}", e.getMessage());
//            return null;
//        }
//
//    }


public <T> T get(String key, Class<T> entityClass) {
    String value = redisTemplate.opsForValue().get(key);

    if (value == null) {
        return null;
    }
    ObjectMapper objectMapper = new ObjectMapper();

    try {
        return objectMapper.readValue(value, entityClass);
    } catch (JsonProcessingException e) {
        log.error("Error while converting object to JSON: {}", e.getMessage());
        return null;
    }
}

    public void set(String key, Object o, Long ttl) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }

}
