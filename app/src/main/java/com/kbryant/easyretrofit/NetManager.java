package com.kbryant.easyretrofit;


import com.kbryant.retrofit.easyretrofit.http.HttpManager;
import com.kbryant.retrofit.easyretrofit.http.HttpOnNextListener;
import com.kbryant.retrofit.easyretrofit.http.RetrofitManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * 网络请求
 * Created by WX on 2017/1/19.
 */

public class NetManager {

    public static void getWeather(RxAppCompatActivity activity, String placeName, HttpOnNextListener simpleOnNextListener) {
        HttpManager.getInstance().doHttpDeal(activity, RetrofitManager.getApi(HttpInterfaces.class).getWeather(placeName), simpleOnNextListener);
    }
}
