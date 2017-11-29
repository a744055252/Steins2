package com.guanhuan.steins.login;

import android.app.Activity;
import android.util.Log;

import com.guanhuan.steins.App;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.data.entity.User;
import com.guanhuan.steins.data.model.ResultModel;
import com.guanhuan.steins.http.DefaultObserver;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.http.RetrofitServiceManager;
import com.guanhuan.steins.util.PreferencesLoader;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.Subscriber;

/**
 * 登陆使用
 * Created by 74405 on 2017/11/16.
 */

public class LoginLoader extends ObjectLoader {

    private LoginService loginService;
    private UserLoader userLoader;

    private static final String TAG = "LoginLoader";

    private Activity activity;

    public LoginLoader(Activity activity){
        this.activity = activity;
        loginService = RetrofitServiceManager.getInstance()
                .create(LoginService.class);
        userLoader = new UserLoader();
    }

    public void login(final String account, String password){

        observe(loginService.getToken(account, password))
                .subscribe(
                        new DefaultObserver<ResultModel<String>>(activity) {
                            @Override
                            public void onSuccess(ResultModel<String> response) {
                                String token = response.getContent();
                                Log.i(TAG, "Token:"+token);
                                LiteOrm liteOrm = App.getsDb();
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

                                //将token存储起来
                                PreferencesLoader loader = new PreferencesLoader(App.getsContext());
                                loader.saveString(Constants.AUTHORIZATION, token);
                                userLoader.getLoginUser();
                            }

                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "onCompleted");
                            }
                        }
                );
    }


    public interface LoginService{
        @FormUrlEncoded
        @POST("User/token")
        Observable<ResultModel<String>> getToken(@Field("account") String account,
                                                 @Field("password") String password);

    }
}
