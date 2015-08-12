package com.bzh.easycustomviewdemo.view_1_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bzh.easycustomviewdemo.R;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/12 18:01 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class BrickView extends View {
    private static final String TAG = "BrickView";
    private Paint mFillPaint, mStrokePaint;// 填充和描边的画笔
    private BitmapShader mBitmapShader;// Bitmap着色器

    private float posX, posY;// 触摸点的XY坐标

    public BrickView(Context context) {
        super(context);
    }

    public BrickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        // 实例化描边画笔
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrokePaint.setColor(0xFF000000);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(5);

        mFillPaint = new Paint();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mFillPaint.setShader(mBitmapShader);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
         * 手指移动时获取触摸点坐标并刷新视图
         */
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            posX = event.getX();
            posY = event.getY();

            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.DKGRAY);

        canvas.drawCircle(posX, posY, 300, mFillPaint);
        canvas.drawCircle(posX, posY, 300, mStrokePaint);
    }

    public BrickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
