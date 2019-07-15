package com.chenyongxiu.firstproject.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * YangPH
 * @author 样例VO
 *
 */
@Data
public class TestRespVO implements Serializable{

	private static final long serialVersionUID = 1L;

    private Date startDate;

    private Date endDate;

    private LocalDate startDate1;

    private LocalDateTime endDate1;


}
