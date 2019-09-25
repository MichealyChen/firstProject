package com.chenyongxiu.httptest;

import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.JedisPoolUtil;
import com.chenyongxiu.firstproject.RestTemplateTest;
import com.chenyongxiu.firstproject.TestJedisPoolUtil;
import com.chenyongxiu.firstproject.UatJedisPoolUtil;
import com.chenyongxiu.firstproject.entity.LoanReviewListParam;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.util.*;

public class GenerateRepaymentPlanTest {

    @Test
    public void httpTest() {
//        String url = "http://testnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";
        String url = "http://localhost:8080/cbs/leaseback/signContract/generateRepaymentPlan";
        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("applyNo","SQHZ20190909000004");
        String response = RestTemplateTest.doFormHttp(url, requestBody);
        System.out.println(response);
    }

    @Test
    public void httpFileTest() {
//        String url = "http://testnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";
        String url = "http://localhost:8080/cbs/directreimburse/importDate";
        String filePath = "C:\\Users\\Administrator\\Desktop\\yy.xlsx";

        Object doHttpForFile = RestTemplateTest.doHttpForFile(url, filePath);
        System.out.println(doHttpForFile);
    }

    @Test
    public void httpFileTest2() {
//        String url = "http://testnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";
        String url = "http://localhost:8091/easyExcelUtil/importDate";
        String filePath = "C:\\Users\\Administrator\\Desktop\\yy.xlsx";

        Object doHttpForFile = RestTemplateTest.doHttpForFile(url, filePath);
        System.out.println(doHttpForFile);
    }

}
