package com.zjh.simplebase.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zjh.simplebase.util.LogUtils;
import com.zjh.simplebase.util.ToastUtils;

import java.io.EOFException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 接口请求
 *
 * @author zhujianhua
 * on 2021/1/8
 */
public class ApiCall {

    private Gson gson = new Gson();

    /**
     * 请求接口
     *
     * @param api      接口
     * @param request  请求参数
     * @param callback 回调
     * @param <Q>      请求类型
     * @param <P>      返回类型
     */
    public <Q, P> void call(String api, Q request, ApiCallback<P> callback) {
        try {
            execute(api, request, callback);
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtils.e("error:" + e.toString());
            dealException(e);
        }
    }

    /**
     * 调用请求
     *
     * @param api      接口
     * @param request  请求参数
     * @param callback 回调
     * @param <Q>      请求类型
     * @param <P>      返回类型
     */
    @SuppressWarnings("unchecked")
    private <Q, P> void execute(String api, Q request, final ApiCallback callback) {
        //处理请求参数
        String postBody = handleRequest(request);
        //请求接口
        RetrofitManager.getApiService().postCall(api, gson.fromJson(postBody, Map.class))
                //被观察者在子线程执行
                .subscribeOn(Schedulers.io())
                //观察者在主线程执行
                .observeOn(AndroidSchedulers.mainThread())
                //创建观察者并订阅
                .subscribe(new ApiObserver<Object>() {
                    @Override
                    public void onStart() {
                        if (callback != null) {
                            //请求开始
                            callback.onStart();
                        }
                    }

                    @Override
                    public void onSuccess(Object s) {
                        //请求成功
                        if (callback != null) {
                            //将获取的报文转为base实体类
                            ApiResponse baseResult = gson.fromJson(obj2String(s), ApiResponse.class);
                            //状态码
                            int code = baseResult.getCode();
                            //msg
                            String msg = baseResult.getMessage() == null ? "" : baseResult.getMessage();
                            //0代表请求成功
                            if (code == 200) {
                                //获取内容
                                String result = obj2String(baseResult.getResult());
                                P p = null;
                                if (!TextUtils.isEmpty(result)) {
                                    //将result转为泛型返回
                                    Class entityClass = callback.getEntityClass();
                                    if (entityClass == null) {
                                        //未指定实体类，返回泛型的实体类
                                        Class<P> responseClass = (Class<P>) getActualTypeArgument(callback.getClass());
                                        if (responseClass != null) {
                                            if (responseClass == String.class) {
                                                p = (P) result;
                                            } else {
                                                p = gson.fromJson(result, responseClass);
                                            }
                                        }
                                    } else {
                                        //返回泛型list
                                        List<P> list = gson.fromJson(result, new TypeToken<List<P>>() {
                                        }.getType());
                                        callback.onSuccess(list);
                                    }
                                } else {
                                    //result无内容
                                    LogUtils.i("result null");
                                }
                                callback.onSuccess(p);
                            } else {
                                //除0外的状态为失败 提示消息
                                callback.onFailure(code, msg);
                            }
                        }
                    }

                    @Override
                    public void onException(Throwable e) {
                        //异常错误
                        LogUtils.i("api onException:" + e.toString());
                        dealException(e);
                    }
                });
    }

    /**
     * 数据异常处理
     *
     * @param e 错误类型
     */
    private void dealException(Throwable e) {
        if (e instanceof JsonSyntaxException) {
            ToastUtils.getInstance().show("数据异常");
        } else if (e instanceof EOFException) {
            ToastUtils.getInstance().show("服务器连接异常");
        } else if (e instanceof ConnectException) {
            ToastUtils.getInstance().show("服务器连接异常");
        } else if (e instanceof SocketException) {
            ToastUtils.getInstance().show("服务器连接异常");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtils.getInstance().show("服务器连接超时");
        } else if (e instanceof UnknownHostException) {
            ToastUtils.getInstance().show("网络连接异常");
        } else {
            ToastUtils.getInstance().show("异常错误");
        }
    }

    /**
     * 获取泛型类型
     */
    private Class<?> getActualTypeArgument(Class<?> clazz) {
        try {
            Class<?> entityClass = null;
            Type genericSuperclass = clazz.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
                        .getActualTypeArguments();
                if (actualTypeArguments.length > 0) {
                    entityClass = (Class<?>) actualTypeArguments[0];
                }
            }
            return entityClass;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理请求参数 添加一些公共参数
     *
     * @param request 请求参数
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    private String handleRequest(Object request) {

        Map requestMap;
        if (request instanceof Map) {
            requestMap = (Map) request;
        } else {
            requestMap = str2Map(obj2String(request));
        }

        long time = System.currentTimeMillis();
        requestMap.put("sendTime", time);
//        requestMap.put("deviceId", DeviceId(context));
//        requestMap.put("sign", MD5(DeviceId(context) + time + "9uxxPbd3jvgE4gTB1LD5RlDwKNj9eFBX"));
//        requestMap.put("userId", "10000211");
//        requestMap.put("accessToken", "691146445303682657453245");

        return obj2String(requestMap);
    }

    /**
     * object转json字符串
     *
     * @param object 对象
     * @return 返回字符串
     */
    private String obj2String(Object object) {
        return gson.toJson(object);
    }

    /**
     * Json转Mao
     *
     * @param json json字符串
     * @return 返回的map
     */
    private Map str2Map(String json) {
        return gson.fromJson(json, Map.class);
    }

    /**
     *
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    private String DeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            return tm.getDeviceId();
        } catch (Exception e) {
            return "DCBA12345";
        }
    }

    /**
     *
     */
    private String MD5(String input) {
        try {
            // 获得 MD5 摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为十六进制数
            for (byte aMd : md) {
                String shaHex = Integer.toHexString(aMd & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
