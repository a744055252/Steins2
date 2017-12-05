package com.guanhuan.steins;

import android.app.Activity;
import android.app.Application;

import com.guanhuan.steins.bridge.BridgeFactory;
import com.guanhuan.steins.bridge.BridgeLifeCycleSetKeeper;
import com.guanhuan.steins.bridge.Bridges;
import com.guanhuan.steins.bridge.cache.localstorage.LocalFileStorageManager;
import com.guanhuan.steins.util.Toasts;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <应用初始化> <功能详细描述>
 * 
 */
public class App extends Application
{
    /**
     * app实例
     */
    public static App app = null;
    
    /**
     * 本地activity栈
     */
    public static List<Activity> activitys = new ArrayList<Activity>();
    
    /**
     * 当前avtivity名称
     */
    public static String currentActivityName = "";
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        app = this;
        BridgeFactory.init(this);
        Toasts.register(this);
        BridgeLifeCycleSetKeeper.getInstance().initOnApplicationCreate(this);
        LocalFileStorageManager manager = BridgeFactory.getBridge(Bridges.LOCAL_FILE_STORAGE);
//        Picasso picasso = new Picasso.Builder(this).downloader(
//                new OkHttpDownloader(new File(manager.getCacheImgFilePath(this)))).build();
//        Picasso.setSingletonInstance(picasso);

    }


    @Override
    public void onTerminate()
    {
        super.onTerminate();
        onDestory();
    }

    /**
     * 退出应用，清理内存
     */
    private void onDestory() {
        BridgeLifeCycleSetKeeper.getInstance().clearOnApplicationQuit();
    }


    /**
     * 
     * <添加> <功能详细描述>
     * 
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void addActivity(Activity activity)
    {
        activitys.add(activity);
    }
    
    /**
     * 
     * <删除>
     * <功能详细描述>
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void deleteActivity(Activity activity)
    {
        if (activity != null)
        {
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }
}
