package com.chenyongxiu.firstproject.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * *****************  类说明  *********************
 *
 * @author :  hmk
 * @version :  1.0
 * @description :
 * @Date : 2019/7/30 10:17
 * ***********************************************
 */
@Data
public class RiskResponse implements Serializable {
    /**
     * 风控评估结果返回通用父类
     * 返回码及相关信息描述请参看
     *
     */
    private String code;
    private String msg;
}
