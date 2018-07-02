package com.baidu.appsearch.imageloader.config;

/**
 * SimpleImageloadingListener
 *
 * @author likai14
 * @since 2018/7/2
 */


public interface SimpleImageloadingListener extends ImageLoadingListener{

    void onLoadingFailed();

    void onLoadingSuccess();
}
