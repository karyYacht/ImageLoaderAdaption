package com.baidu.appsearch.imageloader.loader;

import android.content.Context;
import android.view.View;

import com.baidu.appsearch.imageloader.config.ImageLoaderConfiguration;
import com.bumptech.glide.MemoryCategory;

/**
 * ILoader
 * 基础接口
 * @author likai14
 * @since 2018/7/2
 */


public interface ILoader {

    void init(Context context, ImageLoaderConfiguration config);

    void pause();

    void resume();

    void clearDiskCache();

    void clearMomoryCache(View view);

    void clearMomory();

    boolean  isCached(String url);

    void trimMemory(int level);

    void clearAllMemoryCaches();
}
