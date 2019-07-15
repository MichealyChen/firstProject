package com.chenyongxiu.firstproject.dao.borrow;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FinancialBorrowDetailMapper  {


    int updateLoanByIds(@Param(value = "financialBorrowIds") List<Long> financialBorrowIds);

    /**
     * 根据easNo更新借款单状态
     *
     * @param easId
     * @param status
     * @return
     */
    Integer updateByEasNo(@Param(value = "easId") String easId, @Param(value = "status") String status);

    /**
     * 根据主键，删除(更新)明细
     *
     * @param financialBorrowDetailIds
     * @return
     */
    int updateLoanDetailByIds(@Param(value = "financialBorrowDetailIds") List<Long> financialBorrowDetailIds);

    List<Map<Object,Object>> selectMydata();
}
