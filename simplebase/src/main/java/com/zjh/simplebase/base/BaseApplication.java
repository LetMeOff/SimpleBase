package com.zjh.simplebase.base;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zjh.simplebase.manager.ActivityManager;

import java.util.HashMap;
import java.util.Map;

/**
 * application 基类
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initApplication(this);
    }

    /**
     * 如果没有继承BaseApplication 可以调用initApplication初始化
     *
     * @param application application
     */
    public static synchronized void initApplication(@NonNull Application application) {
        ActivityManager.INSTANCE.init(application);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private static Map<String, String> getHeaders() {
        HashMap<String, String> headersMap = new HashMap<>(16);
        headersMap.put("header", "header");
        return headersMap;
    }

}
