package com.evol.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisConfig {
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    //创建JedisPool
    @Bean
    public JedisPool redisPool(){
        JedisPool jedisPool = new JedisPool(jedisConnectionFactory.getPoolConfig());
        return jedisPool;
    }
}
