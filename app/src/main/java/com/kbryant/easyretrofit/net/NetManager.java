package com.kbryant.easyretrofit.net;


import android.app.Activity;

import com.kbryant.retrofit.easyretrofit.http.ApiSetting;
import com.kbryant.retrofit.easyretrofit.http.HttpManager;
import com.kbryant.retrofit.easyretrofit.http.HttpOnNextListener;
import com.kbryant.retrofit.easyretrofit.http.RetrofitManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * 网络请求
 * Created by WX on 2017/1/19.
 */

public class NetManager {

    public static void getWeather(Activity activity, int key, String sign, HttpOnNextListener httpOnNextListener) {
        HttpManager.getInstance().doHttpRequest((RxAppCompatActivity) activity, RetrofitManager.getApi(HttpInterfaces.class).getWeather("?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json"), httpOnNextListener);
    }
}
