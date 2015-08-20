package com.bzh.easycustomviewdemo.view_1_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.bzh.easycustomviewdemo.R;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/18 14:26 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class LayerView2 extends View {
    private static final String TAG = "LayerView2";
    private Bitmap mBitmap;
    private int mViewWidth;
    private int mViewHeight;

    public LayerView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        mBitmap = Bitmap.createScaledBitmap(mBitmap, mViewWidth, mViewHeight, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(0.8F, 0.35F,mViewWidth,0);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.restore();
    }
}
