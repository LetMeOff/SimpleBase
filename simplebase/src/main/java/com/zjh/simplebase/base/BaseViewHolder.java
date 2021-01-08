package com.zjh.simplebase.base;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author zhujianhua
 * on 2021/1/7
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    BaseViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
    }
}
