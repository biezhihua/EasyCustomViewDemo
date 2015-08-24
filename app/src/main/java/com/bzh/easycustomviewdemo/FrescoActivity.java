package com.bzh.easycustomviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bzh.easycustomviewdemo.fresco.MySimpleDraweeView;


public class FrescoActivity extends AppCompatActivity {

    private MySimpleDraweeView draweeView;

    private String url = "http://img4.duitang.com/uploads/item/201401/27/20140127163416_iwQhi.thumb.700_0.jpeg";
    private String url2 = "http://img4.duitang.com/uploads/item/201307/26/20130726200033_VYwUY.thumb.700_0.jpeg";
    private String url3 = "http://img6.c.yinyuetai.com/starpicture/starpicture/150819/0/-M-287376fd41270fec8c73273092c51af5_0x0.jpg";
    private String url4 = "http://pic8.nipic.com/20100709/4752803_210430061441_2.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        draweeView = (MySimpleDraweeView) findViewById(R.id.main_cv);
        draweeView.setIsGif();
        draweeView.setHierarchy();
        draweeView.setController(url4);
    }
}
