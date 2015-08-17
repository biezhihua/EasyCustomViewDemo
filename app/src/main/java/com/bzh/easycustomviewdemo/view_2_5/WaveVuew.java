package com.bzh.easycustomviewdemo.view_2_5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/17 16:33 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class WaveVuew extends View {
    private static final String TAG = "WaveVuew";
    private Path mPath;
    private Paint mPaint;
    private int vWidth, vHeight;
    private float ctrX, ctrY;
    private float waveY;

    private boolean isInc;

    public WaveVuew(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(0XFFA2D6AE);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        vWidth = w;
        vHeight = h;

        waveY = 1 / 8F * vHeight;

        ctrY = -1 / 16F * vHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.moveTo(-1 / 4F * vWidth, waveY);

        mPath.quadTo(ctrX, ctrY, vWidth + 1 / 4f, waveY);

        mPath.lineTo(vWidth + 1 / 4f, vHeight);
        mPath.lineTo(-1 / 4F * vWidth, vHeight);
        mPath.close();

        canvas.drawPath(mPath, mPaint);

        if (ctrX >= vWidth + 0.25f * vWidth) {
            isInc = false;
        } else if (ctrX <= -0.25f * vWidth) {
            isInc = true;
        }

        ctrX = isInc ? ctrX + 20 : ctrX - 20;

        if (ctrY <= vHeight) {
            ctrY += 2;
            waveY += 2;
        }

        mPath.reset();

        invalidate();

    }
}
