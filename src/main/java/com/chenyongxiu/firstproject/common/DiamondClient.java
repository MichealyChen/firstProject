package com.chenyongxiu.firstproject.common;
 

import com.chenyongxiu.firstproject.diamond.BasePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化配置信息
 * 
 * @author
 *
 */
@Configuration
public class DiamondClient extends BasePropertyPlaceholderConfigurer implements InitializingBean {
	
	private static DiamondClient diamondClient = new DiamondClient();
	
	public static DiamondClient getInstance() {
		return diamondClient;
	}
	
	public void init(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//		Map<String,String> map0 = new HashMap<String,String>();
//		map0.put("group", "oms");
//		map0.put("dataId", "jdbc");
//		list.add(map0);

		Map<String,String> map1 = new HashMap<String,String>(2);
		map1.put("group", "jdbc");
		map1.put("dataId", "nuwa_cbs");
		list.add(map1);

		Map<String,String> map2 = new HashMap<String,String>(2);
		map2.put("group", "common");
		map2.put("dataId", "redis");
		list.add(map2);

		Map<String,String> map4 = new HashMap<String,String>(2);
		map4.put("group", "common");
		map4.put("dataId", "rocketmq");
		list.add(map4);

		Map<String,String> map6 = new HashMap<String,String>(2);
		map6.put("group", "common");
		map6.put("dataId", "zookeeper");
		list.add(map6);

		Map<String,String> map8 = new HashMap<String,String>(2);
		map8.put("group", "common");
		map8.put("dataId", "yicartrip_oss");
		list.add(map8);

		Map<String,String> map9 = new HashMap<String,String>(2);
		map9.put("group", "common");
		map9.put("dataId", "alisms");
		list.add(map9);

		Map<String,String> xxl = new HashMap<String,String>(2);
		xxl.put("group", "xxl");
		xxl.put("dataId", "cbs");
		list.add(xxl);

		Map<String,String> map10 = new HashMap<String,String>(2);
		map10.put("group", "common");
		map10.put("dataId", "xuanwu");
		list.add(map10);
		
		Map<String,String> huizuAccount = new HashMap<String,String>(2);
		huizuAccount.put("group", "common");
		huizuAccount.put("dataId", "huizuAccount");
		list.add(huizuAccount);

		Map<String,String> font = new HashMap<String,String>(2);
		font.put("group", "common");
		font.put("dataId", "font");
		list.add(font);


		this.loadMultConfigFromDiamond(list);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
}
