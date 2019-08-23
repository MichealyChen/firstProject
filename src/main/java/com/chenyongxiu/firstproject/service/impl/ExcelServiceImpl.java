package com.chenyongxiu.firstproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.common.utils.BeanUtil;
import com.chenyongxiu.firstproject.common.utils.ExcelUtil;
import com.chenyongxiu.firstproject.common.utils.TimerTaskUtil;
import com.chenyongxiu.firstproject.common.utils.exception.ExcelException;
import com.chenyongxiu.firstproject.dao.excel.ExcelServiceMapper;
import com.chenyongxiu.firstproject.diamond.BasePropertyPlaceholderConfigurer;
import com.chenyongxiu.firstproject.diamond.DiamondClient;
import com.chenyongxiu.firstproject.entity.DataDictionaryModel;
import com.chenyongxiu.firstproject.entity.DataDictionaryPO;
import com.chenyongxiu.firstproject.service.ExcelService;

import io.netty.util.internal.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private ExcelServiceMapper excelServiceMapper;

    @Value("${cbs.data.dictionary:010001}")
    private int dataSubType;

    @Value("${redis.port3:123}")
    private int master;

    private AtomicInteger tt = new AtomicInteger();

//    @Autowired
//    private PropertyPlaceholderConfigurer propertyPlaceholderConfigurer;

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

        log.info("dictionaryPOS -{}", JSONObject.toJSONString(dictionaryPOS));

        //批量插入excel数据
        excelServiceMapper.saveExcelData(dictionaryPOS);

    }

    @Override
    public List<DataDictionaryPO> saveExcelData1() throws ExcelException {

        System.out.println(master);
//        Properties properties = DiamondProperty.getProperties();
//        String property = properties.getProperty("redis.port2");

//        System.out.println(property);
        List<DataDictionaryPO> dictionaryPO = excelServiceMapper.getDataDictionaryPO(300);
        DiamondClient instance = DiamondClient.getInstance();
        boolean b = instance.logEncrypt();
        System.out.println(b);
        System.out.println(instance);
        return dictionaryPO;

    }

    @Override
    public List<DataDictionaryPO> saveExcelData3() throws ExcelException {
        AtomicInteger n = new AtomicInteger();
//        ttt( n);
//        rrr2(1000);
//        sout();
        new TimerTaskUtil(new Timer()).start();
        return null;

    }

    //    @Scheduled(cron = "0/10 * * * * ?")
//    public void sout() {
//        System.out.println("dddddddddd");
//    }
    private void rrr2(int n) {

        if (tt.incrementAndGet() > 6) {
            return;
        }
        Timer ti = new Timer();

        System.out.println("wwwww"+tt.get());

            ti.schedule(new TimerTask() {
                @Override
                public void run() {
                    int num = new Random().nextInt(30) + 1;
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss");
                    System.out.println(Thread.currentThread().getName() + " " + dateTimeFormatter.format(LocalDateTime.now()));

                    if (num > 5) {
                        rrr2(3000);
                    }
                }
            }, tt.get()*n);

    }


    private void rrr(AtomicInteger n) {
        Timer ti = new Timer();
        int period = 2000 + n.get() * 1000;
        System.out.println("period " + period);
        ti.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss");
                System.out.println(Thread.currentThread().getName() + " " + dateTimeFormatter.format(LocalDateTime.now()));
                int andIncrement = n.getAndIncrement();
                if (andIncrement >= 3) {
                    cancel();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 2000);
    }

    private void ttt(AtomicInteger n) {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss");
            System.out.println(Thread.currentThread().getName() + " " + dateTimeFormatter.format(LocalDateTime.now()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int andIncrement = n.getAndIncrement();

            if (andIncrement >= 5) {
                executorService.shutdownNow();
            }

        }, 2, 2, TimeUnit.SECONDS);
    }


    private List<DataDictionaryModel> getAnalysisData(List<DataDictionaryModel> excelData) {

        //过滤remark为"字段名"的数据
        List<DataDictionaryModel> list = getFilterData(excelData, x -> !"字段名".equals(x.getRemark()));

        //补充数据
        String remark = doRemark(list.get(0).getRemark());
        int subType = dataSubType;

        for (int i = 1; i < list.size(); i++) {
            DataDictionaryModel model = list.get(i);
            if (StringUtils.isEmpty(model.getRemark())) {
                //加入remark
                model.setRemark(remark);
                //加入data_sub_type
                model.setDataSubType("0" + String.valueOf(subType));
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
