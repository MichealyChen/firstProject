package com.chenyongxiu.firstproject.diamond;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.Executor;

@Component
public class DiamondProperty extends PropertyPlaceholderConfigurer implements InitializingBean {
	private long timeOut = 5000;		//超时5秒钟获取一次最新配置
	private static Properties properties = new Properties();
	private static String DEF_DEMAIN_LIST = "a.b.c";//默认动态配置服务器所在IP
	private static int DEF_PORT = 8080;//默认动态配置服务器端口

//    @Bean
//    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer(Properties properties) {
//        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
//        configurer.setProperties(properties);
//        return configurer;
//    }

	public void init() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<>(3);
		map1.put("group", "jdbc");
		map1.put("dataId", "nuwa_cbs");
		list.add(map1);

		Map<String, String> map2 = new HashMap<>(3);
		map2.put("group", "common");
		map2.put("dataId", "redis");
		list.add(map2);

		Map<String, String> map4 = new HashMap<>(3);
		map4.put("group", "common");
		map4.put("dataId", "rocketmq");
		list.add(map4);
		this.loadMultConfigFromDiamond(list);
	}

	public void loadMultConfigFromDiamond(List<Map<String, String>> list) {
		if (CollectionUtils.isNotEmpty(list)){
			list.forEach(x->{
				loadOneConfigFromDiamond(x.get("group"), x.get("dataId"));
			});
		}
	}

	public void loadOneConfigFromDiamond(String group, String dataId) {
		DiamondManager manager = new DefaultDiamondManager(group, dataId, new ManagerListener() {
			public void receiveConfigInfo(String configInfo) {
				loadConfig(configInfo);
			}

			public Executor getExecutor() {
				return null;
			}
		} ,DEF_DEMAIN_LIST);
		manager.getDiamondConfigure().setPort(DEF_PORT);
		String configInfo = manager.getAvailableConfigureInfomation(timeOut);
		loadConfig(configInfo);
	}


	private void loadConfig(String configInfo) {
		try {
			properties.load(new StringReader(configInfo));
		} catch (IOException e) {
			throw new RuntimeException("装载properties失败：" + configInfo, e);
		}
		this.setProperties(properties);
	}

    public static Properties getProperties() {
        return properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
