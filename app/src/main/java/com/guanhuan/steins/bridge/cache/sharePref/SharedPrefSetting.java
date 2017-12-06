package com.guanhuan.steins.bridge.cache.sharePref;

import android.content.Context;

import com.guanhuan.steins.capabilities.cache.BaseSharedPreference;


/**
 * <设置信息缓存>
 *
 */
public class SharedPrefSetting extends BaseSharedPreference {

    /** 设置文件名 */
    private static final String PREF_NAME_SETTING = "setting";

    /** 声音提醒 默认已开启 */
    private static final String SOUND_REMINDER = "sound_reminder";

    /** 震动提醒 默认已开启 */
    private static final String VIBRATION_REMINDER = "vibration_reminder";

    public SharedPrefSetting(Context context) {
        super(context, PREF_NAME_SETTING);
    }

}
