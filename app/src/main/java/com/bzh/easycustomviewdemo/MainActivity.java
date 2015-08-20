package com.bzh.easycustomviewdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bzh.easycustomviewdemo.page.PageTurnView;
import com.bzh.easycustomviewdemo.utils.Utils;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {
    private PageTurnView mCustomView;
    private SimpleDraweeView draweeView;
    private ImageView imageView;
    private CloseableReference<CloseableImage> reference;
    private CloseableReference<CloseableImage> imageReference;
    private DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;

//    private CustomView mCustomView;
//    private int radiu;

//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mCustomView.setRadiu(radiu);
//        }
//    };

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mCustomView = (PageTurnView) findViewById(R.id.main_cv);
//
//        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
//        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.a));
//        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.b));
//        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.c));
//        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.d));
//        mCustomView.setBitmaps(bitmaps);
////        new Thread(mCustomView).start();
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        draweeView = (SimpleDraweeView) findViewById(R.id.main_cv);
//        draweeView.setImageUri("http://img5.c.yinyuetai.com/starpicture/starpicture/150818/0/-M-d2f3f470d41069b46e9e53c2a681a314_0x0.jpg");
//        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
//            @Override
//            public String getName() {
//                return "redMeshPostprocessor";
//            }
//
//            @Override
//            public void process(Bitmap bitmap) {
//                float outHeight = bitmap.getWidth() * 1.0f * (Utils.getScreenHeight(MainActivity.this) * 1.0f / bitmap.getWidth());
//                Canvas canvas = new Canvas(bitmap);
//                Bitmap tmpbitmap = decodeRegion(Bitmap2Bytes(bitmap), bitmap.getWidth(), Math.round(outHeight));
//                canvas.drawBitmap(tmpbitmap, 0, 0, new Paint());
//            }
//        };
//        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
//                .build();
//        mDraweeHolder = DraweeHolder.create(hierarchy, this);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse("http://img5.c.yinyuetai.com/starpicture/starpicture/150818/0/-M-d2f3f470d41069b46e9e53c2a681a314_0x0.jpg"))
//                .setPostprocessor(redMeshPostprocessor)
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
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
                                        int maxHeight = (int) (Utils.getScreenWidth(MainActivity.this) * 1.5F);
                                        if (origW <= 440 && origH > maxHeight) {
                                            float outHeight = origW * 1.0f * (maxHeight * 1.0f / Utils.getScreenWidth(MainActivity.this));
                                            Bitmap tmpbitmap = decodeRegion(Bitmap2Bytes(bitmap), origW, Math.round(outHeight));
                                            draweeView.setImageBitmap(tmpbitmap);
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
        draweeView.setController(controller);
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
//            return bitmapDecoder.decodeRegion(rect, null);
            return bitmapDecoder.decodeRegion(rect, null).copy(Bitmap.Config.ARGB_8888, true);
        } catch (Exception e) {
        }
        return null;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    private static Bitmap captureWebView(WebView webView) {
        Picture snapShot = webView.capturePicture();
        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),
                snapShot.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        return bmp;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_picasso);
//        imageView = (ImageView) findViewById(R.id.main_cv);
//        Picasso.with(this).load(R.drawable.biezhihua).into(imageView);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 界面销毁后清楚Handler的引用
//        mHandler.removeCallbacksAndMessages(null);
    }
}
