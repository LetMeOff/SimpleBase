package com.zjh.simplebase.http

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * 接口请求基类
 * @author zjh
 * on 2021/5/14
 */
open class BaseRepository {
    /**
     * 处理接口的返回结果
     */
    suspend fun <T : Any> executeResponse(
            response: BaseResponse<T>,
            //成功操作 非必传
            successBlock: (suspend CoroutineScope.() -> Unit)? = null,
            //失败的操作 非必传
            errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): ResponseState<T> {
        return coroutineScope {
            //code为0代表成功 其他代表失败
            if (response.code == 0) {
                successBlock?.let { it() }
                ResponseState.Success(response.data)
            } else {
                errorBlock?.let { it() }
                ResponseState.Error(IOException(response.msg))
            }
        }
    }
}