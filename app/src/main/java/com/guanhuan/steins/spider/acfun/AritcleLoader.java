package com.guanhuan.steins.spider.acfun;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.guanhuan.steins.bean.entity.ACMsg;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.http.DefaultObserver;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.bridge.http.RetrofitServiceManager;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by guanhuan_li on 2017/11/23.
 */

public class AritcleLoader extends ObjectLoader {

    private AritcleService aritcleService;

    private static final String TAG = "AritcleLoader";

    private Map<String, List<ACMsg>> result ;

    private AritcleAdapter adapter;

    private RecyclerView recyclerView;

    public AritcleLoader(RecyclerView recyclerView) {
        aritcleService = RetrofitServiceManager.getInstance()
                .create(AritcleService.class);
        this.recyclerView = recyclerView;
    }

    public void loadAritcle(){
        observe(aritcleService.getAllAritcle())
               .subscribe(new DefaultObserver<ResultModel<Map<String, List<ACMsg>>>>() {
                   @Override
                   public void onSuccess(ResultModel<Map<String, List<ACMsg>>> response) {
                       result = response.getContent();
                       Log.i(TAG, "onSuccess: aritcle load success");
                       adapter = new AritcleAdapter(result.get("banana"));
                       recyclerView.setAdapter(adapter);
                   }

                   @Override
                   public void onCompleted() {

                   }
               });

    }

    public List<ACMsg> getBanana(){
        if(result == null || result.isEmpty()){
            loadAritcle();
        }
        return result.get("banana");
    }

    public interface AritcleService {
        @GET("ACMsg/allAritcle")
        Observable<ResultModel<Map<String, List<ACMsg>>>> getAllAritcle() ;
    }
}
