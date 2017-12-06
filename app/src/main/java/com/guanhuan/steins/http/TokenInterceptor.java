package com.guanhuan.steins.http;

import android.util.Log;

import com.guanhuan.steins.App;
import com.guanhuan.steins.App1;
import com.guanhuan.steins.bridge.BridgeFactory;
import com.guanhuan.steins.bridge.Bridges;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefManager;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefUser;
import com.guanhuan.steins.capabilities.cache.BaseSharedPreference;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.util.PreferencesLoader;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截器 从Preferences中读取Token 并存入请求的消息头
 * Created by guanhuan_li on 2017/11/21.
 */

public class TokenInterceptor implements Interceptor{

    private static final String TAG = "TokenInterceptor";
    SharedPrefManager spmanager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
    BaseSharedPreference preference ;


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        //如果已经包含token则不进行,如何要实现刷新则需要改变
//        if(oldRequest.header(Constants.AUTHORIZATION) != null){
//            return chain.proceed(oldRequest);
//        }

        Log.i(TAG, "intercept: TokenInterceptor running!");
        
        Request.Builder requestBuilder =  oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());

        //读取token
//        PreferencesLoader loader = new PreferencesLoader(App.app);
//        String token = loader.getString(Constants.AUTHORIZATION);
        preference =
                spmanager.getSharedPref(SharedPrefManager.SharedPrefs.USER);
        String token = preference.getString(SharedPrefUser.USER_TOKEN, "");

        //添加公共参数Token,添加到header中
        if(token != null && !"".equals(token)) {
            requestBuilder.header(Constants.AUTHORIZATION, token);
            Log.i(TAG, "token:" + token);
        }
        Request newRequest = requestBuilder.build();

        Response response = chain.proceed(newRequest);

        return response;
    }
}
