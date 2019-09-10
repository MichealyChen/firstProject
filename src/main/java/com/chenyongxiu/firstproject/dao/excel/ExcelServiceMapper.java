package com.chenyongxiu.firstproject.dao.excel;


import com.chenyongxiu.firstproject.entity.DataDictionaryPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface ExcelServiceMapper {


    void deleteDataDictionary();

    void saveExcelData(List<DataDictionaryPO> list);

    List<DataDictionaryPO> getDataDictionaryPO(int limit);


}
