package com.zjh.simplebase.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 请求接口
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public interface ApiService {

    /**
     * post请求
     *
     * @param api  接口地址
     * @param body 请求参数
     * @return 回调
     */
    @POST
    Observable<ApiResponse<Object>> postCall(@Url String api, @Body Map<String, Object> body);
}
