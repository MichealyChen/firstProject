package com.chenyongxiu.firstproject.web;


import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.common.utils.exception.ExcelException;
import com.chenyongxiu.firstproject.entity.CbsReimburseImpVO;
import com.chenyongxiu.firstproject.entity.DataDictionaryPO;
import com.chenyongxiu.firstproject.service.ExcelService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("easyExcelUtil")
public class EasyExcelUtilController {

    @Autowired
    private ExcelService excelService;




    @PostMapping("saveExcelData")
    public Object saveExcelData(MultipartHttpServletRequest request){
        Iterator<String> itr = request.getFileNames();
        String uploadedFile = itr.next();
        List<MultipartFile> files = request.getFiles(uploadedFile);
        if (CollectionUtils.isEmpty(files)) {
            return "请选择文件！";
        }
        try {
            excelService.saveExcelData(files.get(0));
            return "";
        } catch (ExcelException e) {
            log.info("");
            return ""+e.getMessage();
        }
    }

    @PostMapping("saveExcelData2")
    public Object saveExcelData2(){
        List<DataDictionaryPO> dataDictionaryPOS = null;
        try {
            dataDictionaryPOS = excelService.saveExcelData1();
        } catch (ExcelException e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(dataDictionaryPOS);
    }

    @PostMapping("saveExcelData3")
    public Object saveExcelData3(){
        List<DataDictionaryPO> dataDictionaryPOS = null;
        try {
            dataDictionaryPOS = excelService.saveExcelData3();
        } catch (ExcelException e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(dataDictionaryPOS);
    }

    @RequestMapping(value = "/importDate", method = RequestMethod.POST)
    public Object importDate(@RequestParam MultipartFile file)  {

        List<CbsReimburseImpVO> cbsReimburseImpVOS = null;
        try {
            cbsReimburseImpVOS = excelService.importData(file);
        } catch (ExcelException e) {
            e.printStackTrace();
        }

        return cbsReimburseImpVOS;
    }
}
