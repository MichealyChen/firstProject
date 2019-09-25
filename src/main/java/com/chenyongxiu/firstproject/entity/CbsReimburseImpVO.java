package com.chenyongxiu.firstproject.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * *****************  类说明  *********************
 *
 * @author :  chenyongxiu
 * @description : 直接报销导入VO
 * @Date : 2019-05-23 15:27:08
 * ***********************************************
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CbsReimburseImpVO extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 费用类型名称
    @ExcelProperty(index = 0 ,value = "费用类型名称")
    private String costTypeName;

    @ExcelProperty(index = 1 ,value = "是否需要打款")
    private String isNeedRemit;

    @ExcelProperty(index = 2 ,value = "发生时间")
    private String happenTime;

    @ExcelProperty(index = 3 ,value = "发票号码")
    private String invoiceNo;

    // 关联汽车车架号
    @ExcelProperty(index = 4 ,value = "关联汽车车架号")
    private String frameNo;

    @ExcelProperty(index = 5 ,value = "费用承担分公司组织名称")
    private String branchOfficeOrgName;

    @ExcelProperty(index = 6 ,value = "费用承担部门名称")
    private String costBearingDeptName;

    // 报销金额
    @ExcelProperty(index = 7 ,value = "报销金额")
    private String reimburseAmount;

    // 报销审批金额
    @ExcelProperty(index = 8 ,value = "报销审批金额")
    private String reimburseAuditAmount;

    // 用途(备注)
    @ExcelProperty(index = 9 ,value = "用途(备注)")
    private String remark;

}
