package com.cardshop.cardshop.Utils;

import android.util.Log;

public class LogUtil {
    public static final boolean isLog = true;
    public static final String TAG = "spq";

    public static void Log(String content) {
        if (!isLog)
            return;
        Log.i(TAG, content);
    }

    public static void LogE(String content) {
        if (!isLog)
            return;
        Log.e(TAG, content);
    }
}
