//package com.chenyongxiu.firstproject.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.Data;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
///**
// * @author :  chenyongxiu
// * @description : 售后回租收款记录表
// * @Date : 2019-06-19 15:27:08
// */
//@Data
//@TableName("t_leaseback_receipt_record")
//public class LBReceiptRecordPO implements Serializable{
//
//   private static final long serialVersionUID = 1L;
//
//   // 收款记录id
//   @TableId(value = "receipt_record_id", type = IdType.AUTO)
//   private Long receiptRecordId;
//
//   // 收款计划ids
//   @TableField(value = "receipt_plan_ids")
//   private String receiptPlanIds;
//
//   // 其他费用ids
//   @TableField(value = "biz_other_fee_ids")
//   private String bizOtherFeeIds;
//
//   // 订单id
//   @TableField(value = "biz_id")
//   private Long bizId;
//
//   // 生气单id
//   @TableField(value = "apply_id")
//   private Long applyId;
//
//   // 逾期id
//   @TableField(value = "overdue_ids")
//   private String overdueIds;
//
//   // 费用减免id
//   @TableField(value = "fee_reduce_ids")
//   private String feeReduceIds;
//
//   // 合同减免费用
//   @TableField(value = "contract_fee_ids")
//   private String contractFeeIds;
//
//   // 优惠券id
//   @TableField(value = "coupon_ids")
//   private String couponIds;
//
//   // 收款方式
//   @TableField(value = "receipt_way")
//   private String receiptWay;
//
//   // 收款二级方式
//   @TableField(value = "receipt_second_way")
//   private String receiptSecondWay;
//
//   // 实收金额
//   @TableField(value = "act_receipt_amount")
//   private BigDecimal actReceiptAmount;
//
//   // 流水号
//   @TableField(value = "flow_no")
//   private String flowNo;
//
//   // 实收日期
//   @TableField(value = "act_receipt_date")
//   private Date actReceiptDate;
//
//   // 经办人
//   @TableField(value = "operator")
//   private String operator;
//
//   // 审核人
//   @TableField(value = "checker")
//   private String checker;
//
//   // 收款收据编号/pos号
//   @TableField(value = "receipt_no")
//   private String receiptNo;
//
//   // 收款状态
//   @TableField(value = "status")
//   private String status;
//
//   // 支付发起端：0:pc,1:APP
//   @TableField(value = "channel")
//   private short channel;
//
//   // 备注
//   @TableField(value = "remark")
//   private String remark;
//
//   // 流程内容，下一步骤id
//   @TableField(value = "wf_next_steps")
//   private Long wfNextSteps;
//
//   // 工作流标识id
//   @TableField(value = "wf_unique_id")
//   private String wfUniqueId;
//
//}