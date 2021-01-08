package com.zjh.simplebase.binding.view;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.zjh.simplebase.util.OnSingleClickListener;

/**
 * view 相关binding
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public class ClickBinding {

    /**
     * 设置单次点击 binding
     *
     * @param view     view
     * @param listener 单次点击事件
     */
    @BindingAdapter(value = ("onSingleClick"))
    public static void setSingleClick(View view, OnSingleClickListener listener) {
        view.setOnClickListener(listener);
    }

}
