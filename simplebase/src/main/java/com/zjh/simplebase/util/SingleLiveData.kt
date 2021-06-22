package com.zjh.simplebase.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 防止某些liveData重复收到通知
 * @author zjh
 * on 2021/5/14
 */
class SingleLiveData<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    companion object {
        private const val TAG = "SingleLiveData"
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            SimpleLog.e(TAG, "多个观察者存在的时候，只会有一个被通知到数据更新")
        }

        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }

}