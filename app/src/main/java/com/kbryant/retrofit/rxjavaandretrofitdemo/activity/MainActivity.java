package com.kbryant.retrofit.rxjavaandretrofitdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kbryant.retrofit.rxjavaandretrofitdemo.R;
import com.kbryant.retrofit.rxjavaandretrofitdemo.download.HttpDownManager;
import com.kbryant.retrofit.rxjavaandretrofitdemo.download.HttpDownOnNextListener;
import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.DownInfo;
import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.Weather;
import com.kbryant.retrofit.rxjavaandretrofitdemo.http.HttpOnNextListener;
import com.kbryant.retrofit.rxjavaandretrofitdemo.http.NetManager;
import com.kbryant.retrofit.rxjavaandretrofitdemo.utils.DbDownUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

public class MainActivity extends RxAppCompatActivity {

    private TextView textView;
    private Button button;
    private HttpDownManager manager;
    private DbDownUtil dbUtil;
    private List<DownInfo> listData;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_show_data);
        button = (Button) findViewById(R.id.btn_sure);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData();
                download();
            }
        });
        manager = HttpDownManager.getInstance();
        dbUtil = DbDownUtil.getInstance();
        initData();

    }

    public void getData() {
        NetManager.getWeather(this, "", httpOnNextListener);
    }

    public void download() {
        manager.startDown(listData.get(0));
    }

    public void initData() {
        listData = dbUtil.queryDownAll();
        DownInfo downInfo;
        if (listData.isEmpty()) {
            String url = "http://www.izaodao.com/app/izaodao_app.apk";
            downInfo = new DownInfo(url, "test.apk", downOnNextListener);
            listData.add(downInfo);
            dbUtil.save(downInfo);
        } else {
            downInfo = listData.get(0);
            downInfo.setListener(downOnNextListener);
        }
        int progress = (int) (downInfo.getReadLength() * 100 / downInfo.getCountLength());
        setText(progress);
    }

    public void setText(int progress) {
        progressBar.setProgress(progress);
        if (progress == 0) {
            button.setText("下载");
        } else if (progress == 100) {
            button.setText("重新下载");
        } else {
            button.setText("已下载" + progress + "%");
        }
    }

    HttpDownOnNextListener<DownInfo> downOnNextListener = new HttpDownOnNextListener<DownInfo>() {

        @Override
        public void onNext(DownInfo downInfo) {

        }

        @Override
        public void onStart() {
            Log.i("log", "onStart");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.i("log", e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.i("log", "onComplete");
            Toast.makeText(MainActivity.this, "下载完成！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            Log.i("log", readLength + "***" + countLength);
            int progress = (int) (readLength * 100 / countLength);
            setText(progress);
        }
    };

    HttpOnNextListener<Weather> httpOnNextListener = new HttpOnNextListener<Weather>() {

        @Override
        public void onSuccess(Weather weather) {
            textView.setText(weather.getWeather());
        }

    };
}
