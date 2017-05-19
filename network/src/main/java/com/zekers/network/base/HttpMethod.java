package com.zekers.network.base;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit网络设置
 * Created by zekers on 2016/12/23.
 */
public class HttpMethod {
    private static HashMap<String,Retrofit> https;

    public static final String API_HTTP="http";
    public static final String API_HTTPS="https";
    public static final String API_RXTEST="rxtest";

    public static final String inWork="inwork";
    public static final String inWork_1="inwork_1";
    public static final String inWork_2="inWork_2";
    public static final String inWork_3="inWork_3";
    public static final String outWork1="outWork1";
    public static final String outWrok2="outWrok2";
    public static final String outWork3="outWork3";
    public static final String outWrok4="outWrok4";
    public static final String outWork2_1="outWork2_1";
    public static final String outWrok2_2="outWrok2_2";
    public static final String outWork2_3="outWork2_3";
    public static final String outWrok2_4="outWrok2_4";
    public static final String outWrok2_5="outWrok2_5";
    static{
        https=new HashMap<>();
        addRetrofit(API_RXTEST,"https://edu.bingocc.com:20139/home/");
        addRetrofit(API_HTTP,"http://edu.depts.bingosoft.net:8084/home/");
        addRetrofit(API_HTTPS,"https://kyfw.12306.cn/");

//        addRetrofit(inWork,"http://10.201.78.24:80882/");
        addRetrofit(inWork_1,"http://10.201.78.24:8090/ms3/");
        addRetrofit(inWork_2,"http://10.201.78.24:8091/msweb4/");
        addRetrofit(inWork_3,"https://10.201.78.253:20143/");

//        addRetrofit(outWork1,"https://edu.bingocc.com:20085");
//        addRetrofit(outWrok2,"https://edu.bingocc.com:20137/ms/3");
//        addRetrofit(outWork3,"http://edu.bingocc.com:8083/msweb4");
//        addRetrofit(outWrok4,"https://edu.bingocc.com:20146");
//
//        addRetrofit(outWork2_1,"https://mms.depts.bingosoft.net:20153");
//        addRetrofit(outWrok2_2,"https://mms.depts.bingosoft.net:20157/ms/");
//        addRetrofit(outWork2_3,"http://mms.depts.bingosoft.net:8082/msweb");
//        addRetrofit(outWrok2_4,"https://mms.depts.bingosoft.net:20154");
//        addRetrofit(outWrok2_5,"http://mms.depts.bingosoft.net:8083");
    }

    //配置基础的Retrofit
    private static Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(OkHttpClientManager.getClient())
                .build();
    }


    public static void addRetrofit(String key,String baseUrl){
        https.put(key,createRetrofit(baseUrl));
    }

    public static Retrofit getRetrofit(String key){
        return https.get(key);
    }

    /**
     * 初始化服务
     * @param cls 服务类型
     * @param <T> 服务类型
     * @return 返回服务
     */
    public static <T> T getService(String key,Class<T> cls) {
        if (getRetrofit(key)!=null)
            return getRetrofit(key).create(cls);
        else
            return null;
    }
}
