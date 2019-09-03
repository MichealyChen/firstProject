package com.chenyongxiu.firstproject;

import com.chenyongxiu.firstproject.dao.UserMapper;
import com.chenyongxiu.firstproject.entity.LBReceiptRecordPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstprojectApplicationTests {


	@Value("${redis.encode}")
	private String port;

	@Value("${spring.cache.master}")
	private String master;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		System.out.println(port);
		System.out.println(master);
	}

	@Test
	public void testSelectOne() {
		LBReceiptRecordPO user = userMapper.selectById(1001L);
		System.out.println(user);
	}

}
