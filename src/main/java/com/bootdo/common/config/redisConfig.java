package com.bootdo.common.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-20 18:39
 **/
@Configuration
public class redisConfig {
    @Value("${spring.redis.host}")
    private String host = "127.0.0.1";
    @Value("${spring.redis.port}")
    private int port = 6379;
    @Value("${spring.redis.timeout}")
    private int timeout = 0;
    @Bean
    public JedisPoolConfig poolConfig() {
        return new JedisPoolConfig();
    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPoolConfig(poolConfig());
        jedisConnectionFactory.setTimeout(timeout);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<Serializable, Object> redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate<Serializable, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

}
