package com.chenyongxiu.firstproject.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataDictionaryModel extends BaseRowModel {
    /**
     * 备注
     */
    @ExcelProperty(index = 0 ,value = "wor0")
    private String remark;

    /**
     * 数据value
     */
    @ExcelProperty(index = 1 ,value = "wor1")
    private String dataValue;

    /**
     * 数据code
     */
    @ExcelProperty(index = 2 ,value = "wor2")
    private String dataCode;

    /**
     * 数据类型；01-人保
     */
    private String dataType="01";

    /**
     * 数据子类型，010001-单位行业
     */
    private String dataSubType;

}
