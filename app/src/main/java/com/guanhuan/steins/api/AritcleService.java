package com.guanhuan.steins.api;

import com.guanhuan.steins.bean.entity.ACMsg;
import com.guanhuan.steins.bean.model.ResultModel;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface AritcleService {
    @GET("ACMsg/allAritcle")
    Observable<ResultModel<Map<String, List<ACMsg>>>> getAllAritcle() ;

    @GET("ACMsg/banana/{refresh}")
    Observable<ResultModel<List<ACMsg>>> getBanana(@Path("refresh") boolean refresh);
}