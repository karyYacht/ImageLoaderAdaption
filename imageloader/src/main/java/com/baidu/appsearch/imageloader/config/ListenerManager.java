package com.baidu.appsearch.imageloader.config;

import android.text.TextUtils;

import com.baidu.appsearch.imageloader.progress.OnProgressListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ListenerManager
 *
 * @author likai14
 * @since 2018/7/2
 */


public class ListenerManager {

    private static Map<String, ImageLoadingListener> listenersMap = Collections.synchronizedMap(new HashMap<>());

    private ListenerManager() {
    }

    public static void addListener(String url, ImageLoadingListener listener) {
        if (!TextUtils.isEmpty(url) && listener != null) {
            listenersMap.put(url, listener);
            if (listener instanceof OnProgressListener) {
                ((OnProgressListener) listener).onProgress(false, 1, 0, 0);
            }
        }
    }

    public static void removeListener(String url) {
        if (!TextUtils.isEmpty(url)) {
            listenersMap.remove(url);
        }
    }

    public static ImageLoadingListener getProgressListener(String url) {
        if (TextUtils.isEmpty(url) || listenersMap == null || listenersMap.size() == 0) {
            return null;
        }
        ImageLoadingListener listenerWeakReference = listenersMap.get(url);
        if (listenerWeakReference != null) {
            return listenerWeakReference;
        }
        return null;
    }
}
