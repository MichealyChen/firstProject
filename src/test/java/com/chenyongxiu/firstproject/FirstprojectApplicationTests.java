package com.chenyongxiu.firstproject;

import org.junit.Test;
import org.junit.runner.RunWith;
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
	@Test
	public void contextLoads() {
		System.out.println(port);
		System.out.println(master);
	}

}
