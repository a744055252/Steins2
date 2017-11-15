package com.guanhuan.steins.conn;

import android.database.Observable;

import org.junit.Test;

import retrofit2.Call;
import rx.android.schedulers.AndroidSchedulers;

import static org.junit.Assert.*;

/**
 * Created by 74405 on 2017/11/15.
 */
public class SteinsRetrofitTest {
    @Test
    public void steinsFactory_test(){
        SteinsFactory.getUserIOSingleton()
                .getToken("a744055252", "1111111")
                .observeOn(AndroidSchedulers.mainThread())

    }
}