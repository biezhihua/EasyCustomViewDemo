package com.bzh.easycustomviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bzh.easycustomviewdemo.utils.Utils;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imageutils.BitmapUtil;

import java.io.ByteArrayOutputStream;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/19 13:15 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class PhotoView extends ImageView {
    DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;
    private CloseableReference<CloseableImage> imageReference = null;

    public PhotoView(Context context) {
        super(context);
        init();
    }

    public PhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        if (mDraweeHolder == null) {
            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                    .setProgressBarImage(new CustomProgressBarDrawable(new CustomProgressBarDrawable.ImageDownloadListener() {
                        @Override
                        public void onUpdate(int progress) {
                            System.out.println("Progress:" + progress);
                        }
                    }))
                    .build();
            mDraweeHolder = DraweeHolder.create(hierarchy, getContext());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        init();
        mDraweeHolder.onAttach();
        super.onAttachedToWindow();
    }

    @Override
    protected boolean verifyDrawable(Drawable dr) {
        super.verifyDrawable(dr);
        if (dr == mDraweeHolder.getHierarchy().getTopLevelDrawable()) {
            return true;
        }
        return false;
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        mDraweeHolder.onDetach();
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        mDraweeHolder.onAttach();
    }

    public void setImageUri(String url) {
        final long startTime = System.currentTimeMillis();
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url)).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mDraweeHolder.getController())
                .setImageRequest(imageRequest)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String s, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                        try {
                            imageReference = dataSource.getResult();
                            if (imageReference != null) {
                                CloseableImage image = imageReference.get();
                                // do something with the image
                                if (image != null && image instanceof CloseableStaticBitmap) {
                                    CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) image;
                                    Bitmap bitmap = closeableStaticBitmap.getUnderlyingBitmap();
                                    if (bitmap != null) {
                                        int origW = bitmap.getWidth();
                                        int origH = bitmap.getHeight();
                                        int maxHeight = (int) (Utils.getScreenWidth(getContext()) * 1.5F);
                                        if (origW <= 440 && origH > maxHeight) {
                                            float outHeight = origW * 1.0f * (maxHeight * 1.0f / Utils.getScreenWidth(getContext()));
                                            Bitmap tmpbitmap = decodeRegion(Bitmap2Bytes(bitmap), origW, Math.round(outHeight));
                                            setImageBitmap(tmpbitmap);
                                        }
                                    }
                                }
                            }
                        } finally {
                            dataSource.close();
                            CloseableReference.closeSafely(imageReference);
                        }
                        long endTime = System.currentTimeMillis();
                    }
                })
                .setTapToRetryEnabled(true)
                .build();
        mDraweeHolder.setController(controller);
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap decodeRegion(byte[] bytes, int width, int height) {
        try {
            BitmapRegionDecoder bitmapDecoder = BitmapRegionDecoder.newInstance(bytes, 0, bytes.length, true);
            Rect rect = new Rect(0, 0, width, height);
            return bitmapDecoder.decodeRegion(rect, null).copy(Bitmap.Config.ARGB_8888, true);
        } catch (Exception e) {
        }
        return null;
    }
}