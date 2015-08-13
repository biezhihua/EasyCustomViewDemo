package com.bzh.easycustomviewdemo.view_1_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
 * <b>创建日期</b>：　2015/8/13 11:19 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class MatrixView extends View {
    private static final String TAG = "MatrixView";

    private static final int RECT_SIZE = 400;

    private Paint mPaint;// 画笔

    private int left, top, right, bottom;
    private int screenX, screenY;


    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);

        screenX = Utils.getScreenWidth(context) / 2;
        screenY = Utils.getScreenHeight(context) / 2;

        // 计算矩形左上右下坐标值
        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.b);

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Matrix matrix = new Matrix();

        matrix.setTranslate(222,222);
        matrix.preRotate(15);

        bitmapShader.setLocalMatrix(matrix);

        mPaint.setShader(bitmapShader);


    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制矩形
        // canvas.drawRect(left, top, right, bottom, mPaint);
        canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
    }
}
