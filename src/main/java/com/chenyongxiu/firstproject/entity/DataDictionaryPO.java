package com.chenyongxiu.firstproject.entity;
import	java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataDictionaryPO implements Serializable{

    private static final long serialVersionUID = -7605096476147102097L;
    /**
     * 数据类型；01-人保
     */
    private String dataType;

    /**
     * 数据子类型，010001-单位行业
     */
    private transient String dataSubType;

    /**
     * 数据code
     */
    private String dataCode;

    /**
     * 数据value
     */
    private String dataValue;

    /**
     * 备注
     */
    private String remark;


}
