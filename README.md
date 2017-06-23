# EasyRetrofit
Rxjava2+Retrofit2网路请求封装库，包括多任务下载。

[![](https://jitpack.io/v/kobebryantwx/EasyRetrofit.svg)](https://jitpack.io/#kobebryantwx/EasyRetrofit)
## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    compile 'com.github.kobebryantwx:EasyRetrofit:1.0'
}
```
## Usage
* 自定义Application继承RetrofitApplication(必须);

* 在自定义Application的oncreate()中，使用HttpManager.getApiSetting()配置baseUrl等；

* Activity继承RxAppCompatActivity(必须)；

* 创建interface类，例如：  
```java 
public interface HttpInterfaces {
    @GET("weather")
    Observable<BaseHttpResult<Weather>> getWeather(@Query("cityname") String placeName);
}
```

* 接口调用方法：  
```java 
HttpManager.getInstance().doHttpDeal(activity, RetrofitManager.getApi(HttpInterfaces.class).getWeather(""), simpleOnNextListener)；
```

* 下载调用方法： 
```java 
HttpDownManager.getInstance().startDown(downInfo);
```

* 详情请看demo. 
  