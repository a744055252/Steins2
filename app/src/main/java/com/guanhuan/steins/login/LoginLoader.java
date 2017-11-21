package com.guanhuan.steins.login;

import android.util.Log;

import com.guanhuan.steins.App;
import com.guanhuan.steins.data.entity.ResultModel;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.http.RetrofitServiceManager;
import com.guanhuan.steins.util.PreferencesLoader;

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
                                new PreferencesLoader(App.getsContext())
                                        .saveString("Token", result.getContent());
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
