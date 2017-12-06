
package com.guanhuan.steins.bridge.cache.sharePref;

import android.content.Context;
import android.content.res.Resources;

import com.guanhuan.steins.bridge.BridgeLifeCycleListener;
import com.guanhuan.steins.capabilities.cache.BaseSharedPreference;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * <管理SharedPreference存储、读取>
 *
 */
public class SharedPrefManager implements BridgeLifeCycleListener {

    private Map<SharedPrefs, BaseSharedPreference> sharedPrefMap;

    private static final Map<SharedPrefs, Class> sharedPrefClassMap;

    private Context mApplicationContext;

    static{
        sharedPrefClassMap = new HashMap<>();
        sharedPrefClassMap.put(SharedPrefs.USER, SharedPrefUser.class);
        sharedPrefClassMap.put(SharedPrefs.SETTING, SharedPrefSetting.class);
    }

    @Override
    public void initOnApplicationCreate(Context context) {
        mApplicationContext = context;
        sharedPrefMap = new HashMap<>();
    }

    @Override
    public void clearOnApplicationQuit() {
        sharedPrefMap = null;
    }

    public BaseSharedPreference getSharedPref(SharedPrefs sharedPref) {
        if (sharedPref == null) {
            throw new NullPointerException("SharedPrefs不能为空");
        }
        Class clazz = sharedPrefClassMap.get(sharedPref);
        if(clazz == null){
            throw new Resources.NotFoundException("不存在名字为"+ sharedPref.value + "的EBSharedPref类");
        }
        BaseSharedPreference sharedPreference = sharedPrefMap.get(sharedPref);
        if(sharedPreference == null){
            try {
                //这里的BaseSharedPreference是带参数的
                Class[] parameterTypes = {Context.class};
                Constructor<BaseSharedPreference> constructor =
                        clazz.getConstructor(parameterTypes);
                sharedPreference = constructor.newInstance(mApplicationContext);
            } catch (Exception e) {
                throw new RuntimeException(sharedPref.getValue()+":无法实例化", e);
            }
            sharedPrefMap.put(sharedPref, sharedPreference);
        }
        return sharedPreference;
    }

    public enum SharedPrefs{

        /** 用户信息缓存 */
        USER("userinfo"),

        /** 设置信息缓存 */
        SETTING("setting");

        private String value;

        SharedPrefs(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
