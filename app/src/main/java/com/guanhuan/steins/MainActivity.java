package com.guanhuan.steins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.guanhuan.steins.conn.SteinsFactory;
import com.guanhuan.steins.conn.UserApi;
import com.guanhuan.steins.data.entity.ResultModel;
import com.guanhuan.steins.data.entity.User;
import com.guanhuan.steins.login.LoginLoader;
import com.guanhuan.steins.login.UserLoader;
import com.guanhuan.steins.util.Toasts;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private LoginLoader loginLoader;
    private UserLoader userLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginLoader = new LoginLoader();
        loginLoader.login("a744055252", "11111111");
        userLoader = new UserLoader();
        userLoader.getLoginUser();
    }


}
