package com.bzh.easycustomviewdemo.view_1_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.bzh.easycustomviewdemo.R;
import com.bzh.easycustomviewdemo.utils.Utils;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/12 17:52 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class ShaderView extends View {
    private static final String TAG = "ShaderView";
    private static final int RECT_SIZE = 400;// 矩形尺寸的一半

    private Paint mPaint;// 画笔

    private int left, top, right, bottom;// 矩形坐上右下坐标
    private int screenX;
    private int screenY;

    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenX = Utils.getScreenWidth(context) / 2;
        screenY = Utils.getScreenHeight(context) / 2;

        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);

        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//        mPaint.setShader(new LinearGradient(left,top,right,bottom, Color.RED,Color.YELLOW, Shader.TileMode.REPEAT));
//        mPaint.setShader(new LinearGradient(left,top,right-RECT_SIZE,bottom-RECT_SIZE, Color.RED,Color.YELLOW, Shader.TileMode.REPEAT));
//        mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE}, new float[]{0, 0.1f, 0.5f, 0.7f, 0.8f}, Shader.TileMode.MIRROR));
//        mPaint.setShader(new SweepGradient(screenX, screenY, Color.RED, Color.YELLOW));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(left, top, right, bottom, mPaint);
        canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
    }

    public ShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
