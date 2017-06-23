package com.kbryant.retrofit.easyretrofit.http;

import com.kbryant.retrofit.easyretrofit.utils.AppUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Describe :
 * Created by Administrator on 2017/5/24 0024.
 */
public class CachingControlInterceptor {

    private static int TIMEOUT_CONNECT = HttpManager.getApiSetting().getCookieNetWorkTime();
    private static int TIMEOUT_DISCONNECT = HttpManager.getApiSetting().getCookieNoNetWorkTime();

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            //获取retrofit @headers里面的参数
            String cache = chain.request().header("cache");
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存。注意这里的cacheControl是服务器返回的
            if (cacheControl == null) {
                //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
                if (cache == null || "".equals(cache)) {
                    cache = TIMEOUT_CONNECT + "";
                }
                originalResponse = originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + cache)
                        .build();
                return originalResponse;
            } else {
                return originalResponse;
            }
        }
    };

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            //离线的时候为7天的缓存。
            if (!AppUtil.isNetworkAvailable()) {
                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + TIMEOUT_DISCONNECT)
                        .build();
            }
            return chain.proceed(request);
        }
    };
}
