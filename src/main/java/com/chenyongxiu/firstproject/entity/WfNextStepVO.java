package com.chenyongxiu.firstproject.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author 彭泽民
* @version 创建时间：2018年6月21日 下午9:11:51
* @Description 类描述：
*/
@Data
@Accessors(chain = true)
@ToString
public class WfNextStepVO implements Serializable{

	private static final long serialVersionUID = 1L;
	//流程实例Id,备用
	private String processInstId;
	//下一步Id
	private Long defineId;
	//下一步Id
	private List<Long> defineIds;
	//别名
	private String alias;
	//流程名称
	private String name;
	//状态
	private String  statusCode;
}
