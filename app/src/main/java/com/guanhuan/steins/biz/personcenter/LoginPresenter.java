package com.guanhuan.steins.biz.personcenter;


import android.util.Log;

import com.guanhuan.steins.App;
import com.guanhuan.steins.api.LoginService;
import com.guanhuan.steins.bean.entity.User;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.biz.BasePresenter;
import com.guanhuan.steins.bridge.BridgeFactory;
import com.guanhuan.steins.bridge.Bridges;
import com.guanhuan.steins.bridge.cache.DB.DBManager;
import com.guanhuan.steins.bridge.http.RetrofitServiceManager;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.http.DefaultObserver;
import com.guanhuan.steins.util.PreferencesLoader;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * <功能详细描述>
 *
 */
public class LoginPresenter extends BasePresenter<IUserLoginView> {

    LoginService loginService;

    private RetrofitServiceManager manager = BridgeFactory.getBridge(Bridges.HTTP);
    private DBManager dbmanager = BridgeFactory.getBridge(Bridges.DATABASE);

    private PreferencesLoader preferencesLoader;
    private CompositeSubscription mCompositeSubscription;

    private static final String TAG = "LoginPresenter";

    public LoginPresenter() {
        loginService = manager.create(LoginService.class);
        mCompositeSubscription = new CompositeSubscription();
        preferencesLoader = new PreferencesLoader(App.app);
    }

    public void login(final String account, String password) {
        //网络层
        mvpView.showLoading();
        Subscription s = observe(loginService.getToken(account, password))
                .subscribe(
                        new DefaultObserver<ResultModel<String>>() {
                            @Override
                            public void onSuccess(ResultModel<String> response) {
                                String token = response.getContent();
                                Log.i(TAG, "Token:"+token);
                                preferencesLoader.saveString(Constants.AUTHORIZATION, token);
                                LiteOrm liteOrm = dbmanager.getsDb();
                                List<User> userList = liteOrm.query(new QueryBuilder(User.class)
                                        .where("account = ?", new String[]{account})
                                        .limit(0,1)
                                );
                                User user;
                                if(userList != null && !userList.isEmpty()){
                                    user = userList.get(0);
                                    user.token = token;
                                    liteOrm.save(user);
                                } else {
                                    user = new User();
                                    user.account = account;
                                    user.token = token;
                                    liteOrm.save(user);
                                }
                                mvpView.clearEditContent();
                                mvpView.onSuccess();
                            }

                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "onCompleted");
                            }
                        }
                );
        mCompositeSubscription.add(s);
    }

    public void onStop() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }
}
