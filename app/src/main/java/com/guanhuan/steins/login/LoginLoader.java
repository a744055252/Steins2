package com.guanhuan.steins.login;

import android.util.Log;

import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.data.entity.ResultModel;
import com.guanhuan.steins.http.HttpCommonInterceptor;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.http.RetrofitServiceManager;
import com.guanhuan.steins.http.RetrofitServiceSingleton;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.Subscriber;

/**
 * 登陆使用
 * Created by 74405 on 2017/11/16.
 */

public class LoginLoader extends ObjectLoader {

    private LoginService loginService;

    private static final String TAG = "LoginLoader";

    public LoginLoader(){
        loginService = RetrofitServiceManager.getInstance().create(LoginService.class);
    }

    public void login(String account, String password){
        observe(loginService.getToken(account, password))
                .subscribe(
                        new Subscriber<ResultModel<String>>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "onCompleted: running");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: "+e.toString(), e);
                            }

                            @Override
                            public void onNext(ResultModel<String> result) {
                                Log.i(TAG, "Token:"+result.getContent());
                                //往请求前添加一个身份验证token
                                HttpCommonInterceptor tokenInterceptor = new HttpCommonInterceptor.Builder()
                                        .addHeaderParams(Constants.AUTHORIZATION, result.getContent())
                                        .build();
                                RetrofitServiceSingleton.getInstance(tokenInterceptor);
                            }
                        }
                );
    }


    public interface LoginService{
        @FormUrlEncoded
        @POST("User/token")
        Observable<ResultModel<String>> getToken(@Field("account") String account,
                                                 @Field("password") String password);

    }
}
