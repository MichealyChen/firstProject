package com.chenyongxiu.firstproject;

import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.dao.borrow.FinancialBorrowDetailMapper;
import com.chenyongxiu.firstproject.redis.RedisSentinelUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
//        redisTemplate.opsForValue().set("123456",16);
//        String[] st={"s","d"};
//        redisTemplate.opsForValue().set("1234567",st);
//        Jedis resource = pool.getResource();
//        String s = resource.get("123456");
//        String s2 = resource.get("1234567");
//        Object ssss = redisTemplate.opsForValue().get("123456");
//        Object ssss2 = redisTemplate.opsForValue().get("1234567");
////
//        System.out.println(ssss);
//        System.out.println(s2);
//        System.out.println(s);
//        System.out.println(ssss2);
    }

    @Test
    public void redisTest22() {
        String[] st={"s","d","555"};
        stringRedisTemplate.opsForValue().set("cyx11", JSONObject.toJSONString(st));
        Object ssss2 = stringRedisTemplate.opsForValue().get("data_type_01");
        System.out.println(ssss2);
    }

    @Test
    public void redisTest242() {
        String[] st={"s","d","5551"};
        redisTemplate.opsForValue().set("cyx12",st);
        Object ssss2 = redisTemplate.opsForValue().get("data_type_01");
        System.out.println(ssss2);
    }

    @Test
    public void redisListTest22() {
        String[] st={"s","d","555"};
        stringRedisTemplate.opsForList().leftPush("cyx123","51");
        stringRedisTemplate.opsForList().leftPush("cyx123","52");
        stringRedisTemplate.opsForList().leftPush("cyx123","53");
        Object ssss2 = stringRedisTemplate.opsForList().range("cyx123",0,-1);
        System.out.println(ssss2);
    }



    @Test
    public void redisTest33() {
        Jedis resource = pool.getResource();
        resource.set("123456","5555");
        String s = resource.get("123456");
        System.out.println(s);
        Set<String> message = null;

        System.out.println(JSONObject.toJSONString(message));
        

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
