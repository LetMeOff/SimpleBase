package com.zjh.simplebase.http;

import androidx.annotation.NonNull;

/**
 * 请求回调
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public abstract class ApiCallback<P> {
    private Class<?> entityClass = null;

    public ApiCallback() {

    }

    public ApiCallback(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    /**
     * 请求开始
     */
    public void onStart() {
    }

    /**
     * 请求成功：相应状态正确、有数据
     *
     * @param p 返回数据
     */
    public abstract void onSuccess(P p);


    /**
     * 请求失败：后台异常、代码异常
     *
     * @param errorCode    异常码
     * @param errorMessage 异常信息
     */
    public abstract void onFailure(int errorCode, @NonNull String errorMessage);
}
