package com.zjh.simplebase.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zjh.simplebase.R;

/**
 * 图片加载类
 *
 * @author zhujianhua
 * on 2021/1/7
 */
public class GlideUtils {

    /**
     * 加载圆角图片
     *
     * @param imageView   imageView
     * @param url         url
     * @param placeHolder 占位图
     * @param error       错误显示图
     * @param round       圆角
     */
    public static void loadRoundImage(ImageView imageView, String url, int placeHolder, int error, int round) {
        try {
            if (error == 0) {
                //设置默认错误图片
                error = R.drawable.ic_error_default;
            }
            RequestOptions options = new RequestOptions().transform(new CenterCrop());
            Glide.with(imageView.getContext())
                    .asBitmap()
                    .load(url)
                    .placeholder(placeHolder)
                    .error(error)
                    .apply(options)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                            roundedBitmapDrawable.setCornerRadius(DensityUtils.dp2px(round));
                            imageView.setImageDrawable(roundedBitmapDrawable);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("图片加载错误：" + e.toString());
        }
    }

    /**
     * 加载圆形图片
     *
     * @param imageView   imageView
     * @param url         url
     * @param placeHolder 占位图
     * @param error       错误显示图
     */
    public static void loadCircleImage(ImageView imageView, String url, int placeHolder, int error) {
        try {
            if (error == 0) {
                //设置默认错误图片
                error = R.drawable.ic_error_default;
            }
            Glide.with(imageView.getContext())
                    .load(url)
                    .placeholder(placeHolder)
                    .error(error)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("图片加载错误：" + e.toString());
        }
    }

}
