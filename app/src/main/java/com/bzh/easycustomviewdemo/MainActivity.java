package com.bzh.easycustomviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.bzh.easycustomviewdemo.view_1_12.CustomView;


public class MainActivity extends AppCompatActivity {

//    private CustomView mCustomView;
//    private int radiu;

//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mCustomView.setRadiu(radiu);
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mCustomView = (CustomView) findViewById(R.id.main_cv);

//        new Thread(mCustomView).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 界面销毁后清楚Handler的引用
//        mHandler.removeCallbacksAndMessages(null);
    }
}
