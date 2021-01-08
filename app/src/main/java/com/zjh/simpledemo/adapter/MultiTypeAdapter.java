package com.zjh.simpledemo.adapter;

import androidx.databinding.ViewDataBinding;

import com.zjh.simplebase.base.BaseAdapter;
import com.zjh.simpledemo.R;
import com.zjh.simpledemo.databinding.AdapterMulti1Binding;
import com.zjh.simpledemo.databinding.AdapterMulti2Binding;
import com.zjh.simpledemo.model.MultiTypeInfo;

import java.util.List;

/**
 * @author zhujianhua
 * on 2021/1/8
 */
public class MultiTypeAdapter extends BaseAdapter<MultiTypeInfo, ViewDataBinding> {

    public MultiTypeAdapter(List<MultiTypeInfo> dataList) {
        super(dataList);
    }

    @Override
    protected int getLayoutId(int viewType) {
        if (viewType == 1) {
            return R.layout.adapter_multi1;
        } else {
            return R.layout.adapter_multi2;
        }
    }

    @Override
    protected void onBindItem(ViewDataBinding binding, MultiTypeInfo multiTypeInfo, int position) {
        if (binding instanceof AdapterMulti1Binding) {
            AdapterMulti1Binding multi1Binding = (AdapterMulti1Binding) binding;
            multi1Binding.setMultiInfo(multiTypeInfo);
        } else if (binding instanceof AdapterMulti2Binding) {
            AdapterMulti2Binding multi2Binding = (AdapterMulti2Binding) binding;
            multi2Binding.setMultiInfo(multiTypeInfo);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getDataList().get(position).viewType;
    }
}
