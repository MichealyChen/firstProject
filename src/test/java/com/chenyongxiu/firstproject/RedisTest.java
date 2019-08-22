package com.chenyongxiu.firstproject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class RedisTest {

    Jedis jedis=null;

    @Before
    public void Before(){

        jedis = new Jedis("192.168.1.232",6379);
    }

    @Test
    public void test(){


        jedis.decr("ssss");

        System.out.println(jedis.get("ssss"));
    }

    @Test
    public void tesqq(){
        String ss="52200";

        ss = String.format("%05d", Integer.parseInt(ss));
        System.out.println(ss);
    }

    @Test
    public void test33(){
        HashMap<String, Object> m = new HashMap<>();
        m.put("1",5555);
        m.put("2","55444");
        System.out.println(m);
        System.out.println(m.clone());
    }

    @Test
    public void test2(){
        String s="ssss";


        Test33 test33 = new Test33();
        System.out.println(test33.toString());
        try {
             Object clone = test33.clone();
            System.out.println(clone.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    // Contains a reference of Test and implements
// clone with shallow copy.
    class Test33 implements Cloneable
    {
        int a=5;
        int b=6;

        @Override
        public String toString() {
            return "Test33{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }

        public Test33 clone() throws
                CloneNotSupportedException
        {
            return (Test33) super.clone();
        }
    }


    @After
    public void after(){

    }
}
