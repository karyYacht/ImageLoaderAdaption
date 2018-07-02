package com.baidu.appsearch.imageloader.progress;

import com.baidu.appsearch.imageloader.config.ImageLoadingListener;
import com.baidu.appsearch.imageloader.config.ListenerManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author by sunfusheng on 2017/6/14.
 */
public class ProgressManager {
    private static OkHttpClient okHttpClient;
    private ProgressManager() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(chain -> {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                                .body(new ProgressResponseBody(request.url().toString(), LISTENER, response.body()))
                                .build();
                    })
                    .build();
        }
        return okHttpClient;
    }

    private static final ProgressResponseBody.InternalProgressListener LISTENER = (url, bytesRead, totalBytes) -> {
        ImageLoadingListener onProgressListener = ListenerManager.getProgressListener(url);
        if (onProgressListener != null
                && onProgressListener instanceof OnProgressListener) {
            if (onProgressListener != null) {
                int percentage = (int) ((bytesRead * 1f / totalBytes) * 100f);
                boolean isComplete = percentage >= 100;
                ((OnProgressListener) onProgressListener).onProgress(isComplete, percentage, bytesRead, totalBytes);
                if (isComplete) {
                    ListenerManager.removeListener(url);
                }
            }
        }
    };
}
