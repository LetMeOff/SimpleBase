package com.zjh.simplebase.util;

import android.util.Log;

/**
 * log
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public class LogUtils {
    private static final String TAG = "SimpleLog";

    public static void i(String message) {
        Log.i(TAG, message);
    }

    public static void e(String message) {
        Log.e(TAG, message);
    }

    public static void w(String message) {
        Log.w(TAG, message);
    }

    public static void d(String message) {
        Log.d(TAG, message);
    }

}
