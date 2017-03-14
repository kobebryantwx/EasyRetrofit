package com.kbryant.retrofit.rxjavaandretrofitdemo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kbryant.retrofit.rxjavaandretrofitdemo.R;
import com.kbryant.retrofit.rxjavaandretrofitdemo.download.HttpDownManager;
import com.kbryant.retrofit.rxjavaandretrofitdemo.download.HttpDownOnNextListener;
import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.DownInfo;
import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.DownState;
import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.StudyPlace;
import com.kbryant.retrofit.rxjavaandretrofitdemo.http.HttpOnNextListener;
import com.kbryant.retrofit.rxjavaandretrofitdemo.http.NetManager;
import com.kbryant.retrofit.rxjavaandretrofitdemo.utils.DbDownUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.util.List;

public class MainActivity extends RxAppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private HttpDownManager manager;
    private DbDownUtil dbUtil;
    private List<DownInfo> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_show_data);
        editText = (EditText) findViewById(R.id.et_place_name);
        button = (Button) findViewById(R.id.btn_sure);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(editText.getText().toString());
                download();
            }
        });
        manager = HttpDownManager.getInstance();
        dbUtil = DbDownUtil.getInstance();
        initData();
    }

    public void getData(String str) {
        NetManager.getAllVideo(this, str, httpOnNextListener);
    }

    public void download() {
        listData.get(0).setListener(downOnNextListener);
        manager.startDown(listData.get(0));
    }

    public void initData() {
        listData = dbUtil.queryDownAll();
        if (listData.isEmpty()) {
            String url = "http://www.izaodao.com/app/izaodao_app.apk";
            File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "test" + 1 + ".apk");
            DownInfo apkApi = new DownInfo(url);
            apkApi.setId(1);
            apkApi.setState(DownState.START);
            apkApi.setSavePath(outputFile.getAbsolutePath());
            dbUtil.save(apkApi);
        }
    }

    HttpDownOnNextListener<DownInfo> downOnNextListener = new HttpDownOnNextListener<DownInfo>() {

        @Override
        public void onNext(DownInfo downInfo) {

        }

        @Override
        public void onStart() {

        }

        @Override
        public void onComplete() {
            Log.i("log", "onComplete");
        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            Log.i("log", readLength + "***" + countLength);
        }
    };
    HttpOnNextListener<List<StudyPlace>> httpOnNextListener = new HttpOnNextListener<List<StudyPlace>>() {

        @Override
        public void onSuccess(List<StudyPlace> studyPlaceList) {
            textView.setText(studyPlaceList.get(0).getAreaName());
        }

    };
}
