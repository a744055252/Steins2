package com.guanhuan.steins.http;

import com.guanhuan.steins.config.ApiConfig;
import com.guanhuan.steins.config.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhouwei on 16/11/9.
 */

public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private OkHttpClient client;
    /** 为请求增加authorization 消息头 */
    private Interceptor authInterceptor;
    private RetrofitServiceManager(Interceptor interceptor){
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间
        if(interceptor != null) {
            builder.addNetworkInterceptor(interceptor);
        }
        client = builder.build();

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
    }

    private RetrofitServiceManager(){
        this(null);
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }


    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
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

    public void setInterceptors(Interceptor interceptor){
        client.networkInterceptors().add(interceptor);
    }

    public void removeInterceptors(Interceptor interceptor){
        client.networkInterceptors().remove(interceptor);
    }

    public void setAuthInterceptor(String token){
        HttpCommonInterceptor httpCommonInterceptor =
                new HttpCommonInterceptor.Builder()
                        .addHeaderParams(Constants.AUTHORIZATION, token)
                        .build();
        this.authInterceptor = httpCommonInterceptor;
        this.setInterceptors(httpCommonInterceptor);
    }

    public void removeAuthInterceptor(){
        if(this.authInterceptor == null){
            throw new NullPointerException("AuthInterceptor is empty!");
        }
        this.removeInterceptors(this.authInterceptor);
    }
}
