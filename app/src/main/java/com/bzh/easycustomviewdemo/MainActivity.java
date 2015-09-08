package com.bzh.easycustomviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bzh.easycustomviewdemo.view_7_12.ImgView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);


//        imgView = (ImageView) findViewById(R.id.image);
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        Request.Builder builder = new Request.Builder();
//        builder.url("http://b.hiphotos.baidu.com/image/pic/item/bd3eb13533fa828ba79b0330f91f4134960a5aac.jpg");
//        Request request = builder.build();
//
//        Call call = okHttpClient.newCall(request);
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//
//            }
//        });
//        FormEncodingBuilder builder1 = new FormEncodingBuilder();
//        builder1.add("username", "张鸿洋");
//        request = new Request.Builder().url("").post(builder1.build()).build();
//        okHttpClient.newCall(request).equals(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//
//            }
//        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        System.out.println(imgView.getMeasuredWidth() + "----" + imgView.getMeasuredHeight());
//    }
}
