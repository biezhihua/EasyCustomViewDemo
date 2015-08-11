package com.bzh.easycustomviewdemo.view_1_6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.bzh.easycustomviewdemo.R;
import com.bzh.easycustomviewdemo.utils.Utils;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/11 15:21 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class CustomView2 extends View {
    private static final String TAG = "CustomView2";
    private Context mContext;
    private Paint mPaint;
    private Bitmap bitmap;
    private int x;
    private int y;

    public CustomView2(Context context) {
        super(context);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        // 初始化画笔
        initPaint();

        //初始化资源
        initRes(context);
    }

    private void initRes(Context context) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);

        x = Utils.getScreenWidth(mContext) / 2 - bitmap.getWidth() / 2;
        y = Utils.getScreenHeight(mContext) / 2 - bitmap.getHeight() / 2;
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 生成彩色矩阵
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0.5f, 0, 0, 0, 0,
//                0, 0.5f, 0, 0, 0,
//                0, 0, 0.5f, 0, 0,
//                0, 0, 0, 0.5f, 0,
//        });
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0, 0, 0, 1, 0,
//        });
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                -1, 0, 0, 1, 1,
//                0, -1, 0, 1, 1,
//                0, 0, -1, 1, 1,
//                0, 0, 0, 1, 0,
//        });
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0, 0, 1, 0, 0,
//                0, 1, 0, 0, 0,
//                1, 0, 0, 0, 0,
//                0, 0, 0, 1, 0,
//        });
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0.393F, 0.769F, 0.189F, 0, 0,
//                0.349F, 0.686F, 0.168F, 0, 0,
//                0.272F, 0.534F, 0.131F, 0, 0,
//                0, 0, 0, 1, 0,
//        });
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1.438F, -0.122F, -0.016F, 0, -0.03F,
                -0.062F, 1.378F, -0.016F, 0, 0.05F,
                -0.062F, -0.122F, 1.483F, 0, -0.02F,
                0, 0, 0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, mPaint);
    }
}
