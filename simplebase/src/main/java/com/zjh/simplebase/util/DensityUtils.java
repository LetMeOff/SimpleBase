package com.zjh.simplebase.util;

import com.zjh.simplebase.manager.ActivityManager;

/**
 * @author zhujianhua
 * on 2021/1/6
 */
public class DensityUtils {

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(final float dpValue) {
        final float scale = ActivityManager.getInstance().getCurrentActivity().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(final float pxValue) {
        final float scale = ActivityManager.getInstance().getCurrentActivity().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
