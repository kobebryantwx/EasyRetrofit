package com.kbryant.retrofit.easyretrofit.http;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Describe :Retrofit管理类
 * Created by Wx on 2017/4/6 0006.
 */
public class RetrofitManager {

    private RetrofitManager() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T getApi(Class<T> serviceClass) {
        return createRetrofit(null, null).create(serviceClass);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getApi(Class<T> serviceClass, OkHttpClient.Builder builder) {
        return createRetrofit(null, builder).create(serviceClass);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getApi(Class<T> serviceClass, ApiSetting apiSetting, OkHttpClient.Builder builder) {
        return createRetrofit(apiSetting, builder).create(serviceClass);
    }

    private static Retrofit createRetrofit(ApiSetting apiSetting, OkHttpClient.Builder builder) {
        if (apiSetting == null)
            apiSetting = HttpManager.getApiSetting();
        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        if (builder == null) {
            builder = new OkHttpClient.Builder();
        }
        //设置日志Level
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging)
                .connectTimeout(apiSetting.getConnectionTime(), TimeUnit.SECONDS);
        if (apiSetting.isCache()) {//缓存
            Cache cache = new Cache(new File(apiSetting.getCacheFile()), apiSetting.getCacheSize());
            //有网络时的拦截器
            builder.addNetworkInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR)
                    //没网络时的拦截器
                    .addInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                    .cache(cache);
        }
        /*创建retrofit对象*/
        return new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(apiSetting.getBaseUrl())
                .build();
    }
}
