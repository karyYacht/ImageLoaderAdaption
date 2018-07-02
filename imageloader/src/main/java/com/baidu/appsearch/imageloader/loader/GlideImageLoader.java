package com.baidu.appsearch.imageloader.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.baidu.appsearch.imageloader.config.ImageLoadingListener;
import com.baidu.appsearch.imageloader.config.ListenerManager;
import com.baidu.appsearch.imageloader.config.SimpleImageloadingListener;
import com.baidu.appsearch.imageloader.progress.GlideApp;
import com.baidu.appsearch.imageloader.progress.GlideRequest;
import com.baidu.appsearch.imageloader.progress.OnProgressListener;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.ref.WeakReference;

/**
 * @author by sunfusheng on 2017/6/6.
 */
public class GlideImageLoader {

    protected static final String ANDROID_RESOURCE = "android.resource://";
    protected static final String FILE = "file://";
    protected static final String SEPARATOR = "/";

    private String url;
    private WeakReference<ImageView> imageViewWeakReference;
    private GlideRequest<Drawable> glideRequest;

    public static GlideImageLoader create(ImageView imageView) {
        return new GlideImageLoader(imageView);
    }

    private GlideImageLoader(ImageView imageView) {
        imageViewWeakReference = new WeakReference<>(imageView);
        glideRequest = GlideApp.with(getContext()).asDrawable();
    }

    public ImageView getImageView() {
        if (imageViewWeakReference != null) {
            return imageViewWeakReference.get();
        }
        return null;
    }

    public Context getContext() {
        if (getImageView() != null) {
            return getImageView().getContext();
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public GlideRequest getGlideRequest() {
        if (glideRequest == null) {
            glideRequest = GlideApp.with(getContext()).asDrawable();
        }
        return glideRequest;
    }

    protected Uri resId2Uri(@DrawableRes int resId) {
        return Uri.parse(ANDROID_RESOURCE + getContext().getPackageName() + SEPARATOR + resId);
    }

    public GlideImageLoader load(@DrawableRes int resId, @DrawableRes int placeholder, @NonNull Transformation<Bitmap> transformation) {
        return loadImage(resId2Uri(resId), placeholder, transformation);
    }

    protected GlideRequest<Drawable> loadImage(Object obj) {
        if (obj instanceof String) {
            url = (String) obj;
        }
        return glideRequest.load(obj);
    }

    public GlideImageLoader loadImage(Object obj, RequestOptions options) {
        glideRequest = loadImage(obj);
        if (options != null) {
            glideRequest = glideRequest.apply(options);
        }
        glideRequest.into(new GlideImageViewTarget(getImageView()));
        return this;
    }


    public GlideImageLoader loadImage(Object obj, @DrawableRes int placeholder, Transformation<Bitmap> transformation) {
        glideRequest = loadImage(obj);
        if (placeholder != 0) {
            glideRequest = glideRequest.placeholder(placeholder);
        }
        if (transformation != null) {
            glideRequest = glideRequest.transform(transformation);
        }

        glideRequest.into(new GlideImageViewTarget(getImageView()));
        return this;
    }

    public GlideImageLoader listener(Object obj, ImageLoadingListener listener) {
        if (obj instanceof String) {
            url = (String) obj;
        }
        ListenerManager.addListener(url, listener);
        return this;
    }

    private class GlideImageViewTarget extends DrawableImageViewTarget {
        GlideImageViewTarget(ImageView view) {
            super(view);
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            super.onLoadStarted(placeholder);
        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {
            ImageLoadingListener imageLoadingListener = ListenerManager.getProgressListener(getUrl());
            if (imageLoadingListener instanceof OnProgressListener) {
                OnProgressListener onProgressListener = (OnProgressListener) imageLoadingListener;
                onProgressListener.onProgress(true, 100, 0, 0);
            } else if (imageLoadingListener instanceof SimpleImageloadingListener){
                SimpleImageloadingListener simpleImageloadListener = (SimpleImageloadingListener) imageLoadingListener;
                simpleImageloadListener.onLoadingFailed();
            }
            ListenerManager.removeListener(getUrl());
            super.onLoadFailed(errorDrawable);
        }

        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
            ImageLoadingListener imageLoadingListener = ListenerManager.getProgressListener(getUrl());
            if (imageLoadingListener instanceof OnProgressListener) {
                OnProgressListener onProgressListener = (OnProgressListener) imageLoadingListener;
                onProgressListener.onProgress(true, 100, 0, 0);
            } else if (imageLoadingListener instanceof SimpleImageloadingListener){
                SimpleImageloadingListener simpleImageloadListener = (SimpleImageloadingListener) imageLoadingListener;
                simpleImageloadListener.onLoadingSuccess();
            }
            ListenerManager.removeListener(getUrl());
            super.onResourceReady(resource, transition);
        }
    }
}
