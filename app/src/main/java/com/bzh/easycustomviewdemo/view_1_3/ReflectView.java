package com.bzh.easycustomviewdemo.view_1_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.bzh.easycustomviewdemo.R;
import com.bzh.easycustomviewdemo.utils.Utils;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/13 09:56 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class ReflectView extends View {
    private static final String TAG = "ReflectView";
    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode; // 混合模式
    private int x;
    private int y;

    public ReflectView(Context context) {
        super(context);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRes(context);
    }

    private void initRes(Context context) {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.b);

        // 实例化一个矩阵
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);

        // 生成倒影图
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

        x = Utils.getScreenWidth(context) / 2 - mSrcBitmap.getWidth() / 2;
        y = Utils.getScreenHeight(context) / 2 - mSrcBitmap.getHeight() / 2;

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(x, y + mSrcBitmap.getHeight(), x, y + mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 3, 0xAA000000, Color.TRANSPARENT, Shader.TileMode.CLAMP));

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, x, y, null);
        int sc = canvas.saveLayer(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mRefBitmap, x, y + mSrcBitmap.getHeight(), null);
        mPaint.setXfermode(mXfermode);
        canvas.drawRect(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
