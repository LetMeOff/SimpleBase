package com.zjh.simplebase.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.zjh.simplebase.inter.OnItemClickInterface;

import java.util.List;

/**
 * adapter 基类
 *
 * @author zhujianhua
 * on 2021/1/7
 */
public abstract class BaseAdapter<T, Binding extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> dataList;
    private OnItemClickInterface onItemClickInterface;

    public BaseAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 点击事件
     *
     * @param onItemClickInterface 点击
     */
    public void setOnItemClickInterface(OnItemClickInterface onItemClickInterface) {
        this.onItemClickInterface = onItemClickInterface;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Binding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(viewType), parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {

        Binding binding = DataBindingUtil.getBinding(holder.itemView);
        T t = dataList.get(position);

        holder.itemView.setOnClickListener(view -> {
            if (null != onItemClickInterface) {
                onItemClickInterface.onItemClick(view, position);
            }
        });

        onBindItem(binding, t, position);
    }

    @Override
    public int getItemCount() {
        return dataList != null && dataList.size() > 0 ? dataList.size() : 0;
    }

    /**
     * 获取布局
     *
     * @param viewType 方便使用不同布局
     * @return layout
     */
    protected abstract int getLayoutId(int viewType);

    /**
     * 绑定数据操作
     *
     * @param binding  DataBinding
     * @param t        数据类
     * @param position 下标
     */
    protected abstract void onBindItem(Binding binding, T t, int position);

}
