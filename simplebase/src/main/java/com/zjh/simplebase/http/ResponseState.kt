package com.zjh.simplebase.http

import java.lang.Exception

/**
 * 请求状态
 * @author zjh
 * on 2021/5/14
 */
sealed class ResponseState<out T : Any> {
    /**
     * 成功
     */
    data class Success<out T : Any>(val data: T) : ResponseState<T>()

    /**
     * 失败
     */
    data class Error(val exception: Exception) : ResponseState<Nothing>()
}