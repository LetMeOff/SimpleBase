package com.zjh.simplebase.inter;

import android.view.View;

/**
 * item点击接口
 *
 * @author zjh
 * @date 2021-01-07
 */
public interface OnItemClickInterface {

    /**
     * item点击事件
     *
     * @param view     view
     * @param position 下标
     */
    void onItemClick(View view, int position);

}
