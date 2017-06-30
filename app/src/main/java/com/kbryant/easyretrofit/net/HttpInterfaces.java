package com.kbryant.easyretrofit.net;


import com.kbryant.easyretrofit.BJTime;
import com.kbryant.easyretrofit.BaseHttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Http接口
 * Created by WX on 2016/12/19.
 */

public interface HttpInterfaces {

    @GET
    Observable<BaseHttpResult<BJTime>> getWeather(@Url String url);

}
