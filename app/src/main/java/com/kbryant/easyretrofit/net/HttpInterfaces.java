package com.kbryant.easyretrofit.net;


import com.kbryant.easyretrofit.Weather;
import com.kbryant.retrofit.easyretrofit.entity.BaseHttpResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Http接口
 * Created by WX on 2016/12/19.
 */

public interface HttpInterfaces {

    @GET("weather")
    Observable<BaseHttpResult<Weather>> getWeather(@Query("cityname") String placeName);

}
