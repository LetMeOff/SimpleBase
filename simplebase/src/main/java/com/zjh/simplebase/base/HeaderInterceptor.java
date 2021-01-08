package com.zjh.simplebase.base;

import androidx.annotation.NonNull;

import com.zjh.simplebase.util.LogUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhujianhua
 * on 2021/1/8
 */
public class HeaderInterceptor implements Interceptor {

    private Map<String, String> headers;

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (headers != null) {
            //添加请求头
            Request request = chain.request().newBuilder()
                    .headers(Headers.of(headers))
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(chain.request().newBuilder().build());
    }
}
