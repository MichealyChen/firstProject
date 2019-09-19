package com.chenyongxiu.firstproject;

import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.entity.LoanReviewListParam;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.*;

public class RestTemplateTest {


    public static String getToken(String url) {
        Set<String> keys = getJedis(url).keys("token_nw_erp_pc_*");
        List<String> list = new ArrayList<>(keys);
        String token = getJedis(url).get(list.get(0));
        return token;
    }

    public static Jedis getJedis(String url) {
        Jedis jedis = null;
        if (url.startsWith("http://dev")) {
            jedis = new JedisPoolUtil().getJedis();
        } else if (url.startsWith("http://test")) {
            jedis = new TestJedisPoolUtil().getJedis();
        }else if(url.startsWith("http://uat")){
            jedis = new UatJedisPoolUtil().getJedis();
        }
        else {
            jedis = new JedisPoolUtil().getJedis();
        }
        return jedis;
    }

    @Test
    public void clientTest() {
        String url = "http://devnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";

        RestTemplate restTemplate = new RestTemplate();

        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("token", getToken(url));

        //body
        //MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        Map<String, String> requestBody = new HashMap();

        requestBody.put("status", "2");
        requestBody.put("applyNo", "SQHZ2");

        //HttpEntity
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, requestHeaders);
        //HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);

        String s = restTemplate.postForObject(url, requestEntity, String.class);

        System.out.println(s);
    }


    public static String doFormHttp(String url,  MultiValueMap<String, String> body) {

        RestTemplate restTemplate = new RestTemplate();

        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestHeaders.set("token", getToken(url));
        //body
//        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(body, requestHeaders);

        String s = restTemplate.postForObject(url, requestEntity, String.class);

        return s;
    }

    public static String doHttp(String url, Object requestBody) {

        RestTemplate restTemplate = new RestTemplate();

        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("token", getToken(url));

        String s1 = JSONObject.toJSONString(requestBody);
        Map<String, String> map = JSONObject.parseObject(s1, Map.class);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(map, requestHeaders);


        String response = restTemplate.postForObject(url, requestEntity, String.class);

        return response;
    }

    public static Object doHttpForFile(String url, String filePath) {

        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        requestHeaders.set("token", getToken(url));
        RestTemplate rest = new RestTemplate();

        // 设置请求的格式类型
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, requestHeaders);
        ResponseEntity<Object> responseResponseEntity = rest.postForEntity(url, files, Object.class);

        String response = rest.postForObject(url, files, String.class);
        Object body = responseResponseEntity.getBody();


        return body;
    }

    public String doHttpForFile2(String url, String filePath) {

        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        requestHeaders.set("token", getToken(url));

        RestTemplate rest = new RestTemplate();

        // 设置请求的格式类型
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, requestHeaders);

        String response = rest.postForObject(url, files, String.class);

        return response;
    }

    @Test
    public void httpTest() {
//        String url = "http://testnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";
        String url = "http://localhost:8080/cbs/zyLeaseback/loanReviewList";
        LoanReviewListParam listParam = new LoanReviewListParam();
        listParam.setStatus("2");
        listParam.setApplyNo("SQHZ2");
        String response = doHttp(url, listParam);
        System.out.println(response);
    }

    @Test
    public void httpFileTest() {
//        String url = "http://testnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";
        String url = "http://localhost:8080/cbs/directreimburse/importDate";
        String filePath = "C:\\Users\\Administrator\\Desktop\\yy.xlsx";

        Object doHttpForFile = doHttpForFile(url, filePath);
        System.out.println(doHttpForFile);
    }

    @Test
    public void httpFileTest333() {
//        String url = "http://testnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";
        String url = "http://localhost:8080/cbs/directreimburse/importDate";
        String filePath = "C:\\Users\\Administrator\\Desktop\\直接报销导入模板11111.xlsx";

        Object doHttpForFile = doHttpForFile(url, filePath);
        System.out.println(doHttpForFile);
    }


    @Test
    public void httpFileTest3() {
        String url = "http://testnuwa.yicartrip.com/api/cbs/directreimburse/importDate";
        String filePath = "C:\\Users\\Administrator\\Desktop\\yy.xlsx";

        Object doHttpForFile = doHttpForFile(url, filePath);
        System.out.println(doHttpForFile);
    }

    @Test
    public void httpFileTest4() {
        String url = "http://testnuwa.yicartrip.com/api/cbs/directreimburse/importDate";
        String filePath = "C:\\Users\\Administrator\\Desktop\\直接报销导入模板11111.xlsx";

        Object doHttpForFile = doHttpForFile(url, filePath);
        System.out.println(doHttpForFile);
    }

    @Test
    public void httpFileTest5() {
        String url = "http://devnuwa.yicartrip.com/api/cbs/directreimburse/importDate";
        String filePath = "C:\\Users\\Administrator\\Desktop\\直接报销导入模板11111.xlsx";

        Object doHttpForFile = doHttpForFile(url, filePath);
        System.out.println(doHttpForFile);
    }


}
