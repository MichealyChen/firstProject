package com.chenyongxiu.firstproject;

import com.chenyongxiu.firstproject.dao.borrow.FinancialBorrowDetailMapper;
import com.chenyongxiu.firstproject.redis.RedisSentinelUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisSentinelUtil util;


    @Resource
    private FinancialBorrowDetailMapper financialBorrowDetailMapper;

    @Autowired
    private JedisSentinelPool pool;


    @Autowired
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer;

    @Test
    public void diamondTest() {
        System.out.println("");
    }

    @Test
    public void contextLoads() {

        List<Map<Object, Object>> maps = financialBorrowDetailMapper.selectMydata();

        System.out.println(maps);
    }

    @Test
    public void redisTest() {
        redisTemplate.opsForValue().set("123456",11);
//        Jedis resource = pool.getResource();
//        String s = resource.get("123456");
        Object ssss = redisTemplate.opsForValue().get("123456");
//
//        System.out.println(ssss);
        System.out.println(ssss);
    }

    @Test
    public void redisTest33() {
        Jedis resource = pool.getResource();
        resource.set("123456","5555");
        String s = resource.get("123456");
        System.out.println(s);

    }

    @Test
    public void redisTest2() {

        boolean cyx = util.setLockV3("cyx", "b4d4a940-89db-4487-abde-da1b616ff50a3",4444111);
        System.out.println(cyx);
    }

    @Test
    public void redisTest44() {

        boolean cyx = util.releaseLock("cyx","b4d4a940-89db-4487-abde-da1b616ff50a3");
        System.out.println(cyx);
    }

    @Test
    public void redisTest112() {

        boolean cyx = util.setLock("cyx", "b4d4a940-89db-4487-abde-da1b616ff50a1",44441111);
        System.out.println(cyx);
    }

    @Test
    public void redisTest3() {

        boolean cyx = util.releaseLock("cyx","b4d4a940-89db-4487-abde-da1b616ff50a");
        System.out.println(cyx);
    }





}
