package com.zjh.simpledemo.activity;

import com.zjh.simplebase.BR;
import com.zjh.simplebase.base.BaseActivity;
import com.zjh.simplebase.base.CreateViewModel;
import com.zjh.simpledemo.R;
import com.zjh.simpledemo.databinding.ActivityImageBinding;
import com.zjh.simpledemo.viewmodel.ImageViewModel;

/**
 * @author zhujianhua
 * on 2021/1/7
 */
@CreateViewModel(viewModel = ImageViewModel.class)
public class ImageActivity extends BaseActivity<ActivityImageBinding, ImageViewModel> {
    @Override
    public int initLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    public int initVariableId() {
        return BR.imageVM;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initObserve() {

    }
}
