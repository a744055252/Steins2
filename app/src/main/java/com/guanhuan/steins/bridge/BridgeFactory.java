
package com.guanhuan.steins.bridge;

import android.content.Context;

import com.guanhuan.steins.bridge.cache.DB.DBManager;
import com.guanhuan.steins.bridge.cache.localstorage.LocalFileStorageManager;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefManager;
import com.guanhuan.steins.bridge.http.RetrofitServiceManager;
import com.guanhuan.steins.bridge.security.SecurityManager;

import java.util.HashMap;


/**
 * <中间连接层>
 *
 */
public class BridgeFactory {

    private static BridgeFactory model;

    private HashMap<String, Object> mBridges;

    private BridgeFactory() {
        mBridges = new HashMap<String, Object>();
    }

    public static void init(Context context) {
        model = new BridgeFactory();
        model.iniLocalFileStorageManager();
        model.initPreferenceManager();
        model.initSecurityManager();
        model.initUserSession();
        model.initCoreServiceManager(context);
        //网络请求使用Retrofit+rxjava+okhttp+gson 进行包装
        model.initRetrofitServiceManager();
        model.initDBManager(context);
    }

    public static void destroy() {
        model.mBridges = null;
        model = null;
    }

    /**
     * 初始化本地存储路径管理类
     */
    private void iniLocalFileStorageManager() {
        LocalFileStorageManager localFileStorageManager = new LocalFileStorageManager();
        model.mBridges.put(Bridges.LOCAL_FILE_STORAGE, localFileStorageManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(localFileStorageManager);
    }

    /**
     * 初始化SharedPreference管理类
     */
    private void initPreferenceManager() {
        SharedPrefManager sharedPrefManager = new SharedPrefManager();
        model.mBridges.put(Bridges.SHARED_PREFERENCE, sharedPrefManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(sharedPrefManager);
    }

    /**
     * 网络请求管理类
     */
    private void initRetrofitServiceManager() {
        RetrofitServiceManager mRetrofitServiceManager = RetrofitServiceManager.getInstance();
        model.mBridges.put(Bridges.HTTP, mRetrofitServiceManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(mRetrofitServiceManager);
    }

    /**
     * 初始化安全模块
     */
    private void initSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        model.mBridges.put(Bridges.SECURITY, securityManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(securityManager);
    }

    /**
     * 初始化用户信息模块
     */
    private void initUserSession() {
    }

    /**
     * 初始化Tcp服务
     *
     * @param context
     */
    private void initCoreServiceManager(Context context) {
    }


    private void initDBManager(Context context) {
        DBManager dbManager = new DBManager(context);
        model.mBridges.put(Bridges.DATABASE, dbManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(dbManager);
    }

    /**
     * 通过bridgeKey {@link Bridges}来获取对应的Bridge模块
     *
     * @param bridgeKey {@link Bridges}
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <V extends Object> V getBridge(String bridgeKey) {
        final Object bridge = model.mBridges.get(bridgeKey);
        if (bridge == null) {
            throw new NullPointerException("-no defined bridge-");
        }
        return (V) bridge;
    }
}
