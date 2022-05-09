package com.taogen.demo.springdataredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 * @author Taogen
 */
@Configuration
public class LettuceConfig {
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
//    }

    /**
     * If you need less verbose configuration, you can remove LettuceConnectionFactory bean from your configuration code and just inject the LettuceConnectionFactory bean in your redisTemplate.
     * lettuceConnectionFactory will be populated with properties from application.yml
     *
     * @param lettuceConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        // By default, RedisCache and RedisTemplate are configured to use Java native serialization. Java native serialization is known for allowing the running of remote code caused by payloads that exploit vulnerable libraries and classes injecting unverified bytecode. Manipulated input could lead to unwanted code being run in the application during the deserialization step. As a consequence, do not use serialization in untrusted environments. In general, we strongly recommend any other message format (such as JSON) instead.
        // redis key use normal string not hex string
        redisTemplate.setKeySerializer(new GenericToStringSerializer<String>(String.class));
        // redis value use normal string not hex string
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }
}
