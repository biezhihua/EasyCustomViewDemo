package com.bzh.easycustomviewdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.bzh.easycustomviewdemo.page.PageTurnView;
import com.bzh.easycustomviewdemo.view_1_12.CustomView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private PageTurnView mCustomView;

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
        mCustomView = (PageTurnView) findViewById(R.id.main_cv);

        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.a));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.b));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.c));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.d));
        mCustomView.setBitmaps(bitmaps);
//        new Thread(mCustomView).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 界面销毁后清楚Handler的引用
//        mHandler.removeCallbacksAndMessages(null);
    }
}
