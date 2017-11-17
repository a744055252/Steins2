package com.guanhuan.steins.http;

/**
 * 总感觉不对，后续再看看
 * Created by guanhuan_li on 2017/11/17.
 */

public class RetrofitServiceSingleton {

    private static volatile InterRetrofitServiceManager instance;

    public static InterRetrofitServiceManager getInstance(){
        if(isCreated()){
            synchronized (RetrofitServiceSingleton.class) {
                if(isCreated()) {
                    return instance;
                }
            }
        } else {
            throw new NullPointerException("应先调用getInstance(interceptor i)创建");
        }
        return null;
    }

    public static InterRetrofitServiceManager getInstance(HttpCommonInterceptor interceptor){
        if(!isCreated()){
            synchronized (RetrofitServiceSingleton.class){
                if(!isCreated()){
                    instance = new InterRetrofitServiceManager(interceptor);
                }
            }
        }
        return instance;
    }

    public static boolean isCreated(){
        return instance != null;
    }
}
