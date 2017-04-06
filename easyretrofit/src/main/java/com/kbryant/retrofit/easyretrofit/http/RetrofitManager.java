package com.kbryant.retrofit.easyretrofit.http;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Describe :
 * Created by Administrator on 2017/4/6 0006.
 */
public class RetrofitManager {

    private RetrofitManager() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T getApi(Class<T> serviceClass) {
        return createRetrofit(null).create(serviceClass);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getApi(Class<T> serviceClass, ApiSetting apiSetting) {
        return createRetrofit(apiSetting).create(serviceClass);
    }

    private static Retrofit createRetrofit(ApiSetting apiSetting) {
        if (apiSetting == null)
            apiSetting = HttpManager.getApiSetting();
        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                RequestBody requestBody = request.body();
                Buffer buffer = new Buffer();
                String paramsStr = request.url().encodedPath();
                if (requestBody != null) {
                    requestBody.writeTo(buffer);
                    Charset charset = Charset.forName("UTF-8");
                    MediaType contentType = requestBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(charset);
                    }
                    paramsStr += buffer.readString(charset);
                }
                Log.i("response", paramsStr);
                return response;
            }
        });
        builder.connectTimeout(apiSetting.getConnectionTime(), TimeUnit.SECONDS);
        if (apiSetting.isCache()) {
            builder.addInterceptor(new CookieInterceptor(true, apiSetting.getUrl()));
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
