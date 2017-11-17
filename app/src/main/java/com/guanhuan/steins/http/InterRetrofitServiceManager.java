package com.guanhuan.steins.http;

import com.guanhuan.steins.config.ApiConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 74405 on 2017/11/17.
 */

public class InterRetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private final Retrofit mRetrofit;
    private final OkHttpClient client;
    private final HttpCommonInterceptor interceptor;
    /** 为请求增加authorization 消息头 */
    public InterRetrofitServiceManager(HttpCommonInterceptor interceptor){

        this.interceptor = interceptor;

        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//连接超时时间
            .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)//写操作 超时时间
            .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)//读操作超时时间
            .addNetworkInterceptor(interceptor);//添加消息头过滤器
        client = builder.build();

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
