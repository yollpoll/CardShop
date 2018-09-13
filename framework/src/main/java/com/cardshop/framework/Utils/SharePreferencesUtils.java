package com.cardshop.framework.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.cardshop.framework.Base.SpqApplication;

/**
 * Created by 鹏祺 on 2016/4/1.
 */
public class SharePreferencesUtils {
    public static final String SHARED_PREFERENCE_NAME = "common";

    public static void putString(String key, String value) {
        SharedPreferences settings = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 没有默认值
     */
    public static String getString(String key) {
        return getString(key, null);
    }


    public static String getString(String key, String defaultValue) {
        SharedPreferences settings = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sharedPreferences = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key) {
        return getInt(key, -1);
    }

    public static int getInt(String key, int defultValue) {
        SharedPreferences sharedPreferences = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defultValue);
    }

    public static void putLong(String key, long value) {
        SharedPreferences sharedPreferences = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(String key) {
        return getLong(key, -1);
    }

    public static long getLong(String key, long defaultValue) {
        SharedPreferences sharedPreferences = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void putFloat(String key, Float valus) {
        SharedPreferences sharedPreferences = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, valus);
        editor.commit();

    }

    public static Float getFloat(String key, Float defaultValue) {
        SharedPreferences sharedPreferences = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, SpqApplication.getInstance().MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static Float getFloat(String key) {
        return getFloat(key);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sharedPreference = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = SpqApplication.getInstance().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

}
