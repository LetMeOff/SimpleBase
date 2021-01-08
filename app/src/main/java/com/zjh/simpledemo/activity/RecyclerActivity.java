package com.zjh.simpledemo.activity;

import com.zjh.simplebase.BR;
import com.zjh.simplebase.base.BaseActivity;
import com.zjh.simplebase.base.CreateViewModel;
import com.zjh.simpledemo.R;
import com.zjh.simpledemo.adapter.MultiTypeAdapter;
import com.zjh.simpledemo.databinding.ActivityRecyclerBinding;
import com.zjh.simpledemo.viewmodel.RecyclerViewModel;

/**
 * @author zhujianhua
 * on 2021/1/8
 */
@CreateViewModel(viewModel = RecyclerViewModel.class)
public class RecyclerActivity extends BaseActivity<ActivityRecyclerBinding, RecyclerViewModel> {
    @Override
    public int initLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public int initVariableId() {
        return BR.recyclerVM;
    }

    @Override
    public void initData() {
        initMultiRecycler();
    }

    @Override
    public void initObserve() {

    }

    /**
     * 多布局recyclerView
     */
    private void initMultiRecycler() {
        MultiTypeAdapter adapter = new MultiTypeAdapter(viewModel.multiList);
        binding.multiRv.setAdapter(adapter);
    }

}
