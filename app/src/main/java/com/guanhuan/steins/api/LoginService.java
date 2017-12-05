package com.guanhuan.steins.api;

import com.guanhuan.steins.bean.model.ResultModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface LoginService{
    @FormUrlEncoded
    @POST("User/token")
    Observable<ResultModel<String>> getToken(@Field("account") String account,
                                             @Field("password") String password);

}