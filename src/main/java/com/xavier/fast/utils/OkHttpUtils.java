package com.xavier.fast.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * http 工具类 copy by com.bxm.util.OkHttpUtils
 *
 * @ClassName OkHttpUtils
 * @CopyRright (c) 2018-bxm：杭州微财网络科技有限公司
 * @Author kk.xie
 * @Date 2018/6/19 15:07
 * @Version 1.0
 * @Modifier kk.xie
 * @Modify Date 2018/6/19 15:07
 **/
@Slf4j
public class OkHttpUtils {

    /**
     * 参数格式
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 创建一个 OkHttpClient
     */
    public static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().connectTimeout(5,
            TimeUnit.SECONDS).readTimeout(60,
            TimeUnit.SECONDS).build();

    /**
     * 方法描述:httpGet
     *
     * @param url serverHost可以带参数如http://www.baidu.com?param1=xxx&param2=xxx
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:59:07
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 方法描述:httpGet
     *
     * @param url    serverHost
     * @param params 参数:map格式
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:53:50
     */
    public static String get(String url, Map<String, String> params) throws IOException {
        // 构建请求参数
        StringBuffer paramSb = new StringBuffer();
        if (params != null) {
            for (Entry<String, String> e : params.entrySet()) {

                if (e.getValue() != null) {
                    paramSb.append(URLEncoder.encode(e.getKey(), "UTF-8"));
                    paramSb.append("=");
                    // 将参数值urlEncode编码,防止传递中乱码
                    paramSb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
                    paramSb.append("&");
                }
            }
        }
        if (paramSb.length() > 0) {
            String paramStr = paramSb.toString();
            paramStr = paramStr.substring(0, paramStr.length() - 1);
            url += "?" + paramStr;
        }

        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException  e){
            throw e;
        }
    }

    /**
     * 方法描述:httpPost
     *
     * @param url    serverHost
     * @param params 参数:json格式
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:53:28
     */
    public static String post(String url, String params) throws IOException {
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 方法描述:httpPost
     *
     * @param url    serverHost
     * @param params 参数:map格式
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:53:50
     */
    public static String post(String url, Map<String, String> params) throws IOException {

        FormBody.Builder build = new FormBody.Builder();
//        for (String key : params.keySet()) {
//            if (params.get(key) != null) {
//                build.add(URLEncoder.encode(key, "UTF-8"), URLEncoder.encode(params.get(key).toString(), "UTF-8"));
//            }
//        }
        StringBuilder sb = new StringBuilder(url);
        int count = 0;
        for (String key : params.keySet()) {
            if(0 == count){
                sb.append("?"+URLEncoder.encode(key, "UTF-8")+"="+URLEncoder.encode(params.get(key).toString(), "UTF-8"));
            }else {
                sb.append("&"+URLEncoder.encode(key, "UTF-8")+"="+URLEncoder.encode(params.get(key).toString(), "UTF-8"));
            }
            count++;
        }
        RequestBody body = build.build();
        Request request = new Request.Builder().url(sb.toString()).post(body).build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException  e){
            throw e;
        }
    }

    /**
     * 方法描述:httpPut
     *
     * @param url    serverHost
     * @param params 参数:json格式
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:53:50
     */
    public static String put(String url, String params) throws IOException {
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder().url(url).put(body).build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 方法描述:httpPut
     *
     * @param url    serverHost
     * @param params 参数:map格式数
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:53:50
     */
    public static String put(String url, Map<String, Object> params) throws IOException {
        FormBody.Builder build = new FormBody.Builder();
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                build.add(URLEncoder.encode(key, "UTF-8"), URLEncoder.encode(params.get(key).toString(), "UTF-8"));
            }
        }
        RequestBody body = build.build();

        Request request = new Request.Builder().url(url).put(body).build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 方法描述:httpDelete
     *
     * @param url serverHost
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:53:50
     */
    public static String delete(String url) throws IOException {
        Request request = new Request.Builder().url(url).delete().build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 方法描述:httpPatch
     *
     * @param url    serverHost
     * @param params 参数:map格式
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:57:23
     */
    public static String patch(String url, Map<String, Object> params) throws IOException {
        FormBody.Builder build = new FormBody.Builder();
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                build.add(URLEncoder.encode(key, "UTF-8"), URLEncoder.encode(params.get(key).toString(), "UTF-8"));
            }
        }
        RequestBody body = build.build();
        Request request = new Request.Builder().url(url).patch(body).build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 方法描述:httpPatch
     *
     * @param url    serverHost
     * @param params 参数:json格式
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:57:23
     */
    public static String patch(String url, String params) throws IOException {
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder().url(url).patch(body).build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 方法描述:httpHead
     *
     * @param url serverHost
     * @return
     * @throws IOException
     * @author leon 2017年5月10日 下午3:58:28
     */

    public static String head(String url) throws IOException {
        Request request = new Request.Builder().url(url).head().build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

}