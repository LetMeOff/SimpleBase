package com.zjh.simplebase.binding.recyclerview;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.zjh.simplebase.base.SimpleAdapter;
import com.zjh.simplebase.inter.OnItemClickInterface;

import java.util.List;

/**
 * recyclerView 加载相关adapter
 *
 * @author zhujianhua
 * on 2021/1/7
 */
public class RecyclerBinding {

    /**
     * recyclerView adapter
     *
     * @param recyclerView recyclerView
     * @param data         数据list
     * @param layoutId     布局id
     * @param brId         modelId
     * @param itemClick    item点击
     */
    @BindingAdapter(value = {"recyclerData", "recyclerLayoutId", "recyclerBrId", "recyclerItemClick"}, requireAll = false)
    public static void setRecyclerAdapter(RecyclerView recyclerView, List data, int layoutId, int brId, OnItemClickInterface itemClick) {
        SimpleAdapter adapter = new SimpleAdapter(data, layoutId, brId);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickInterface(itemClick);
    }

}
