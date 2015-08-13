package com.bzh.easycustomviewdemo.view_1_3;

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
 * <b>创建日期</b>：　2015/8/13 14:54 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class MultiCricleView extends View {
    private static final String TAG = "MultiCricleView";

    private static final float STROKE_WIDTH = 1F / 256F;// 描边宽度占比
    private static final float LINE_LENGTH = 3F / 32F; // 线段长度占比
    private static final float CIRCLE_LARGER_RADIU = 3F / 32F; // 大圆半径
    private static final float CIRCLE_SMALL_RADIU = 5F / 64F; // 小圆半径
    private static final float ARC_RADIU = 1F / 8F; // 弧半径
    private static final float ARC_TEXT_RADIU = 5F / 32F; // 弧围绕文字半径

    private Paint strokePaint; // 描边画笔
    private int size; // 控件边长
    private float strokeWidth; // 描边宽度
    private float ccX, ccY; //  中心园圆心坐标
    private float largeCircleRadiu;
    private float lineLenght;
    private Paint centerCirclePaint;

    public MultiCricleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    private void initPaint(Context context) {
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.WHITE);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);

        centerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        centerCirclePaint.setStyle(Paint.Style.FILL);
        centerCirclePaint.setColor(Color.RED);
        centerCirclePaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        size = w;
        calcalation();
    }

    private void calcalation() {
        strokeWidth = STROKE_WIDTH * size;

        largeCircleRadiu = size * CIRCLE_LARGER_RADIU;

        lineLenght = size * LINE_LENGTH;

        ccX = size / 2;
        ccY = size / 2 + size * CIRCLE_LARGER_RADIU;

        setPara();

    }

    private void setPara() {
        strokePaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFF29B76);
        canvas.drawCircle(ccX, ccY, largeCircleRadiu, strokePaint);
        canvas.drawCircle(ccX, ccY, largeCircleRadiu / 5, centerCirclePaint);
        drawTopLeft(canvas);
    }

    private void drawTopLeft(Canvas canvas) {
        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(-30);

        // largeCircleRadiu = largeCircleRadiu
        canvas.drawLine(0, -largeCircleRadiu, 0, -lineLenght * 2, strokePaint);
        canvas.drawCircle(0, -lineLenght * 2 - largeCircleRadiu, largeCircleRadiu, strokePaint);
        canvas.drawLine(0, -largeCircleRadiu * 3 - lineLenght, 0, -largeCircleRadiu * 3 - lineLenght * 2, strokePaint);
        canvas.drawCircle(0, -largeCircleRadiu * 4 - lineLenght * 2, largeCircleRadiu, strokePaint);

        canvas.restore();
    }
}
