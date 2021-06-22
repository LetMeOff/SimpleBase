package com.zjh.simplebase.http

import androidx.annotation.Keep

/**
 * 接口统一返回参数
 * @author zjh
 * on 2021/5/13
 */
@Keep
class BaseResponse<T>(
        var code: Int,
        var msg: String,
        var data: T,
        var timestamp: Long
)