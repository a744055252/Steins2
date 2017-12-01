package com.guanhuan.steins.Api;

import com.guanhuan.steins.bean.entity.ACMsg;
import com.guanhuan.steins.bean.model.ResultModel;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import rx.Observable;

public interface AritcleService {
    @GET("ACMsg/allAritcle")
    Observable<ResultModel<Map<String, List<ACMsg>>>> getAllAritcle() ;
}