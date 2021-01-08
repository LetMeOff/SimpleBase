package com.zjh.simpledemo.activity;

import android.content.Intent;

import com.zjh.simplebase.base.BaseActivity;
import com.zjh.simplebase.base.BaseViewModel;
import com.zjh.simpledemo.R;
import com.zjh.simpledemo.databinding.ActivityMainBinding;

/**
 * main
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public void initData() {
        binding.img.setOnClickListener(view -> startActivity(new Intent(this, ImageActivity.class)));
        binding.recycler.setOnClickListener(view -> startActivity(new Intent(this, RecyclerActivity.class)));
    }

    @Override
    public void initObserve() {

    }
}