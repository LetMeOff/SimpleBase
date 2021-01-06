package com.zjh.simpledemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zjh.simplebase.base.BaseViewModel;

/**
 * @author zhujianhua
 * on 2021/1/6
 */
public class MainViewModel extends BaseViewModel {

    public String text;

    public MainViewModel(@NonNull Application application) {
        super(application);

        init();
    }

    private void init() {
        text = "test";
    }

}
