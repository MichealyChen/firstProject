package com.chenyongxiu.firstproject;


import com.chenyongxiu.firstproject.common.DataUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//import com.example.myproject.redis.RedisSentinelUtil;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MyTest {

    @Autowired
    private JedisSentinelPool pool;

//    @Autowired
//    private RedisSentinelUtil util;

//    @Autowired
//    private StringRedisTemplate redis;



    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    public Jedis getJedis() {
        return pool.getResource();
    }

//    @Test
    public void redistest(){
        getJedis().set("ssss11","44444444");
        System.out.println(getJedis().get("ssss11"));
    }

//    @Test
//    public void redistest2(){
//
//        RedisSentinelUtil sentinelUtil = new RedisSentinelUtil();
//        Jedis jedis = sentinelUtil.getJedis();
//
//        jedis.set("ssss11","44444444");
//        System.out.println(jedis.get("ssss11"));
//    }

//    @Test
//    public void redisTest(){
//        redis.opsForValue().set("ssss11","44444444");
//        System.out.println(redis.opsForValue().get("ssss11"));
//    }

    @Test
    public void redisTest2() throws NoSuchMethodException {

        Method method = MyTest.class.getDeclaredMethod("redistest");
        System.out.println(method);
    }

    public static void main(String[] args) {
        String x = new String("A");
        String y = new String("B");
        getOprate(x, y);
        System.out.println("x "+x);
        System.out.println("x1 "+y);
    }
    public void test() {}
    private static void getOprate(String x, String y) {
//        x.(y);
        y=x;
        System.out.println("x2 "+y);
    }

//    public static void main(String[] args) {
//        List<String> l1 = new ArrayList<>();
//        List<String> l2 = new ArrayList<>();
//        l1.add("111");
//        l2.add("222");
//        getOprate1(l1, l2);
//        System.out.println("x "+l1);
//        System.out.println("x1 "+l2);
//    }


    private static void getOprate1(List<String> x, List<String> y) {
        x.addAll(y);
        y=x;
        y.add("333");
        System.out.println("x2 "+y);
    }

    @Test
    public void redisTest222() {

        String s="3333";
        long count = s.chars().count();
        boolean b = s.chars().anyMatch(Character::isUpperCase);
        ArrayList<Object> lis = Lists.newArrayList();
//        IntStream.range(1,5).forEach(x-> System.out.println(s));

        System.out.println(DataUtil.isNumber(s));


    }

    private boolean isNumber(String string) {
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        return string.chars().allMatch(Character::isDigit);
    }

}
