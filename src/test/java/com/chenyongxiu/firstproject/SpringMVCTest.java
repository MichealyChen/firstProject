//package com.chenyongxiu.firstproject;
//
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.chenyongxiu.firstproject.dao.UserMapper;
//import com.chenyongxiu.firstproject.entity.LBReceiptRecordPO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.concurrent.locks.Condition;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringMVCTest {
//
//    @Autowired
//    private UserMapper userMapper;
//
//
//    @Test
//    public void testSelectOne() {
//        LBReceiptRecordPO user = userMapper.selectById(1001L);
//        System.out.println(user);
//    }
//
//    @Test
//    public void testSelectOne2() {
//        QueryWrapper<LBReceiptRecordPO> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.eq("receipt_record_id",1001L);
//        List<LBReceiptRecordPO> lbReceiptRecordPOS = userMapper.selectList(objectQueryWrapper);
//        for (LBReceiptRecordPO lb:lbReceiptRecordPOS){
//            System.out.println(lb);
//        }
//    }
//}
