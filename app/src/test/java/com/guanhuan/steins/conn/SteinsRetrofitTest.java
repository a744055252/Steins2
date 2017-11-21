package com.guanhuan.steins.conn;

import android.content.SharedPreferences;
import android.database.Observable;
import android.provider.Settings;

import com.guanhuan.steins.App;
import com.guanhuan.steins.login.LoginLoader;
import com.guanhuan.steins.util.PreferencesLoader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import retrofit2.Call;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Created by 74405 on 2017/11/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 25)
public class SteinsRetrofitTest {
    @Test
    public void steinsFactory_test(){
        LoginLoader loginLoader = new LoginLoader();
        loginLoader.login("a744055252", "11111111");

        PreferencesLoader loader = new PreferencesLoader(App.getsContext());
        System.out.print(loader.getString("Token"));
    }
}