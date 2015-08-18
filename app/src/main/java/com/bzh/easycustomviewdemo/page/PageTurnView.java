package com.bzh.easycustomviewdemo.page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/18 15:28 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class PageTurnView extends View {

    private static final String TAG = "PageTurnView";

    private List<Bitmap> mBitmaps; // 位图数据列表

    public PageTurnView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public synchronized void setBitmaps(List<Bitmap> mBitmaps) {
        if (null == mBitmaps || 0 == mBitmaps.size()) {
            throw new IllegalArgumentException("no bitmap to display");
        }

        if (mBitmaps.size() < 2) {
            throw new IllegalArgumentException("Fuck you and Fuck to use imageview");
        }

        this.mBitmaps = mBitmaps;
        invalidate();
    }

    private void defaultDisplay(Canvas canvas) {
        // 绘制底色
        canvas.drawColor(Color.WHITE);

        // 绘制标题文本
        mTextPaint.setTextSize(mTextSizeLarger);
        mTextPaint.setColor(Color.RED);
        canvas.drawText("FBI WARNING", mViewWidth / 2, mViewHeight / 4, mTextPaint);

        // 绘制提示文本
        mTextPaint.setTextSize(mTextSizeNormal);
        mTextPaint.setColor(Color.BLACK);
        canvas.drawText("Please set data use setBitmaps method", mViewWidth / 2, mViewHeight / 3, mTextPaint);
    }

    private void initBitmaps() {
        List<Bitmap> temp = new ArrayList<Bitmap>();
        for (int i = 0, j = mBitmaps.size(); i < j; i++) {
            Bitmap bitmap = Bitmap.createScaledBitmap(mBitmaps.get(i), mViewWidth, mViewHeight, true);
            temp.add(bitmap);
        }
        mBitmaps = temp;
    }

    private void drawBitmaps(Canvas canvas) {
        for (int i = 0, j = mBitmaps.size(); i < j; i++) {
            canvas.save();
            canvas.drawBitmap(mBitmaps.get(i), 0, 0, null);
            canvas.restore();
        }
    }

}
