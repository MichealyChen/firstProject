package com.chenyongxiu.firstproject.dao.excel;


import com.chenyongxiu.firstproject.entity.DataDictionaryPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelServiceMapper {


    void deleteDataDictionary();

    void saveExcelData(List<DataDictionaryPO> list);

    List<DataDictionaryPO> getDataDictionaryPO(int limit);


}
