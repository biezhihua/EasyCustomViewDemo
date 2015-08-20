package com.bzh.easycustomviewdemo.view_1_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/18 13:54 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class LayerView extends View {
    private static final String TAG = "LayerView";
    private final Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);
//
////        canvas.save();
//        final int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
//        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200);
//        canvas.drawColor(Color.GREEN);
//
//        final int saveID2 = canvas.save(Canvas.MATRIX_SAVE_FLAG);
//        canvas.rotate(5);
//        canvas.drawColor(Color.BLUE);
//        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
//        canvas.restoreToCount(saveID2);
//
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(mViewWidth / 2F - 400, mViewHeight / 2F - 400, mViewWidth / 2F + 400, mViewHeight / 2F + 400, mPaint);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 300, mViewHeight / 2F - 300, mViewWidth / 2F + 300, mViewHeight / 2F + 300);
        canvas.drawColor(Color.YELLOW);
        canvas.restore();
    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID2 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);
        canvas.restore();

    /*
     * 保存画布并旋转后绘制一个蓝色的矩形
     */
        int saveID3 = canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate(5);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
        canvas.restore();
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(mViewWidth / 2F, mViewHeight / 2F, mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);
    }
}
