package com.chenyongxiu.firstproject;

import com.alibaba.fastjson.JSONObject;
import com.chenyongxiu.firstproject.entity.LoanReviewListParam;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.util.*;

public class RestTemplateTest {


    public String getToken(String url) {
        Set<String> keys = getJedis(url).keys("token_nw_erp_pc_*");
        List<String> list = new ArrayList<>(keys);
        String token = getJedis(url).get(list.get(0));
        return token;
    }

    public Jedis getJedis(String url) {
        Jedis jedis = null;
        if (url.startsWith("http://dev")) {
            jedis = new JedisPoolUtil().getJedis();
        } else if (url.startsWith("http://test")) {
            jedis = new TestJedisPoolUtil().getJedis();
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

    public String doHttp(String url, Object requestBody) {

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

    @Test
    public void httpTest() {
        String url = "http://testnuwa.yicartrip.com/api/cbs/zyLeaseback/loanReviewList";
        LoanReviewListParam listParam = new LoanReviewListParam();
        listParam.setStatus("2");
        listParam.setApplyNo("SQHZ2");
        String response = doHttp(url, listParam);
        System.out.println(response);
    }

}
