package com.chenyongxiu.firstproject.diamond;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * 
 * @author yanzh 2015-09-10
 *
 */
public class BasePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private long timeOut = 5000;		//超时5秒钟获取一次最新配置
	private static Properties properties = new Properties();
	private static String DEF_DEMAIN_LIST = "a.b.c";//默认动态配置服务器所在IP
	private static int DEF_PORT = 8080;//默认动态配置服务器端口

	/**
	 * 
	 * @param group
	 *            配置中心的组名
	 * @param dataId
	 *            配置中心的ID
	 * 
	 */
	/**
	 * 加载单个组的所有配置
	 * @param group  配置中心的组名
	 * @param dataId  配置中心的ID
	 */
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
	
	/**
	 * 加载单个组的所有配置
	 * @param group  配置中心的组名
	 * @param dataId  配置中心的ID
	 */
	public Properties loadModuleConfigFromDiamond(String group, String dataId) {
		Properties modProperties = new Properties();
		DiamondManager manager = new DefaultDiamondManager(group, dataId, new ManagerListener() {
			public void receiveConfigInfo(String configInfo) {
				loadModuleConfig(configInfo);
			}

			public Executor getExecutor() {
				return null;
			}
		} ,DEF_DEMAIN_LIST);
		manager.getDiamondConfigure().setPort(DEF_PORT);
		String configInfo = manager.getAvailableConfigureInfomation(timeOut);
		modProperties = loadModuleConfig(configInfo);
		return modProperties;
	}

	/**
	 * @param list
	 *            map格式 为： map.put("group","aaa"); map.put("dataId","abc");
	 *            key一定要书写正确，list中可以放多个map,用来获取多组配置
	 */
	public void loadMultConfigFromDiamond(List<Map<String, String>> list) {
		if (null == list || list.size() < 1)
			return;
		String group = "";
		String dataId = "";
		for (Map<String, String> map : list) {
			group = map.get("group");
			dataId = map.get("dataId");
			loadOneConfigFromDiamond(group, dataId);
		}
	}
	
	private Properties loadModuleConfig(String configInfo) { 
		Properties modProperties = new Properties();
		try {
			modProperties.load(new StringReader(configInfo));
		} catch (IOException e) {
			throw new RuntimeException("装载properties失败：" + configInfo, e);
		}
		return modProperties;
		//this.setProperties(properties);
	}

	private void loadConfig(String configInfo) {
		try {
			properties.load(new StringReader(configInfo));
		} catch (IOException e) {
			throw new RuntimeException("装载properties失败：" + configInfo, e);
		}
		this.setProperties(properties);
	}

	public String getConfig(String key) {
		if (null == key || "".equals(key))
			return "";
		return properties.getProperty(key);
	}
	
	public Set getPropertiesAllKey(Properties modProperties){
		return modProperties.keySet();
	}

	public static Properties getProperties() {
		return properties;
	}
	
}
