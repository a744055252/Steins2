package com.guanhuan.steins.biz.personcenter;

import com.guanhuan.steins.api.AritcleService;
import com.guanhuan.steins.bean.entity.ACMsg;
import com.guanhuan.steins.bean.entity.User;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.biz.BasePresenter;
import com.guanhuan.steins.biz.IAdapterView;
import com.guanhuan.steins.bridge.BridgeFactory;
import com.guanhuan.steins.bridge.Bridges;
import com.guanhuan.steins.bridge.cache.DB.DBManager;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefManager;
import com.guanhuan.steins.bridge.http.RetrofitServiceManager;
import com.guanhuan.steins.http.DefaultObserver;

import java.util.List;

import rx.Subscription;

/**
 * Created by guanhuan_li on 2017/12/8.
 */

public class BananaPresenter extends BasePresenter<IAdapterView> {

    private AritcleService aritcleService;

    private RetrofitServiceManager manager = BridgeFactory.getBridge(Bridges.HTTP);

    private List<ACMsg> data;

    public BananaPresenter() {
        this.aritcleService = manager.create(AritcleService.class);
    }

    public void getBanana(){
        mvpView.showLoading();
        Subscription s = observe(aritcleService.getBanana())
                .subscribe(new DefaultObserver<ResultModel<List<ACMsg>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<ACMsg>> response) {
                        data = response.getContent();
                        mvpView.setData(data);
                        mvpView.hideLoading();
                    }
                });
        super.mCompositeSubscription.add(s);
    }
}
