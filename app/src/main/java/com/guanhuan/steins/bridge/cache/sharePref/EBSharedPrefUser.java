package com.guanhuan.steins.bridge.cache.sharePref;

import android.content.Context;

import com.guanhuan.steins.capabilities.cache.BaseSharedPreference;

/**
 * <用户信息缓存>
 *
 */
public class EBSharedPrefUser extends BaseSharedPreference {
    /**
     * 登录名
     */
    public static final String USER_NAME = "user_name";

    public EBSharedPrefUser(Context context, String fileName) {
        super(context,fileName);
    }
}
