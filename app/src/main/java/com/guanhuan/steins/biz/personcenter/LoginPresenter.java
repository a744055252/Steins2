package com.guanhuan.steins.biz.personcenter;


import android.util.Log;

import com.guanhuan.steins.Api.LoginService;
import com.guanhuan.steins.bean.entity.User;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.biz.BasePresenter;
import com.guanhuan.steins.bridge.BridgeFactory;
import com.guanhuan.steins.bridge.Bridges;
import com.guanhuan.steins.bridge.cache.DB.DBManager;
import com.guanhuan.steins.bridge.http.RetrofitServiceManager;
import com.guanhuan.steins.http.DefaultObserver;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

/**
 * <功能详细描述>
 *
 * @author caoyinfei
 * @version [版本号, 2016/5/4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginPresenter extends BasePresenter<IUserLoginView> {

    LoginService loginService;

    RetrofitServiceManager manager = BridgeFactory.getBridge(Bridges.HTTP);
    DBManager dbmanager = BridgeFactory.getBridge(Bridges.DATABASE);

    private static final String TAG = "LoginPresenter";

    public LoginPresenter() {
        loginService = manager.create(LoginService.class);
    }

    public void login(final String account, String password) {
        //网络层
        mvpView.showLoading();
        observe(loginService.getToken(account, password))
                .subscribe(
                        new DefaultObserver<ResultModel<String>>() {
                            @Override
                            public void onSuccess(ResultModel<String> response) {
                                String token = response.getContent();
                                Log.i(TAG, "Token:"+token);
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

                            }

                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "onCompleted");
                            }
                        }
                );
//        SecurityManager securityManager = BridgeFactory.getBridge(Bridges.SECURITY);

//        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
//
//        httpManager.requestAsyncPostByTag(URLUtil.USER_LOGIN, getName(), new ITRequestResult<LoginResp>() {
//                    @Override
//                    public void onCompleted() {
//                        mvpView.hideLoading();
//                    }
//
//                    @Override
//                    public void onSuccessful(LoginResp entity) {
//                        mvpView.onSuccess();
//                        EBSharedPrefManager manager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
//                        manager.getKDPreferenceUserInfo().saveString(EBSharedPrefUser.USER_NAME, "abc");
//                    }
//
//                    @Override
//                    public void onFailure(String errorMsg) {
//                        mvpView.onError(errorMsg, "");
//                    }
//
//                }, LoginResp.class, new Param("username", useName),
//                new Param("pas", securityManager.get32MD5Str(password)));
    }
}
