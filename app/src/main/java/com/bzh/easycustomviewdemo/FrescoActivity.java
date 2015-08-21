package com.bzh.easycustomviewdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bzh.easycustomviewdemo.drawable.TextDrawableFactory;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class FrescoActivity extends AppCompatActivity {

    private SimpleDraweeView draweeView;

    private String url = "http://img4.duitang.com/uploads/item/201401/27/20140127163416_iwQhi.thumb.700_0.jpeg";
    private String url2 = "http://img4.duitang.com/uploads/item/201307/26/20130726200033_VYwUY.thumb.700_0.jpeg";
    private String url3 = "http://img6.c.yinyuetai.com/starpicture/starpicture/150819/0/-M-287376fd41270fec8c73273092c51af5_0x0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        draweeView = (SimpleDraweeView) findViewById(R.id.main_cv);


        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setFailureImage(TextDrawableFactory.getDefaultTextDrawable(this))
                .setProgressBarImage(getResources().getDrawable(R.drawable.loading_white))
                .build();

        draweeView.setHierarchy(hierarchy);
        hierarchy = draweeView.getHierarchy();
        hierarchy.setPlaceholderImage(getResources().getDrawable(R.color.placeholder));
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url2))
                .setPostprocessor(new BasePostprocessor() {
                    @Override
                    public String getName() {
                        return super.getName();
                    }

                    @Override
                    public CloseableReference<Bitmap> process(Bitmap sourceBitmap, PlatformBitmapFactory bitmapFactory) {
                        Bitmap bitmap = decodeRegion(Bitmap2Bytes(sourceBitmap), sourceBitmap.getWidth(), (int) (sourceBitmap.getWidth() * 1.5F));
                        return super.process(bitmap, bitmapFactory);
                    }

                    @Override
                    public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
                        super.process(destBitmap, sourceBitmap);
                        // sourceBimap 源
                        // destBitmap 目标，会传入process(Bitmap)
                    }

                    @Override
                    public void process(Bitmap bitmap) {
                        super.process(bitmap);
                    }

                    @Override
                    public CacheKey getPostprocessorCacheKey() {
                        return super.getPostprocessorCacheKey();
                    }
                })
                .setProgressiveRenderingEnabled(true)
                .build();

        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onSubmit(String id, Object callerContext) {
                        super.onSubmit(id, callerContext);
                    }

                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                        super.onIntermediateImageSet(id, imageInfo);
                    }

                    @Override
                    public void onIntermediateImageFailed(String id, Throwable throwable) {
                        super.onIntermediateImageFailed(id, throwable);
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        super.onFailure(id, throwable);
                    }

                    @Override
                    public void onRelease(String id) {
                        super.onRelease(id);
                    }
                })
                .setOldController(draweeView.getController())
                .setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .build();
        draweeView.setController(controller);
    }


    public static byte[] Bitmap2Bytes(Bitmap bm) {
        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("bitmap.widht=%d,bitmap.height=%d,bitmap.getcount=%d", bm.getWidth(), bm.getHeight(), bm.getByteCount()));
        System.out.println("Bitmap转换byte[]，耗费:" + (endTime - startTime) + "s");
        return baos.toByteArray();
    }

    public static Bitmap decodeRegion(byte[] bytes, int width, int height) {
        long startTime = System.currentTimeMillis();
        BitmapRegionDecoder bitmapDecoder = null;
        try {
            bitmapDecoder = BitmapRegionDecoder.newInstance(bytes, 0, bytes.length, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Rect rect = new Rect(0, 0, width, height);
        long endTime = System.currentTimeMillis();
        Bitmap copy = bitmapDecoder.decodeRegion(rect, null).copy(Bitmap.Config.ARGB_8888, true);
        System.out.println(String.format("截取[width=%d,height=%d],耗费：%ds", width, height, (endTime - startTime)));
        return copy;
    }
}
