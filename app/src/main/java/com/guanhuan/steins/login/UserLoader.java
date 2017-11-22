package com.guanhuan.steins.login;

import android.util.Log;

import com.guanhuan.steins.App;
import com.guanhuan.steins.data.model.ResultModel;
import com.guanhuan.steins.data.entity.User;
import com.guanhuan.steins.http.ObjectLoader;
import com.guanhuan.steins.http.RetrofitServiceManager;
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
                                List<User> userList = App.getsDb().query(new QueryBuilder(User.class)
                                        .where("account = ?" , new String[]{user.account})
                                );

                                Log.i(TAG, "account list: " + userList.toString());

                                if(userList != null && !userList.isEmpty()){
                                    user.userId = userList.get(0).userId;
                                }
                                App.getsDb().save(user);


                                List<User> userList1 = App.getsDb().query(new QueryBuilder(User.class)
                                        .where("account = ?" , new String[]{"a744055252"})
                                );

                                Log.i(TAG, "userList1:"+userList1.toString());

                                if(userList1 != null && !userList.isEmpty()){
                                    User user1 = userList.get(0);
                                    Log.i(TAG, "_____user1:+" + user1.toString());
                                } else {
                                    Log.i(TAG, "_____userList is empty");
                                }

//                                Log.i(TAG, "____User1:"+ user1.toString());

                            }
                        }
                );
    }

    public interface UserService{

        @GET("User/user")
        Observable<ResultModel<User>> getLoginUser();

    }
}
