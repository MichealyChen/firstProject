package com.chenyongxiu.firstproject.web;


import com.chenyongxiu.firstproject.common.utils.exception.ExcelException;
import com.chenyongxiu.firstproject.service.ExcelService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public void saveExcelData2(){
        try {
            excelService.saveExcelData1();
        } catch (ExcelException e) {
            e.printStackTrace();
        }
    }
}
