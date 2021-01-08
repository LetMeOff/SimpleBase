package com.zjh.simplebase.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.zjh.simplebase.R;
import com.zjh.simplebase.manager.ActivityManager;

/**
 * Toast
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public class ToastUtils {

    private static ToastUtils instance = null;
    private Toast mToast = null;

    public static ToastUtils getInstance() {
        if (instance == null) {
            synchronized (ToastUtils.class) {
                if (instance == null) {
                    instance = new ToastUtils();
                }
            }
        }
        return instance;
    }

    @SuppressLint("ShowToast")
    public void show(String text) {
        Context context = ActivityManager.getInstance().getCurrentActivity();
        if (context == null) {
            LogUtils.e("context为null");
            return;
        }
        if (TextUtils.isEmpty(text)) {
            return;
        }
        cancel();
        try {
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
            TextView toastTv = view.findViewById(R.id.toast_tv);
            toastTv.setText(text);
            mToast.setView(view);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show(@StringRes int resId) {
        String text;
        try {
            Context context = ActivityManager.getInstance().getCurrentActivity();
            if (context == null) {
                LogUtils.e("context为null");
                return;
            }
            text = context.getResources().getString(resId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            text = String.valueOf(resId);
        }
        show(text);
    }

    /**
     * 销毁
     */
    private void cancel() {
        if (mToast != null) {
            try {
                mToast.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mToast = null;
        }
    }

}
