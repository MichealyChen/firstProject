package com.chenyongxiu.firstproject;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class UatJedisPoolUtil {

    private static JedisSentinelPool pool = null;

    /**
     * 创建连接池
     *
     */
    @Before
    public   void createJedisPool() {
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(1113);
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(55);
        // 设置空间连接
        config.setMaxIdle(65);
        // jedis实例是否可用
        boolean borrow = true;
        config.setTestOnBorrow(borrow);
        // 创建连接池
//      pool = new JedisPool(config, prop.getProperty("ADDR"), StringUtil.nullToInteger(prop.getProperty("PORT")), StringUtil.nullToInteger(prop.getProperty("TIMEOUT")));// 线程数量限制，IP地址，端口，超时时间
        //获取redis密码

        String masterName = "r2";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.2.126:56379");
        sentinels.add("192.168.2.128:56379");
        sentinels.add("192.168.2.143:56379");
        pool = new JedisSentinelPool(masterName, sentinels, config);
    }

    /**
     * 在多线程环境同步初始化
     */
    public synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    public Jedis getJedis() {
        if (pool == null)
            poolInit();
        return pool.getResource();
    }




    @Test
    public  void mainqq(){
        Jedis jedis=getJedis();
//        jedis.set("123456","ww");
        String aa = jedis.get("123456");
        System.out.println(aa);
    }
}
