package com.baidu.appsearch.imageloader.loader;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.baidu.appsearch.imageloader.config.ImageLoaderConfiguration;
import com.baidu.appsearch.imageloader.config.ImageLoadingListener;
import com.baidu.appsearch.imageloader.progress.GlideApp;
import com.bumptech.glide.request.RequestOptions;

/**
 * ImageLoader
 *
 * @author likai14
 * @since 2018/7/2
 */


public class ImageLoader implements ILoader {
    private static ImageLoader mInstance;
    private Context mContext;
    private ImageLoader(){}
    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    /**
     * 传入本地资源，同步显示，不支持.9
     *
     * @param uri     资源地址
     * @param imageView 依附的imageview
     */
    public void displayImage(Object uri, ImageView imageView) {
        displayImage(uri, imageView, null, null);
    }

    public void displayImage(Object uri, ImageView imageView, ImageLoadingListener listener) {
        displayImage(uri, imageView, null, listener);
    }

    public void displayImage(Object uri, ImageView imageView, RequestOptions options) {
        displayImage(uri, imageView, options, null);
    }

    public void displayImage(Object uri, ImageView imageView, RequestOptions options,
                             final ImageLoadingListener listener) {
        if (imageView == null) {
            return;
        }
        GlideImageLoader.create(imageView).listener(uri, listener).loadImage(uri, options);
    }

    @Override
    public void init(Context context, ImageLoaderConfiguration config) {
        mContext = context;
    }

    @Override
    public void pause() {
        GlideApp.with(mContext).pauseRequests();
    }

    @Override
    public void resume() {
        GlideApp.with(mContext).resumeRequests();
    }

    @Override
    public void clearDiskCache() {
        GlideApp.get(mContext).clearDiskCache();
    }

    @Override
    public void clearMomoryCache(View view) {
        GlideApp.with(mContext).clear(view);
    }

    @Override
    public void clearMomory() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                GlideApp.get(mContext).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCached(String url) {
        return false;
    }

    @Override
    public void trimMemory(int level) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                GlideApp.get(mContext).onTrimMemory(level);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearAllMemoryCaches() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                GlideApp.get(mContext).onLowMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
