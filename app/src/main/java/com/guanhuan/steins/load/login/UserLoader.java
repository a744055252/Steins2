package com.guanhuan.steins.load.login;

import android.content.Intent;
import android.util.Log;

import com.guanhuan.steins.App1;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.bean.entity.User;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.bridge.http.RetrofitServiceManager;
import com.guanhuan.steins.util.PreferencesLoader;
import com.guanhuan.steins.util.Toasts;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by guanhuan_li on 2017/11/17.
 */

public class UserLoader extends ObjectLoader {

    private UserService userService;

    private static final String TAG = "UserLoader";


    public UserLoader(){
        userService = RetrofitServiceManager.getInstance().create(UserService.class);
    }

    public void getLoginUser(){
        observe(userService.getLoginUser())
                .subscribe(
                        new Subscriber<ResultModel<User>>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: "+e, e);
                            }

                            @Override
                            public void onNext(ResultModel<User> userResultModel) {
                                User user = userResultModel.getContent();
                                Log.i(TAG, "____user:"+user.toString());
                                //保存用户
                                List<User> userList = App1.getsDb().query(new QueryBuilder(User.class)
                                        .where("account = ?" , new String[]{user.account})
                                );

                                if(userList != null && !userList.isEmpty()){
                                    user.userId = userList.get(0).userId;
                                }
                                App1.getsDb().save(user);

                                PreferencesLoader loader = new PreferencesLoader(App1.getsContext());
                                if(user != null){
                                    loader.saveString(Constants.LOGIN_USERNAME, user.userName);
                                    loader.saveString(Constants.LOGIN_EMAIL, user.email);
                                }
                                Toasts.showShort(user.userName+"登陆成功");
                                //发送一次刷新请求
                                Intent intent = new Intent(Constants.ACTION_REFRESH_USER);
                                App1.getsContext().sendBroadcast(intent);
                            }
                        }
                );
    }

    public interface UserService{

        @GET("User/user")
        Observable<ResultModel<User>> getLoginUser();

    }
}
