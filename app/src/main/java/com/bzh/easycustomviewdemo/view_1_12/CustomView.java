package com.bzh.easycustomviewdemo.view_1_12;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.bzh.easycustomviewdemo.utils.Utils;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/11 14:34 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class CustomView extends View implements Runnable {

    private Context mContext;
    private Paint mPaint;
    private float radiu;

    // 直接new
    public CustomView(Context context) {
        super(context);
    }

    // 带系统属性的
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        // 初始化画笔
        initPaint();
    }

    // 带自定义属性的
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Android5.0
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initPaint() {
        // 实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(10); // 描边的粗细
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(Utils.getScreenWidth(mContext) / 2, Utils.getScreenHeight(mContext) / 2, radiu, mPaint);
    }

    public void setRadiu(float radiu) {
        this.radiu = radiu;
    }

    @Override
    public void run() {
        while (true) {
            if (radiu <= 200) {
                radiu += 10;

            } else {
                radiu = 0;
            }
            setRadiu(radiu);
            postInvalidate();// 可以在子线程进行刷新
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
