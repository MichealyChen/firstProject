package com.chenyongxiu.firstproject.diamond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liupeng
 * @Date 2019/06/28 12:45
 * @Description
 */
public class DiamondClient extends BasePropertyPlaceholderConfigurer {
    private static DiamondClient diamondClient;
    private DiamondClient(){}
    public static final DiamondClient getInstance(){
          if(diamondClient == null){
              synchronized (DiamondClient.class){
                  if(diamondClient == null){
                      diamondClient = new DiamondClient();
                      diamondClient.init();
                  }
              }
          }
          return diamondClient;
    }
    private void init() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        //picc配置
        Map<String, String> debitSplit = new HashMap<String, String>();
        debitSplit.put("group", "common");
        debitSplit.put("dataId", "platform");
        list.add(debitSplit);

        this.loadMultConfigFromDiamond(list);
    }

    /**
     * 日志是否加密码
     *
     * @return
     */
    public boolean logEncrypt() {
        boolean encrypt = true;
        // 异常处理，如果读取不到参数，默认为加密码日志，开发环境与测试环境可以配置，生产环境可根据情况决定是否配置此参数
        try {
            String configParam = this.getConfig("platform.log.encrypt");
            if(configParam != null){
                encrypt = Boolean.parseBoolean(this.getConfig("platform.log.encrypt"));
            }
        } catch (Exception ex) {
            encrypt = false;
        }
        return encrypt;
    }
}
