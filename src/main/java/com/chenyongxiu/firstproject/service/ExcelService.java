package com.chenyongxiu.firstproject.service;

import com.chenyongxiu.firstproject.common.utils.exception.ExcelException;
import com.chenyongxiu.firstproject.entity.CbsReimburseImpVO;
import com.chenyongxiu.firstproject.entity.DataDictionaryPO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 处理excel数据
 *
 * @author chenyongxiu
 * @date 2019/07/05 11:46
 */
public interface ExcelService {

    /**
     * 保存excel信息
     *
     * @param excel
     */
    void saveExcelData(MultipartFile excel) throws ExcelException;
    List<CbsReimburseImpVO> importData(MultipartFile excel) throws ExcelException;
    List<CbsReimburseImpVO> importData1(MultipartFile excel) throws ExcelException;


    List<DataDictionaryPO> saveExcelData1() throws ExcelException;

    List<DataDictionaryPO> saveExcelData3() throws ExcelException;
}
