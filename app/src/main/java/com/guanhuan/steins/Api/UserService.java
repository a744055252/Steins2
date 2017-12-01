package com.guanhuan.steins.Api;

import com.guanhuan.steins.bean.entity.User;
import com.guanhuan.steins.bean.model.ResultModel;

import retrofit2.http.GET;
import rx.Observable;

public interface UserService{
    @GET("User/user")
    Observable<ResultModel<User>> getLoginUser();

}