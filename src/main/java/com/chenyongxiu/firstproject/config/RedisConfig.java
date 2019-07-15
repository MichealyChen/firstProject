package com.chenyongxiu.firstproject.config;

import com.chenyongxiu.firstproject.properties.RedisProperties1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableCaching
public class RedisConfig {

    @Autowired
    private RedisProperties1 redisProperties;

    @Bean
    public JedisPoolConfig getPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getMaxIdle());
        poolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(60000);
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        poolConfig.setNumTestsPerEvictionRun(-1);
        return poolConfig;
    }


    @Bean
    public JedisSentinelPool getSentinelCluster(JedisPoolConfig jedisPoolConfig) {
        String masterName = redisProperties.getMaster();
        List<String> sentinels = redisProperties.getNodes();
        return new JedisSentinelPool(masterName, new HashSet<>(sentinels), jedisPoolConfig);
    }

    @Bean
    public RedisConnectionFactory connectionFactory(JedisPoolConfig jedisPoolConfig) {
        String master = redisProperties.getMaster();
        List<String> nodes = redisProperties.getNodes();
        Set<String> nodesSet = new HashSet<>(nodes);
        RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration(master, nodesSet);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisConfig, jedisPoolConfig);
        return jedisConnectionFactory;
    }

}
