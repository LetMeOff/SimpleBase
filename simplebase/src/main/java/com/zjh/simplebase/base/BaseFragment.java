package com.zjh.simplebase.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Fragment基类
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public abstract class BaseFragment<Binding extends ViewDataBinding, VM extends AndroidViewModel & IBaseViewModel> extends Fragment {

    protected Binding binding;
    protected VM viewModel;
    /**
     * 记录上次创建的view
     */
    protected View lastView = null;
    /**
     * 记录是否已经初始化过一次视图
     */
    private boolean isNavigationViewInit = false;
    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //如果fragment的view已经创建则不再重新创建
        if (lastView == null) {
            binding = DataBindingUtil.inflate(inflater, initLayoutId(), container, false);
            binding.setLifecycleOwner(this);
            lastView = binding.getRoot();
        }
        return lastView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!isNavigationViewInit) {
            //初始化过视图则不再进行view和data初始化
            super.onViewCreated(view, savedInstanceState);

            initViewDataBinding();
            //页面数据初始化方法
            initData();
            //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
            initObserve();
        }
        isNavigationViewInit = true;
    }

    /**
     * 初始化binding和viewModel
     */
    @SuppressWarnings("unchecked")
    private void initViewDataBinding() {
        CreateViewModel createViewModel = this.getClass().getAnnotation(CreateViewModel.class);
        if (null != createViewModel) {
            viewModel = (VM) new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication())).get(createViewModel.viewModel());
        } else {
            //默认使用BaseViewModel
            viewModel = (VM) new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication())).get(BaseViewModel.class);
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
