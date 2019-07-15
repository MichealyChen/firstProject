package com.chenyongxiu.firstproject.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@Data
@PropertySource(value = "classpath:/application-redis.yml")
@ConfigurationProperties(prefix = "spring.cache")
public class RedisProperties1 {

    /**
     * Redis server host.
     */
    private String host = "192.168.1.232";

    /**
     * Login password of the redis server.
     */
    private String password;

    /**
     * Redis server port.
     */
    private int port = 6379;

    private String master;

    private int database;

    /**
     * Connection timeout.
     */
    private Duration timeout;


    private List<String> nodes;

    private int maxIdle ;


    private int minIdle ;


    private int maxActive ;

    private long maxWaitMillis ;

}
