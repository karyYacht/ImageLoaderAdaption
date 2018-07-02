package com.baidu.appsearch.imageloader.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.baidu.appsearch.imageloader.config.ImageLoaderConfiguration;
import com.baidu.appsearch.imageloader.config.ImageLoadingListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.lang.ref.WeakReference;

/**
 * GlideLoader
 *
 * @author likai14
 * @since 2018/7/2
 */


public class GlideLoader implements ILoader {
    private static GlideLoader mInstance;
    private GlideLoader(){}
    public static GlideLoader getInstance() {
        if (mInstance == null) {
            synchronized (GlideLoader.class) {
                if (mInstance == null) {
                    mInstance = new GlideLoader();
                }
            }
        }
        return mInstance;
    }

    /**
     * 传入本地资源，同步显示，不支持.9
     *
     * @param resId     资源id
     * @param imageView 依附的imageview
     */
    public void displayImage(int resId, ImageView imageView) {
        displayImage(resId, imageView, null, null);
    }

    public void displayImage(int resId, ImageView imageView, RequestOptions options) {
        displayImage(resId, imageView, options, null);
    }

    public void displayImage(String uri, ImageView imageView) {
        displayImage(uri, imageView, null, null);
    }

    public void displayImage(String uri, ImageView imageView, RequestOptions options) {
        displayImage(uri, imageView, options, null);
    }

    public void displayImage(int resId, ImageView imageView, RequestOptions options,
                             final ImageLoadingListener listener) {
        if (imageView == null) {
            return;
        }
        GlideImageLoader.create(imageView).loadImage(resId, options);
    }

    public void displayImage(String uri, ImageView imageView, RequestOptions options,
                             final ImageLoadingListener listener) {
        if (imageView == null) {
            return;
        }
        GlideImageLoader.create(imageView).loadImage(uri, options);
//        Glide.with(imageView.getContext()).load(uri).apply(options).listener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                listener.onLoadingFailed(e);
//                return true;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                listener.onLoadingComplete();
//                return true;
//            }
//        }).into(imageView);
    }

    @Override
    public void init(Context context, ImageLoaderConfiguration config) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void clearDiskCache() {

    }

    @Override
    public void clearMomoryCache(View view) {

    }

    @Override
    public void clearMomory() {

    }

    @Override
    public boolean isCached(String url) {
        return false;
    }

    @Override
    public void trimMemory(int level) {

    }

    @Override
    public void clearAllMemoryCaches() {

    }
}
