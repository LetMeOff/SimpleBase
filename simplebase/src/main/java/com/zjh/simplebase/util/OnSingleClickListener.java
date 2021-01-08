package com.zjh.simplebase.util;

import android.view.View;

/**
 * 单次点击事件
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public abstract class OnSingleClickListener implements View.OnClickListener {

    /**
     * 单击判断生效时间，以此时间判断生效
     */
    protected long intervalClickTime = 1000;
    /**
     * 上次点击的时间
     */
    private long lastClickTime = 0;

    @Override
    public void onClick(View view) {
        if (System.currentTimeMillis() - lastClickTime > intervalClickTime) {
            lastClickTime = System.currentTimeMillis();
            onSingleClick(view);
        }
    }

    /**
     * 点击事件
     *
     * @param view view
     */
    public abstract void onSingleClick(View view);

}
