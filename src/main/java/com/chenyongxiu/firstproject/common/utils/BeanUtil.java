package com.chenyongxiu.firstproject.common.utils;
/**
* @author chenyongxiu
* @version 创建时间：2019年7月5日 09:06:48
* @Description 类描述：
*/

import com.alibaba.fastjson.JSONObject;

import java.util.List;


public class BeanUtil {
	
	

	/**
	 * @param source 需要转换的类
	 * @param target 目标类
	 * @param <T>
	 * @return t2
	 */
	public static <T> T convertBean(Object source, Class<T> target) {
		String s = JSONObject.toJSONString(source);
		T t = JSONObject.parseObject(s, target);
		return t;
	}

	/**
	 * list 转 list
	 *
	 * @param sources
	 * @param target
	 * @return
	 */
	public static <T> List<T> convertBean(List<? extends Object> sources, Class<T> target)  {

		String s = JSONObject.toJSONString(sources);
		List<T> result = JSONObject.parseArray(s, target);
		return result;
	}

}
