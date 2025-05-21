package com.example.servey_service.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
@Slf4j
public class RedisCacheConfig {

    @Bean("cacheManager")
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory factory) {

        ObjectMapper om = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 전체 타입에 @class 메타정보 허용
        om.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.EVERYTHING,
                JsonTypeInfo.As.PROPERTY
        );

        // 커스텀 JSON Serializer 생성
        CustomRedisJsonSerializer ser = new CustomRedisJsonSerializer(om);

        // RedisCacheConfiguration 설정
        RedisCacheConfiguration cfg = RedisCacheConfiguration.defaultCacheConfig()
                                                             // 키 직렬화: String
                                                             .serializeKeysWith(RedisSerializationContext.SerializationPair
                                                                     .fromSerializer(new StringRedisSerializer()))
                                                             // 값 직렬화: JSON
                                                             .serializeValuesWith(RedisSerializationContext.SerializationPair
                                                                     .fromSerializer(ser))
                                                             // TTL 설정: 15초
                                                             .entryTtl(Duration.ofSeconds(15));

        return RedisCacheManager.builder(factory)
                                .cacheDefaults(cfg)
                                .build();
    }
}
