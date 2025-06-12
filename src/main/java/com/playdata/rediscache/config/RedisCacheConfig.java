package com.playdata.rediscache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching // spring 캐싱 기능 활성화. 외부 저장소와 연계해서 캐시 기능을 쓰겠다는 설정.
public class RedisCacheConfig {

    @Bean
    public CacheManager boardCacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 레디스 캐시 기본 설정을 담당하는 객체를 불러옴.
        RedisCacheConfiguration redisCacheConfiguration
                = RedisCacheConfiguration.defaultCacheConfig();

        // Key 직렬화 설정.
        // 직렬화: 자바 객체를 저장/전송 가능한 형태로 변환하는 것
        redisCacheConfiguration.serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new StringRedisSerializer()
                )
        );

        // Value의 직렬화 설정 -> JSON 형태로 직렬화해서 저장.
        redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new Jackson2JsonRedisSerializer<Object>(Object.class)
                )
        );

        // 캐시 데이터의 만료 기간 (Time To Live) 설정
        redisCacheConfiguration.entryTtl(Duration.ofMinutes(1));

        // spring의 CacheManager의 구현체 (Redis용)
        // 접속을 위한 factory, 위에서 설정한 config 정보를 담은 객체를 전달해서 build 후 빈 등록.
        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();

    }

}








