package com.kbryant.retrofit.easyretrofit.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ParseException;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.json.JSONException;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * 用于在Http请求开始时，显示一个ProgressDialog
 * 在Http请求结束时，关闭ProgressDialog
 * Created by WX on 2016/7/16.
 */
public class ApiSubscriber<T> implements Observer<T> {
    /*是否弹框*/
    private boolean showProgress = true;
    /* 软引用回调接口*/
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
    /*软引用防止内存泄露*/
    private SoftReference<RxAppCompatActivity> mActivity;
    /*加载框可自己定义*/
    private ProgressDialog pd;
    /*请求数据*/
    private ApiSetting api;

    private Disposable disposable = null;

    /**
     * 构造
     *
     * @param api
     * @param activity
     * @param listener
     */
    public ApiSubscriber(RxAppCompatActivity activity, ApiSetting api, HttpOnNextListener listener) {
        this.api = api;
        this.mSubscriberOnNextListener = new SoftReference<>(listener);
        this.mActivity = new SoftReference<>(activity);
        init();
    }

    /**
     * 构造
     *
     * @param activity
     * @param listener
     */
    public ApiSubscriber(RxAppCompatActivity activity, HttpOnNextListener listener) {
        this(activity, new ApiSetting(), listener);
    }

    /**
     * 初始化加载框显示
     */
    private void init() {
        setShowProgress(api.isShowProgress());
        if (api.isShowProgress()) {
            initProgressDialog(api.isCancel());
        }
    }

    /**
     * 初始化加载框
     */
    private void initProgressDialog(boolean cancel) {
        Context context = mActivity.get();
        if (pd == null && context != null) {
            pd = new ProgressDialog(context);
            pd.setCancelable(cancel);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            if (cancel) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        if (mSubscriberOnNextListener.get() != null) {
                            mSubscriberOnNextListener.get().onCancel();
                        }
                        onCancelProgress();
                    }
                });
            }
        }
    }


    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!isShowProgress()) return;
        Context context = mActivity.get();
        if (pd == null || context == null) return;
        if (!pd.isShowing()) {
            pd.show();
        }
    }


    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!isShowProgress()) return;
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }


    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
//        Log.i("error", e.getMessage());
        doError(e);
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    /*错误统一处理*/
    private void doError(Throwable e) {
        Context context = mActivity.get();
        if (context == null) return;
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            Toast.makeText(context, "数据异常，解析数据失败", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "错误" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e);
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
        showProgressDialog();
        mSubscriberOnNextListener.get().onStart();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onSuccess(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {

    }


    public boolean isShowProgress() {
        return showProgress;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showProgress
     */
    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }
}