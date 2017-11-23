package com.guanhuan.steins.spider.acfun;

import android.util.Log;

import com.guanhuan.steins.data.entity.ACMsg;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.http.ResultModel;
import com.guanhuan.steins.http.RetrofitServiceManager;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by guanhuan_li on 2017/11/23.
 */

public class AritcleLoader extends ObjectLoader {

    private AritcleService aritcleService;

    private static final String TAG = "AritcleLoader";

    public AritcleLoader() {
        aritcleService = RetrofitServiceManager.getInstance()
                .create(AritcleService.class);
    }

    public void loadAritcle(){
        observe(aritcleService.getAllAritcle())
               .map(new Func1<ResultModel<Map<String,List<ACMsg>>>, Map<String, List<ACMsg>>>() {
                   @Override
                   public Map<String, List<ACMsg>> call(ResultModel<Map<String, List<ACMsg>>> mapResultModel) {
                       Log.i(TAG, "resultModel:" + mapResultModel.getMessage());
                       return mapResultModel.getContent();
                   }
               })
               .subscribe(new Action1<Map<String, List<ACMsg>>>() {
                   @Override
                   public void call(Map<String, List<ACMsg>> stringListMap) {
                       for(Map.Entry<String, List<ACMsg>> entry : stringListMap.entrySet()){
                           Log.i(TAG, "__call:"+entry.getKey()+"_"+entry.getValue().get(0).acUrl);
                       }
                   }
               })
        ;
    }

    public interface AritcleService {
        @GET("ACMsg/allAritcle")
        Observable<ResultModel<Map<String, List<ACMsg>>>> getAllAritcle() ;
    }
}
