package com.zjh.simplebase.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * base viewModel
 * @author zjh
 * on 2021/5/13
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), IBaseViewModel {

    /**
     * 加载中
     */
//    val loadingLiveData = SingleLiveData<Boolean>()

    /**
     * 接口请求
     */
    fun requestLaunch(
            //加载、处理数据
            block: suspend () -> Unit,
            //异常处理
            error: suspend (Throwable) -> Unit,
            //结束操作 非必传
            completed: (suspend () -> Unit)? = null
    ) {
//        loadingLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (e: Exception) {
                //404 或者其他特殊情况
                error(e)
            } finally {
                //结束
                completed?.let { it() }
            }
        }
    }

}