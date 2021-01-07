package com.zjh.simplebase.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.zjh.simplebase.base.BaseActivity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Activity 管理器
 * 需在 Application 中注册「registerActivityLifecycleCallbacks」
 */
public class ActivityManager implements Application.ActivityLifecycleCallbacks {

    private static ActivityManager activityManager;
    private Stack<Activity> activityStack = new Stack<>();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (activityManager == null) {
            synchronized (ActivityManager.class) {
                if (activityManager == null) {
                    activityManager = new ActivityManager();
                }
            }
        }
        return activityManager;
    }

    /**
     * Activity 入栈
     */
    private void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * Activity 出栈
     */
    private void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前Activity
     */
    public Activity getCurrentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 获取Activity栈
     */
    public Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();

    }

    /**
     * activity存在
     */
    public boolean hasActivity(Class<? extends BaseActivity> cls) {
        boolean has = false;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                has = true;
                break;
            }
        }
        return has;
    }

    /**
     * 结束某 Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            removeActivity(activity);
            activity.finish();
        }
    }

    /**
     * 结束某 Activity
     */
    public void finishActivity(Class<? extends BaseActivity> cls) {
        Iterator iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = (Activity) iterator.next();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }
}
