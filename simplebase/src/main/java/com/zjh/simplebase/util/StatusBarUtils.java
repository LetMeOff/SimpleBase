package com.zjh.simplebase.util;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.zackratos.ultimatebarx.library.UltimateBarX;

/**
 * 状态栏
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public class StatusBarUtils {

    /**
     * 状态栏颜色
     *
     * @param activity activity
     * @param color    颜色
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setStatusBarColorInt(FragmentActivity activity, @ColorInt int color, boolean light) {
        UltimateBarX.with(activity)
                .color(color)
                .light(light)
                .applyStatusBar();
    }

    /**
     * 状态栏颜色
     *
     * @param fragment fragment
     * @param color    颜色
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setStatusBarColorInt(Fragment fragment, @ColorInt int color, boolean light) {
        UltimateBarX.with(fragment)
                .color(color)
                .light(light)
                .applyStatusBar();
    }

    /**
     * 状态栏颜色
     *
     * @param activity activity
     * @param color    颜色(资源文件)
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setStatusBarColorRes(FragmentActivity activity, @ColorRes int color, boolean light) {
        UltimateBarX.with(activity)
                .colorRes(color)
                .light(light)
                .applyStatusBar();
    }

    /**
     * 状态栏颜色
     *
     * @param fragment fragment
     * @param color    颜色(资源文件)
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setStatusBarColorRes(Fragment fragment, @ColorRes int color, boolean light) {
        UltimateBarX.with(fragment)
                .colorRes(color)
                .light(light)
                .applyStatusBar();
    }

    /**
     * 状态栏透明
     *
     * @param activity activity
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setStatusBarTransparent(FragmentActivity activity, boolean light) {
        UltimateBarX.with(activity)
                .transparent()
                .light(light)
                .applyStatusBar();
    }

    /**
     * 状态栏透明
     *
     * @param fragment fragment
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setStatusBarTransparent(Fragment fragment, boolean light) {
        UltimateBarX.with(fragment)
                .transparent()
                .light(light)
                .applyStatusBar();
    }

    /**
     * 导航栏颜色
     *
     * @param activity activity
     * @param color    颜色
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setNavigationBarColorInt(FragmentActivity activity, @ColorInt int color, boolean light) {
        UltimateBarX.with(activity)
                .color(color)
                .light(light)
                .applyNavigationBar();
    }

    /**
     * 导航栏颜色
     *
     * @param fragment fragment
     * @param color    颜色
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setNavigationBarColorInt(Fragment fragment, @ColorInt int color, boolean light) {
        UltimateBarX.with(fragment)
                .color(color)
                .light(light)
                .applyNavigationBar();
    }

    /**
     * 导航栏颜色
     *
     * @param activity activity
     * @param color    颜色(资源文件)
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setNavigationBarColorRes(FragmentActivity activity, @ColorRes int color, boolean light) {
        UltimateBarX.with(activity)
                .colorRes(color)
                .light(light)
                .applyNavigationBar();
    }

    /**
     * 导航栏颜色
     *
     * @param fragment fragment
     * @param color    颜色(资源文件)
     * @param light    状态栏文字颜色 true:黑色 false:白色
     */
    public static void setNavigationBarColorRes(Fragment fragment, @ColorRes int color, boolean light) {
        UltimateBarX.with(fragment)
                .colorRes(color)
                .light(light)
                .applyNavigationBar();
    }

}
