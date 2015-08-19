package com.bzh.easycustomviewdemo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/7/27 17:20 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class CustomProgressBarDrawable extends Drawable {

    private ImageDownloadListener mListener;

    public CustomProgressBarDrawable(ImageDownloadListener listener) {
        mListener = listener;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    protected boolean onLevelChange(int level) {
        int progress = (int) ((level / 10000.0) * 100);
        if (mListener != null) {
            mListener.onUpdate(progress);
        }
        return super.onLevelChange(level);
    }

    public interface  ImageDownloadListener {
        void onUpdate(int progress);
    }
}
