package com.chenyongxiu.firstproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.common.utils.BeanUtil;
import com.chenyongxiu.firstproject.common.utils.ExcelUtil;
import com.chenyongxiu.firstproject.common.utils.exception.ExcelException;
import com.chenyongxiu.firstproject.dao.excel.ExcelServiceMapper;
import com.chenyongxiu.firstproject.diamond.DiamondProperty;
import com.chenyongxiu.firstproject.entity.DataDictionaryModel;
import com.chenyongxiu.firstproject.entity.DataDictionaryPO;
import com.chenyongxiu.firstproject.service.ExcelService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelServiceMapper excelServiceMapper;

    @Value("${cbs.data.dictionary:010001}")
    private int dataSubType;

    @Value("${redis.port3:123}")
    private int master;

    @Autowired
    private PropertyPlaceholderConfigurer propertyPlaceholderConfigurer;

    @Override
    public void saveExcelData(MultipartFile excel) throws ExcelException {

        //读取excel数据
        List<DataDictionaryModel> excelData = ExcelUtil.readExcel(excel, DataDictionaryModel.class);

        //补充remark信息
        List<DataDictionaryModel> remarkDataList = getAnalysisData(excelData);

        //数据转换
        List<DataDictionaryPO> dictionaryPOS = BeanUtil.convertBean(remarkDataList, DataDictionaryPO.class);

        //先删除表里面的数据
        excelServiceMapper.deleteDataDictionary();

        log.info("dictionaryPOS -{}",JSONObject.toJSONString(dictionaryPOS));

        //批量插入excel数据
        excelServiceMapper.saveExcelData(dictionaryPOS);

    }

    @Override
    public void saveExcelData1() throws ExcelException {

        System.out.println(master);
        System.out.println(DiamondProperty.getProperties().getProperty("redis.port2"));

    }

    private List<DataDictionaryModel> getAnalysisData(List<DataDictionaryModel> excelData) {

        //过滤remark为"字段名"的数据
        List<DataDictionaryModel> list = getFilterData(excelData,x->!"字段名".equals(x.getRemark()));

        //补充数据
        String remark = doRemark(list.get(0).getRemark());
        int subType = dataSubType;

        for (int i = 1; i < list.size(); i++) {
            DataDictionaryModel model = list.get(i);
            if (StringUtils.isEmpty(model.getRemark())) {
                //加入remark
                model.setRemark(remark);
                //加入data_sub_type
                model.setDataSubType("0"+String.valueOf(subType));
            } else {
                remark = doRemark(model.getRemark());
                subType++;
            }
        }
        //过滤数据code为null的数据
        List<DataDictionaryModel> filterData = getFilterData(list, x -> StringUtils.isNotEmpty(x.getDataCode()));
        return filterData;
    }

    private <T> List<T> getFilterData(List<T> excelData, Predicate<T> predicate) {
        return excelData.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private String doRemark(String remark) {
        int i = remark.indexOf("-") + 1;
        return remark.substring(i);
    }

}
