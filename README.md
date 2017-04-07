# EasyRetrofit
Rxjava2+Retrofit2网路请求封装库，包括多任务下载。

# Usage
* 自定义Application继承RetrofitApplication(必须);
* 在自定义Application的oncreate()中，使用HttpManager.getApiSetting()配置baseUrl等；
* Activity继承RxAppCompatActivity(必须)；
* 创建interface类，例如：  
<code> public interface HttpInterfaces {  
    @GET("weather")  
    Observable<BaseHttpResult<Weather>> getWeather(@Query("cityname") String placeName);  
}</code>
* 接口调用方法：  <code>HttpManager.getInstance().doHttpDeal(activity, RetrofitManager.getApi(HttpInterfaces.class).getWeather(""), simpleOnNextListener);
</code>
* 下载调用方法： <code>HttpDownManager.getInstance().startDown(downInfo);</code>
* 详情请看demo. 
  