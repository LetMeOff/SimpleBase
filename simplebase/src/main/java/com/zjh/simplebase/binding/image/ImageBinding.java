package com.zjh.simplebase.binding.image;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.zjh.simplebase.util.GlideUtils;
import com.zjh.simplebase.util.LogUtils;

/**
 * 图片加载相关 bindingAdapter
 *
 * @author zhujianhua
 * on 2021/1/7
 */
public class ImageBinding {

    /**
     * 加载图片
     *
     * @param imageView      imageView
     * @param imgUrl         url
     * @param imgPlaceHolder 占位图
     * @param imgError       错误显示图
     * @param imgRound       圆角
     * @param circleImg      是否加载圆形图片
     */
    @BindingAdapter(value = {"imgUrl", "imgPlaceHolder", "imgError", "imgRound", "circleImg"}, requireAll = false)
    public static void loadRoundImage(ImageView imageView, String imgUrl, int imgPlaceHolder, int imgError, int imgRound, boolean circleImg) {
        if (!TextUtils.isEmpty(imgUrl)) {
            if (circleImg) {
                //圆形图片
                GlideUtils.loadCircleImage(imageView, imgUrl, imgPlaceHolder, imgError);
            } else {
                //圆角图片
                GlideUtils.loadRoundImage(imageView, imgUrl, imgPlaceHolder, imgError, imgRound);
            }
        }
    }
}
