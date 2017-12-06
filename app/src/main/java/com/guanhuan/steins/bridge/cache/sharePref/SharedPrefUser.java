package com.guanhuan.steins.bridge.cache.sharePref;

import android.content.Context;

import com.guanhuan.steins.capabilities.cache.BaseSharedPreference;

/**
 * <用户信息缓存>
 *
 */
public class SharedPrefUser extends BaseSharedPreference {

    /** 用户文件名 */
    private static final String PREF_NAME_USERINFO = "userinfo";

    /** 登录名*/
    public static final String USER_NAME = "user_name";

    /** 用户邮箱 */
    public static final String USER_EMAIL = "user_email";

    /** 用户头像 */
    public static final String USER_IMAGE = "user_image";

    /** 用户token */
    public static final String USER_TOKEN = "user_token";

    public SharedPrefUser(Context context) {
        super(context,PREF_NAME_USERINFO);
    }

}
