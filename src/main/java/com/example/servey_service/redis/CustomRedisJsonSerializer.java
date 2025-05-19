package com.example.servey_service.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;


public class CustomRedisJsonSerializer extends GenericJackson2JsonRedisSerializer {

    public CustomRedisJsonSerializer(ObjectMapper objectMapper) {
        super(objectMapper);
    }
}
