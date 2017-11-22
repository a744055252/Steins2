package com.guanhuan.steins.http;

import com.guanhuan.steins.App;
import com.guanhuan.steins.config.ApiConfig;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.util.PreferencesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhouwei on 16/11/9.
 * Alter guanhuan_li on 2017-11-22 17:15:34
 */

public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private OkHttpClient client;
    /** 为请求增加authorization 消息头 */
    private RetrofitServiceManager(){
        //InputStream[] cerInput = null;
        //读取证书流，可以为多个
        //cerInput[0] = App.getsContext().getAssets().open("android.cer");

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);


        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间
        builder.hostnameVerifier(new MyHostNameVerifier());
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);//配置证书
        builder.addNetworkInterceptor(new TokenInterceptor());
        client = builder.build();

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
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

}
