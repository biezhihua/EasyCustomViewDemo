package com.bzh.easycustomviewdemo.view_1_6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bzh.easycustomviewdemo.R;
import com.bzh.easycustomviewdemo.utils.Utils;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/11 15:51 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class EraserView extends View {
    private static final String TAG = "EraserView";

    private static final int MIN_MOVE_DIS = 5;

    private Bitmap fgBitmap, bgBitmap;

    private Canvas mCanvas;

    private Paint mPaint;

    private Path mPath;


    public EraserView(Context context) {
        super(context);
    }

    public EraserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cal(context);
        init(context);
    }


    public EraserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int screenW, screenH;// 屏幕宽高
    private float preX, preY;// 记录上一个触摸事件的位置坐标

    private void cal(Context context) {
        screenW = Utils.getScreenWidth(context);
        screenH = Utils.getScreenHeight(context);
    }

    private void init(Context context) {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setARGB(128, 255, 0, 0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(50);
        fgBitmap = Bitmap.createBitmap(screenW, screenH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(fgBitmap);
        mCanvas.drawColor(0XFF808080);
        bgBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a);
        bgBitmap = Bitmap.createScaledBitmap(bgBitmap, screenW, screenH, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgBitmap, 0, 0, null);
        canvas.drawBitmap(fgBitmap, 0, 0, null);

        mCanvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
         * 获取当前事件位置坐标
         */
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:// 手指接触屏幕重置路径
                mPath.reset();
                mPath.moveTo(x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:// 手指移动时连接路径
                float dx = Math.abs(x - preX);
                float dy = Math.abs(y - preY);
                if (dx >= MIN_MOVE_DIS || dy >= MIN_MOVE_DIS) {
                    mPath.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
                    preX = x;
                    preY = y;
                }
                break;
        }

        // 重绘视图
        invalidate();
        return true;
    }
}
