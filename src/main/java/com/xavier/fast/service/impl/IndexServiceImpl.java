package com.xavier.fast.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xavier.fast.entity.StoreList;
import com.xavier.fast.mapper.StoreListMapper;
import com.xavier.fast.service.IndexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * @author xavier
 * createDate:  2018/11/8
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private StoreListMapper storeListMapper;

    @Override
    public List<StoreList> getUser(String path,String out) {
        List<String> ins = new ArrayList<>();

        File file = new File(path);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件

            String s = null;
            while((s = br.readLine())!=null){
                String line = System.lineSeparator()+s;
                ins = Arrays.asList(line.split(","));
            }
            br.close();
        }catch(Exception e){
        }

        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(out)){
            map.put("out",out);
        }
        map.put("ins",ins);

        List<StoreList> results = storeListMapper.selectUserList(map);
        return results;
    }

    @Override
    public String insertRecord(String url,String remake) {
        //OkHttpUtils.get("https://sj.qq.com/myapp/searchAjax.htm?kw=%E6%8B%BC%E5%A4%9A%E5%A4%9A");

        File file = new File(url);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件

            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String line = System.lineSeparator()+s;
                try {
                    storeListMapper.insertForeach(createBean(line,remake));
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("INSERT ERROR:" + line + "~~" + remake);
                    continue;
                }

            }
            br.close();
        }catch(Exception e){
        }
        return null;
    }


    public List<StoreList> createBean(String line,String remake){

        JSONObject json = JSONObject.parseObject(line);
        String imei = (String)json.get("imei");
        String imsi = (String)json.get("imsi");
        String phone = (String)json.get("phone");
        String applist = (String)json.get("applist");

        List<StoreList> lists = new ArrayList<>();
        String[] applists = applist.split(",");
        try {
            for (String appMsg:applists) {
                StoreList storeList = new StoreList();
                storeList.setApplist(appMsg);
                storeList.setPhone(phone);
                storeList.setImei(imei);
                storeList.setImsi(imsi);
                storeList.setRemake(remake);
                if (appMsg.contains(":")){
                    String[] appMsgs = appMsg.split(":");
                    if (appMsgs.length > 1){
                        storeList.setAppName(appMsgs[0]!=null ? appMsgs[0] : "nothing");
                        storeList.setAppPackage(appMsgs[1]!=null ? appMsgs[1] : "nothing");
                    }
                }else if (appMsg.contains("|")){
                    String[] appMsgs = appMsg.split("\\u007C");
                    if (appMsgs.length > 1){
                        storeList.setAppName(appMsgs[1]!=null ? appMsgs[1] : "nothing");
                        storeList.setAppPackage(appMsgs[0]!=null ? appMsgs[0] : "nothing");
                    }
                }else {
                    storeList.setAppName("nothing");
                    storeList.setAppPackage("nothing");
                }
                lists.add(storeList);
            }
        }catch (Exception e){
            System.out.println("CREATE ERROR:" + imei + "~~~" + remake);
            return new ArrayList<>();
        }
        return  lists;
    }

}
