package com.zjh.simpledemo;

import com.zjh.simplebase.base.BaseActivity;
import com.zjh.simplebase.base.CreateViewModel;
import com.zjh.simplebase.util.DensityUtils;
import com.zjh.simplebase.util.LogUtils;
import com.zjh.simpledemo.databinding.ActivityMainBinding;
import com.zjh.simpledemo.viewmodel.MainViewModel;

/**
 * main
 *
 * @author zhujianhua
 * on 2021/1/6
 */
@CreateViewModel(viewModel = MainViewModel.class)
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.mainViewModel;
    }

    @Override
    public void initData() {
        int a = DensityUtils.dp2px(20);
        LogUtils.e("aa:" + a);
    }

    @Override
    public void initObserve() {

    }
}