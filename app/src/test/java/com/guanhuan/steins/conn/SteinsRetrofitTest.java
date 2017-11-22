package com.guanhuan.steins.conn;


import com.guanhuan.steins.App;
import com.guanhuan.steins.data.entity.User;
import com.guanhuan.steins.login.LoginLoader;
import com.litesuits.orm.db.assit.QueryBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;

import java.util.List;

import retrofit2.Call;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Created by 74405 on 2017/11/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 25, manifest = "\\app\\src\\main\\AndroidManifest.xml")
public class SteinsRetrofitTest {
    @Test
    public void steinsFactory_test(){

    }
}