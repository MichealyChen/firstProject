package com.chenyongxiu.firstproject.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.entity.WfNextStepVO;
import org.apache.commons.lang.StringUtils;


import java.util.ArrayList;
import java.util.List;

/**
* @author 彭泽民
* @version 创建时间：2018年6月23日 下午6:17:57
* @Description 类描述：
*/
public class WfJsonUtils {

	/**
	 * 获取流程名称
	 * @param jsonString
	 * @param processName
	 * @return
	 */
	public static WfNextStepVO getProceesName(String jsonString, String processName){

		if (jsonString == null)
			return null;
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		if(jsonObject.get("approval") != null){
			JSONArray approvalJsonList = JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("approval")));

			WfNextStepVO wfNextStepVO =StringUtils.isNotEmpty(processName)?
					getWfNextStepVO(approvalJsonList)
					:getWfNextStepVO(approvalJsonList, processName);
			if(wfNextStepVO != null){
				return wfNextStepVO;
			}
		}
		if(jsonObject.get("biz") != null){
			JSONArray bizJsonList = JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("biz")));
			WfNextStepVO wfNextStepVO = StringUtils.isNotEmpty(processName)
					?getWfNextStepVO(bizJsonList)
					:getWfNextStepVO(bizJsonList, processName);
			if(wfNextStepVO != null){
				return wfNextStepVO;
			}
		}
		return null;
	}

	/**
	 * 获取流程名称
	 * @param jsonString
	 * @param processName
	 * @return
	 */
	public static WfNextStepVO getWfNextStep(String jsonString,String processName){

		if (jsonString == null)
			return null;
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		if(jsonObject.get("approval") != null){
			JSONArray approvalJsonList = JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("approval")));

			List<Long> defineIds = new ArrayList<>();
			if(approvalJsonList != null && approvalJsonList.size() > 0){
				for (int i = 0; i < approvalJsonList.size(); i++) {
					JSONObject object = approvalJsonList.getJSONObject(i);
					defineIds.add(object.getLong("defineId"));
				}
			}
			WfNextStepVO wfNextStepVO =StringUtils.isNotEmpty(processName)?
					getWfNextStepVO(approvalJsonList)
					:getWfNextStepVO(approvalJsonList, processName);
			if(wfNextStepVO != null){
				wfNextStepVO.setDefineIds(defineIds);
				return wfNextStepVO;
			}
		}
		if(jsonObject.get("biz") != null){
			JSONArray bizJsonList = JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("biz")));
			WfNextStepVO wfNextStepVO = StringUtils.isNotEmpty(processName)? getWfNextStepVO(bizJsonList):getWfNextStepVO(bizJsonList, processName);
			if(wfNextStepVO != null){
				return wfNextStepVO;
			}
		}
		return null;
	}


	private static WfNextStepVO getWfNextStepVO(JSONArray bizJsonList, String processName){
		WfNextStepVO wfNextStepVO = new WfNextStepVO();
		for(Object bizobject : bizJsonList){
			JSONObject object = JSONObject.parseObject(bizobject.toString());
			if(object.get("name") != null && processName.equals(object.get("name"))){
				wfNextStepVO.setDefineId(Long.parseLong(object.getString("defineId")));
				wfNextStepVO.setName(object.getString("name"));
				wfNextStepVO.setProcessInstId(object.getString("processInstId"));
				wfNextStepVO.setStatusCode(object.getString("statusCode"));
				wfNextStepVO.setAlias(object.getString("alias"));
				return wfNextStepVO;
			}
		}
		return null;
	}
	
	private static WfNextStepVO getWfNextStepVO(JSONArray bizJsonList){
		WfNextStepVO wfNextStepVO = new WfNextStepVO();
		for(Object bizobject : bizJsonList){
			JSONObject object = JSONObject.parseObject(bizobject.toString());
			wfNextStepVO.setDefineId(Long.parseLong(object.getString("defineId")));
			wfNextStepVO.setName(object.getString("name"));
			wfNextStepVO.setProcessInstId(object.getString("processInstId"));
			wfNextStepVO.setStatusCode(object.getString("statusCode"));
			wfNextStepVO.setAlias(object.getString("alias"));
			return wfNextStepVO;
		}
		return null;
	}
	
}
