package com.bzh.easycustomviewdemo.view_1_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bzh.easycustomviewdemo.R;
import com.bzh.easycustomviewdemo.utils.Utils;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/13 11:28 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class MatrixImageView extends ImageView {
    private static final String TAG = "MatrixImageView";

    private static final int MODE_NONE = 0x00123;// 默认的触摸模式
    private static final int MODE_DRAAG = 0x00321; // 拖拽模式
    private static final int MODE_ZOOM = 0x00132; // 缩放or旋转模式

    private int mode;// 当前的触摸模式

    private float preMove = 1f;//
    private float saveRotate = 0f; // 保存的角度值
    private float rotate = 0f;// 旋转的角度

    private float[] preEventCoor; // 上一次触摸点的坐标集合

    private PointF start, mid; // 起点，重点对象
    private Matrix currentMatrix, savedMatrix; // 当前和保存的Matrix对象
    private Context mContext;


    public MatrixImageView(Context context) {
        super(context);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
         /*
         * 实例化对象
         */
        currentMatrix = new Matrix();
        savedMatrix = new Matrix();
        start = new PointF();
        mid = new PointF();

        // 模式初始化
        mode = MODE_NONE;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.b);
        bitmap = Bitmap.createScaledBitmap(bitmap, Utils.getScreenWidth(mContext), Utils.getScreenHeight(mContext), true);
        setImageBitmap(bitmap);
    }

    public MatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:// 单点触摸屏幕时
                savedMatrix.set(currentMatrix);
                start.set(event.getX(), event.getY());
                mode = MODE_DRAAG;
                preEventCoor = null;
                break;

            case MotionEvent.ACTION_POINTER_DOWN://第二个点触摸屏幕时
                preMove = calSpacing(event);
                if (preMove > 10f) {
                    savedMatrix.set(currentMatrix);
                    calMidPoaint(mid, event);
                    mode = MODE_ZOOM;
                }
                preEventCoor = new float[4];
                preEventCoor[0] = event.getX(0);
                preEventCoor[1] = event.getX(1);
                preEventCoor[2] = event.getY(0);
                preEventCoor[3] = event.getY(1);
                saveRotate = calRotation(event);
                break;
            case MotionEvent.ACTION_UP:// 单点离开屏幕时
            case MotionEvent.ACTION_POINTER_UP:// 第二个点离开屏幕时
                mode = MODE_NONE;
                preEventCoor = null;
                break;

            case MotionEvent.ACTION_MOVE:
                // 单点触控拖拽平移
                if (mode == MODE_DRAAG) {
                    currentMatrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    currentMatrix.postTranslate(dx, dy);
                } else if (mode == MODE_ZOOM && event.getPointerCount() == 2) {
                    float currentMove = calSpacing(event);
                    currentMatrix.set(savedMatrix);

                    // 指尖移动距离大于10缩放
                    if (currentMove > 10f) {
                        float scale = currentMove / preMove;
                        currentMatrix.postScale(scale, scale, mid.x, mid.y);
                    }
                      /*
                 * 保持两点时旋转
                 */
                    if (preEventCoor != null) {
                        rotate = calRotation(event);
                        float r = rotate - saveRotate;
                        currentMatrix.postRotate(r, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                    }
                }
                break;
        }
        setImageMatrix(currentMatrix);
        return true;
    }

    // 计算两个触摸点间的距离
    private float calSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void calMidPoaint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private float calRotation(MotionEvent event) {
        double deltaX = (event.getX(0) - event.getX(1));
        double deltaY = (event.getY(0) - event.getY(1));
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);
    }
}
