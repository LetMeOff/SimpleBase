package com.zjh.simplebase.base;

import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * 单viewType adapter
 *
 * @author zhujianhua
 * on 2021/1/7
 */
public class SimpleAdapter<T, Binding extends ViewDataBinding> extends BaseAdapter<T, Binding> {

    private int layoutId;
    private int brId;

    public SimpleAdapter(List<T> dataList, int layoutId, int brId) {
        super(dataList);
        this.layoutId = layoutId;
        this.brId = brId;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return layoutId;
    }

    @Override
    protected void onBindItem(Binding binding, T t, int position) {
        binding.setVariable(brId, t);
    }

}
