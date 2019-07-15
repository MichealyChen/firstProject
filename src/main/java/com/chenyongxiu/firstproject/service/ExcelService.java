package com.chenyongxiu.firstproject.service;

import com.chenyongxiu.firstproject.common.utils.exception.ExcelException;
import org.springframework.web.multipart.MultipartFile;

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
    void saveExcelData1() throws ExcelException;
}
