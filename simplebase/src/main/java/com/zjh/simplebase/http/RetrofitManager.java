package com.zjh.simplebase.http;

import com.zjh.simplebase.base.HeaderInterceptor;
import com.zjh.simplebase.util.LogUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public class RetrofitManager {

    private static RetrofitManager retrofitManager;
    private static Retrofit retrofit;
    private static volatile ApiService apiService = null;
    private Map<String, String> headers;

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    static ApiService getApiService() {
        if (apiService == null) {
            synchronized (ApiService.class) {
                if (apiService == null) {
                    apiService = retrofit.create(ApiService.class);
                }
            }
        }
        return apiService;
    }

    public RetrofitManager setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public void initRetrofitManager(String baseUrl) {
        LogUtils.e("initRetrofitManager ：" + headers.size());
        //声明日志类
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(LogUtils::e);
        //设定日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //自定义OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //添加拦截器
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HeaderInterceptor(headers))
                //设置连接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(10, TimeUnit.SECONDS)
                //写入超时
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder().
                baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        LogUtils.e("initRetrofitManager over");
    }
}
