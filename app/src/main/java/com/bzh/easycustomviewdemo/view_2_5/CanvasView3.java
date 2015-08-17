package com.bzh.easycustomviewdemo.view_2_5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/17 16:16 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class CanvasView3 extends View {
    private static final String TAG = "CanvasView";
    private Rect mRect;

    public CanvasView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect(0, 0, 500, 500);
        final boolean intersect = mRect.intersect(250, 250, 750, 750);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);
        canvas.clipRect(mRect);
        canvas.drawColor(Color.RED);
    }
}
