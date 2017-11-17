package com.guanhuan.steins.login;

import android.util.Log;

import com.guanhuan.steins.data.entity.ResultModel;
import com.guanhuan.steins.data.entity.User;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.http.RetrofitServiceSingleton;

import retrofit2.http.GET;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by guanhuan_li on 2017/11/17.
 */

public class UserLoader extends ObjectLoader {

    private UserService userService;

    private static final String TAG = "UserLoader";

    public UserLoader(){
        userService = RetrofitServiceSingleton.getInstance().create(UserService.class);
    }

    public void getLoginUser(){
        observe(userService.getLoginUser())
                .subscribe(
                        new Subscriber<ResultModel<User>>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: "+e, e);
                            }

                            @Override
                            public void onNext(ResultModel<User> userResultModel) {
                                Log.i(TAG, "user:"+userResultModel.getContent().userName);
                            }
                        }
                );
    }

    public interface UserService{

        @GET("User/user")
        Observable<ResultModel<User>> getLoginUser();

    }
}
