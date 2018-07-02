package com.baidu.appsearch.imageloader.progress;

import com.baidu.appsearch.imageloader.config.ImageLoadingListener;

/**
 * @author by sunfusheng on 2017/6/14.
 */
public interface OnProgressListener extends ImageLoadingListener{
    void onProgress(boolean isComplete, int percentage, long bytesRead, long totalBytes);
}
