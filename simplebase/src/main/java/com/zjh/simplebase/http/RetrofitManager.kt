package com.zjh.simplebase.http

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit
 * @author zjh
 * on 2021/5/13
 */
object RetrofitManager {

    private lateinit var retrofit: Retrofit
    private var headers: Map<String, String>? = null

    fun setHeaders(headers: Map<String, String>): RetrofitManager {
        this.headers = headers
        return this
    }

    fun <T> getService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    /**
     * 初始化retrofit
     */
    fun initRetrofitManager(debug: Boolean) {
        //声明日志类
        val httpLoggingInterceptor = HttpLoggingInterceptor {
            if (debug) {
                Log.e("RetrofitManager", it)
            }
        }
        // 设定日志级别
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //自定义OkHttpClient
        val okHttpClient = OkHttpClient.Builder()
                //添加拦截器
                .addInterceptor(HeaderInterceptor(headers))
                .addInterceptor(httpLoggingInterceptor)
                //设置连接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(10, TimeUnit.SECONDS)
                //写入超时
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()

        val baseUrl = if (debug) "http://fat-hwtqapi.mloveli.com/" else "https://hwtqapi.hellocalf.com/"

        retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }
}