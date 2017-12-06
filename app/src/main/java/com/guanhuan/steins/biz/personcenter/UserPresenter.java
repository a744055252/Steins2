package com.guanhuan.steins.biz.personcenter;

import android.util.Log;

import com.guanhuan.steins.api.UserService;
import com.guanhuan.steins.bean.entity.User;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.biz.BasePresenter;
import com.guanhuan.steins.bridge.BridgeFactory;
import com.guanhuan.steins.bridge.Bridges;
import com.guanhuan.steins.bridge.cache.DB.DBManager;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefManager;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefUser;
import com.guanhuan.steins.bridge.http.RetrofitServiceManager;
import com.guanhuan.steins.capabilities.cache.BaseSharedPreference;
import com.guanhuan.steins.http.DefaultObserver;
import com.litesuits.orm.db.assit.QueryBuilder;


import java.util.List;

import rx.Subscription;

/**
 * Created by guanhuan_li on 2017/12/5.
 * 调用CompositeSubscription.add()将 Subscription 加入管理避免内存泄漏
 */

public class UserPresenter extends BasePresenter<IHomeView> {

    private UserService userService;

    private static final String TAG = "UserPresenter";

    private RetrofitServiceManager manager = BridgeFactory.getBridge(Bridges.HTTP);
    private DBManager dbmanager = BridgeFactory.getBridge(Bridges.DATABASE);
    private SharedPrefManager spmanager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);

    public UserPresenter(){
        userService = manager.create(UserService.class);
    }

    public void getLoginUser(){
        mvpView.showLoading();
        Subscription s = observe(userService.getLoginUser())
                .subscribe(
                        new DefaultObserver<ResultModel<User>>() {

                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onSuccess(ResultModel<User> userResultModel) {
                                User user = userResultModel.getContent();
                                Log.i(TAG, "____user:"+user.toString());
                                //保存用户
                                List<User> userList = dbmanager.getsDb().query(new QueryBuilder(User.class)
                                        .where("account = ?" , new String[]{user.account})
                                );

                                if(userList != null && !userList.isEmpty()){
                                    user.userId = userList.get(0).userId;
                                }
                                dbmanager.getsDb().save(user);

//                                PreferencesLoader loader = new PreferencesLoader(App.app);
//
//                                if(user != null){
//                                    loader.saveString(Constants.LOGIN_USERNAME, user.userName);
//                                    loader.saveString(Constants.LOGIN_EMAIL, user.email);
//                                }
                                BaseSharedPreference userShared =
                                        spmanager.getSharedPref(SharedPrefManager.SharedPrefs.USER);
                                userShared.saveString(SharedPrefUser.USER_NAME, user.userName);
                                userShared.saveString(SharedPrefUser.USER_EMAIL, user.email);
                                //调用刷新,必须在主线程下调用ui
                                mvpView.onSuccess();
                            }

                        }
                );
        mCompositeSubscription.add(s);
    }

}
