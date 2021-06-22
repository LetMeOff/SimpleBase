package com.zjh.simplebase.http

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 请求头拦截器
 * @author zjh
 * on 2021/5/13
 */
class HeaderInterceptor(private val headers: Map<String, String>? = null) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (headers != null) {
            //添加请求头
            val request = chain.request().newBuilder()
                    .headers(Headers.of(headers))
                    .build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request().newBuilder().build())
    }
}