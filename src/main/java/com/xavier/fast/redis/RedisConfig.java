package com.xavier.fast.redis;

import com.xavier.fast.properties.JedisProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2015/5/30.
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private JedisProperties properties;

	@Primary
    @Bean
    public JedisPool redisPoolFactory() {
        String ip =  properties.getHost();
        Integer port =  properties.getPort();
        String password =  properties.getPassword();
        
        password = StringUtils.isNotEmpty(password)?password:null;
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxWaitMillis(-1);
        return new JedisPool(jedisPoolConfig, ip, port, 0, password, 0);
         
    }
    
}
