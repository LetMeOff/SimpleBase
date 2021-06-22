package com.zjh.simplebase.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zjh.simplebase.util.StatusBarUtils;

/**
 * activity基类
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public abstract class BaseActivity<Binding extends ViewDataBinding, VM extends AndroidViewModel & IBaseViewModel> extends AppCompatActivity {

    protected Binding binding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtils.setStatusBarColorInt(this, Color.WHITE, true);

        initViewDataBinding();
        initData();
        initObserve();
    }

    /**
     * 初始化binding和viewModel
     */
    @SuppressWarnings("unchecked")
    private void initViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, initLayoutId());

        CreateViewModel createViewModel = this.getClass().getAnnotation(CreateViewModel.class);
        if (null != createViewModel) {
            viewModel = (VM) new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(createViewModel.viewModel());
        } else {
            //默认使用BaseViewModel
            viewModel = (VM) new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(BaseViewModel.class);
        }

        binding.setLifecycleOwner(this);

        if (initVariableId() != 0) {
            //关联viewModel
            binding.setVariable(initVariableId(), viewModel);
        }
        //ViewModel感应view的生命周期
        getLifecycle().addObserver(viewModel);
    }

    /**
     * 初始化布局
     *
     * @return id
     */
    public abstract int initLayoutId();

    /**
     * 初始化ViewModel的id
     *
     * @return id
     */
    public abstract int initVariableId();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化数据监听
     */
    public abstract void initObserve();

}
