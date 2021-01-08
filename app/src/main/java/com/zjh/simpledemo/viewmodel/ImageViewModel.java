package com.zjh.simpledemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zjh.simplebase.base.BaseViewModel;
import com.zjh.simpledemo.R;

/**
 * @author zhujianhua
 * on 2021/1/7
 */
public class ImageViewModel extends BaseViewModel {

    public String imgUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa4.att.hudong.com%2F27%2F67%2F01300000921826141299672233506.jpg&refer=http%3A%2F%2Fa4.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612595118&t=9feb0fc5ff6ccc535bceb463621610e2";
    public int errorUrl = R.drawable.ic_error_default;

    public ImageViewModel(@NonNull Application application) {
        super(application);
    }
}
