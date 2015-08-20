package com.bzh.easycustomviewdemo.view_1_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.bzh.easycustomviewdemo.R;
import com.bzh.easycustomviewdemo.utils.Utils;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/13 10:14 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class DreamEffectView2 extends View {
    private static final String TAG = "DreamEffectView";

    private Paint mBitmapPaint; // 位图画笔
    private Bitmap mBitmap;
    private PorterDuffXfermode mXfermode; // 图形混合模式
    private int x, y;// 位图期待你坐标
    private int screenW, screenH;
    private Paint mShaderPaint;
    private Bitmap darkCornerBitmap;

    public DreamEffectView2(Context context) {
        super(context);
    }

    public DreamEffectView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRes(context);
        initPaint();
    }


    public DreamEffectView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBitmapPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{0.8587F, 0.2940F, -0.0927F, 0, 6.79F, 0.0821F, 0.9145F, 0.0634F, 0, 6.79F, 0.2019F, 0.1097F, 0.7483F, 0, 6.79F, 0, 0, 0, 1, 0}));

        mShaderPaint = new Paint();

        darkCornerBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(darkCornerBitmap);

        float radiu = canvas.getHeight() * (2f / 3f);

        RadialGradient radialGradient = new RadialGradient(canvas.getWidth() / 2f, canvas.getHeight() / 2f, radiu, new int[]{0, 0, 0xAA000000}, new float[]{0F, 0.7F, 1.0F}, Shader.TileMode.CLAMP);

        Matrix matrix = new Matrix();

        matrix.setScale(canvas.getWidth() / (radiu * 2F), 1.0f);

        matrix.preTranslate(((radiu * 2f) - canvas.getWidth()) / 2f, 0);

        radialGradient.setLocalMatrix(matrix);

        mShaderPaint.setShader(radialGradient);
    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.b);

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        screenH = Utils.getScreenHeight(context);
        screenW = Utils.getScreenWidth(context);

        x = screenW / 2 - mBitmap.getWidth() / 2;
        y = screenH / 2 - mBitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 新建图层
        int sc = canvas.saveLayer(x, y, x + mBitmap.getWidth(), y + mBitmap.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        // 绘制混合颜色
        canvas.drawColor(0xCC1C093E);

        mBitmapPaint.setXfermode(mXfermode);

        canvas.drawBitmap(mBitmap, x, y, mBitmapPaint);

        mBitmapPaint.setXfermode(null);

        canvas.restoreToCount(sc);

        canvas.drawBitmap(darkCornerBitmap, x, y, null);
    }
}
