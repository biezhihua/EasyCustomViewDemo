package com.bzh.easycustomviewdemo.fresco;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;

import com.bzh.easycustomviewdemo.R;
import com.bzh.easycustomviewdemo.drawable.TextDrawable;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　15-8-21 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　    V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class MySimpleDraweeView extends SimpleDraweeView {

    private static final String TAG = "MySimpleDraweeView";
    private Context mContext;

    public MySimpleDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init(context);
    }

    public MySimpleDraweeView(Context context) {
        super(context);
        init(context);
    }

    public MySimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MySimpleDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        postprocessor = new MyBasePostProcessor();
    }

    private Postprocessor postprocessor;

    private ControllerListener controllerListener;

    public ControllerListener getControllerListener() {
        return controllerListener;
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.controllerListener = controllerListener;
    }

    public Postprocessor getPostprocessor() {
        return postprocessor;
    }

    public void setPostprocessor(Postprocessor postprocessor) {
        this.postprocessor = postprocessor;
    }

    public void setHierarchy() {
        if (mContext == null) {
            throw new IllegalArgumentException("Context is not null");
        }
        setHierarchy(getGenericDraweeHierarchy(mContext));
    }

    public void setController(String uri) {
        setController(getDraweeController(getImageRequest(this, uri), this));
    }

    @Override
    public void setHierarchy(GenericDraweeHierarchy hierarchy) {
        super.setHierarchy(hierarchy);
    }

    // ============
    private static GenericDraweeHierarchy defaultGenericDraweeHierarchy = null;
    private static GenericDraweeHierarchy roundGenericDraweeHierarchy = null;

    public static GenericDraweeHierarchy getGenericDraweeHierarchy(Context context) {
        if (defaultGenericDraweeHierarchy == null) {
            defaultGenericDraweeHierarchy = new GenericDraweeHierarchyBuilder(context.getResources())
                    .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE)//fresco:actualImageScaleType="focusCrop"缩放类型
                    .setRetryImage(TextDrawable.getDefaultTextDrawable(context, "点击重新加载!"))//fresco:retryImage="@drawable/retrying"点击重新加载
                    .setFailureImage(TextDrawable.getDefaultTextDrawable(context, "加载失败!"))//fresco:failureImage="@drawable/error"失败图
                    .setProgressBarImage(context.getResources().getDrawable(R.drawable.loading_white))//进度条fresco:progressBarImage="@drawable/progress_bar"进度条
                    .build();
        }
        defaultGenericDraweeHierarchy.setPlaceholderImage(context.getResources().getDrawable(R.color.placeholder));//fresco:placeholderImage="@color/wait_color"占位图
        return defaultGenericDraweeHierarchy;
    }

    public static GenericDraweeHierarchy getRoundGenericDraweeHierarchy(Context context) {
        if (roundGenericDraweeHierarchy == null) {
            roundGenericDraweeHierarchy = new GenericDraweeHierarchyBuilder(context.getResources())
                    .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)//fresco:actualImageScaleType="focusCrop"缩放类型
                    .setPlaceholderImage(context.getResources().getDrawable(R.color.placeholder))//fresco:placeholderImage="@color/wait_color"占位图
                    .setRoundingParams(RoundingParams.asCircle())//圆形/圆角fresco:roundAsCircle="true"圆形
                    .build();
        }
        return roundGenericDraweeHierarchy;
    }

    //图片显示
    public static ImageRequest getImageRequest(MySimpleDraweeView view, String uri) {
        return ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setPostprocessor(view.getPostprocessor())//修改图片
                .setProgressiveRenderingEnabled(true)//渐进加载，主要用于渐进式的JPEG图，影响图片显示速度（普通）
                .build();
    }

    public static DraweeController getDraweeController(ImageRequest imageRequest, MySimpleDraweeView view) {
        return Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)//自动播放图片动画
                .setControllerListener(view.getControllerListener())//监听图片下载完毕等
                .setImageRequest(imageRequest)//设置单个图片请求～～～不可与setFirstAvailableImageRequests共用，配合setLowResImageRequest为高分辨率的图
                .setOldController(view.getController())//DraweeController复用
                .setTapToRetryEnabled(true)//点击重新加载图
                .build();
    }

    class MyBasePostProcessor extends BasePostprocessor {
        public MyBasePostProcessor() {
            super();
        }

        @Override
        public CloseableReference<Bitmap> process(Bitmap sourceBitmap, PlatformBitmapFactory bitmapFactory) {
            if (sourceBitmap.getHeight() > (int) (sourceBitmap.getWidth() * 1.5F)) {
                Bitmap bitmap = decodeRegion(sourceBitmap, sourceBitmap.getWidth(), (int) (sourceBitmap.getWidth() * 1.5F));
                return super.process(bitmap, bitmapFactory);
            } else {
                return super.process(sourceBitmap, bitmapFactory);
            }
        }

        @Override
        public String getName() {
            return super.getName();
        }

        @Override
        public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
            super.process(destBitmap, sourceBitmap);
        }

        @Override
        public CacheKey getPostprocessorCacheKey() {
            return super.getPostprocessorCacheKey();
        }

        @Override
        public void process(Bitmap bitmap) {
            super.process(bitmap);
            final Drawable drawable = mContext.getResources().getDrawable(R.drawable.gif_icon);
            final Bitmap temp = ((BitmapDrawable) drawable).getBitmap();
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(bitmap, bitmap.getWidth() - drawable.getIntrinsicWidth() - 6, bitmap.getHeight() - drawable.getIntrinsicHeight() - 6, new Paint());
        }
    }

    public static Bitmap decodeRegion(byte[] bytes, int width, int height) {
        long startTime = System.currentTimeMillis();
        BitmapRegionDecoder bitmapRegionDecoder = null;
        try {
            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(bytes, 0, bytes.length, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Rect rect = new Rect(0, 0, width, height);
        long endTime = System.currentTimeMillis();
        Bitmap copy = bitmapRegionDecoder.decodeRegion(rect, null).copy(Bitmap.Config.ARGB_8888, true);
        Log.d(TAG, String.format("截取[width=%d,height=%d],耗费：%ds", width, height, (endTime - startTime)));
        return copy;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("bitmap is not null");
        }
        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        long endTime = System.currentTimeMillis();
        Log.d(TAG, String.format("bitmap.width=%d,bitmap.height=%d,bitmap.getcount=%d", bitmap.getWidth(), bitmap.getHeight(), bitmap.getByteCount()));
        Log.d(TAG, "Bitmap转换byte[]，耗费:" + (endTime - startTime) + "s");
        return baos.toByteArray();
    }

    public static Bitmap decodeRegion(Bitmap bitmap, int width, int height) {
        return decodeRegion(bitmap2Bytes(bitmap), width, height);
    }
}
