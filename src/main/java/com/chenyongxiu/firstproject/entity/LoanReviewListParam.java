package com.chenyongxiu.firstproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :  chenyongxiu
 * @description : 放款审核参数
 * @Date : 2019-07-30 15:27:08
 */
@Data
@NoArgsConstructor
public class LoanReviewListParam {

    private String name;

    private String applyNo;

    private String status="55";

    private Integer current = 1;

    private Integer size = 10;

}
