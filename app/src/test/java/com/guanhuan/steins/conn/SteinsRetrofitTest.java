package com.guanhuan.steins.conn;

import android.database.Observable;
import android.provider.Settings;

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
        UserApi api = SteinsFactory.getUserIOSingleton();
        api.getToken("a744055252", "1111111")
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.print("ok");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.print(e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.print("Token:" + s);
                    }
                });

    }
}