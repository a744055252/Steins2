package com.guanhuan.steins.bridge.cache.DB;

import android.content.Context;

import com.guanhuan.steins.BuildConfig;
import com.guanhuan.steins.bridge.BridgeLifeCycleListener;
import com.litesuits.orm.LiteOrm;

/**
 * Created by guanhuan_li on 2017/12/1.
 */

public class DBManager implements BridgeLifeCycleListener {

    private static final String DB_NAME = "steins.db";

    private static LiteOrm sDb;

    public DBManager(Context context){
        sDb = LiteOrm.newSingleInstance(context, DB_NAME);
        if (BuildConfig.DEBUG) {
            sDb.setDebugged(true);
        }
    }

    public LiteOrm getsDb(){
        return sDb;
    }

    @Override
    public void initOnApplicationCreate(Context context) {
    }

    @Override
    public void clearOnApplicationQuit() {
        if (sDb != null) {
            sDb.close();
        }
        sDb = null;
    }
}
