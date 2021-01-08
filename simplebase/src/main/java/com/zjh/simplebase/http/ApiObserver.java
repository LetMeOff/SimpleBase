package com.zjh.simplebase.http;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 请求监听
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public abstract class ApiObserver<T> implements Observer<T> {

    /**
     * 建立链接,代表正式与被观察者关联
     */
    @Override
    public final void onSubscribe(Disposable d) {
        onStart();
    }

    /**
     * 收到事件，代表通讯成功，一切正常
     */
    @Override
    public final void onNext(T value) {
        onSuccess(value);
    }

    /**
     * 收到错误事件，代表通讯出问题了,也不会再接收事件了
     */
    @Override
    public final void onError(Throwable e) {
        onException(e);
    }

    /**
     * 事件接收完成，不管再发送多少事件过来，都不会再接收了
     */
    @Override
    public final void onComplete() {
    }

    //==============================================================================================

    /**
     * 接口请求前
     */
    public abstract void onStart();

    /**
     * 请求成功
     *
     * @param t 返回数据
     */
    public abstract void onSuccess(T t);

    /**
     * 请求失败
     *
     * @param e exception
     */
    public abstract void onException(Throwable e);
}
