//package com.baidu.appsearch.imageloader.loader;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.View;
//
//import com.baidu.appsearch.imageloader.config.ImageLoaderOptions;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * ImageLoader
// * 统一封装接口
// * @author likai14
// * @since 2018/6/14
// */
//
//public class ImageLoader {
//    private static final ImageLoader INSTANCE=new ImageLoader();
////    private HashMap<LoaderEnum,IImageLoaderstrategy> imageloaderMap=new HashMap<>();
////    private LoaderEnum curLoader = null;
//    private ImageLoader(){
//    }
//    public static ImageLoader getInstance(){
//        return INSTANCE;
//    }
//
//    /*
//     *   可创建默认的Options设置，假如不需要使用ImageView ，
//     *    请自行new一个Imageview传入即可
//     *  内部只需要获取Context
//     */
//    public static ImageLoaderOptions getDefaultOptions(@NonNull View container, @NonNull String url){
//        return new ImageLoaderOptions.Builder(container,url).isCrossFade(true).build();
//    }
//
//    public void showImage(@NonNull ImageLoaderOptions options) {
//        if (getLoaderstrategy(curLoader) != null) {
//            getLoaderstrategy(curLoader).showImage(options);
//        }
//    }
//    public void showImage(@NonNull ImageLoaderOptions options,LoaderEnum loaderEnum) {
//        if (getLoaderstrategy(loaderEnum) != null) {
//            getLoaderstrategy(loaderEnum).showImage(options);
//        }
//    }
//
//    public void hideImage(@NonNull View view, int visiable) {
//        if (getLoaderstrategy(curLoader) != null) {
//            getLoaderstrategy(curLoader).hideImage(view,visiable);
//        }
//    }
//
//
//    public void cleanMemory(Context context) {
//        getLoaderstrategy(curLoader).cleanMemory(context);
//    }
//
//    public void pause(Context context) {
//        if (getLoaderstrategy(curLoader) != null) {
//            getLoaderstrategy(curLoader).pause(context);
//        }
//    }
//
//    public void resume(Context context) {
//        if (getLoaderstrategy(curLoader) != null) {
//            getLoaderstrategy(curLoader).resume(context);
//        }
//    }
//    public void setCurImageLoader(LoaderEnum loader) {
//        curLoader=loader;
//    }
//    // 在application的oncreate中初始化
//    public void init(Context context,ImageLoaderConfig config) {
//        imageloaderMap = config.getImageloaderMap();
//        for (Map.Entry<LoaderEnum, IImageLoaderstrategy> entry : imageloaderMap.entrySet()) {
//            if (entry.getValue() != null) {
//                entry.getValue().init(context,config);
//            }
//
//            if (curLoader == null) {
//                curLoader=entry.getKey();
//            }
//        }
//
//
////        loaderstrategy=new GlideImageLocader();
////        loaderstrategy.init(context);
//    }
//    private IImageLoaderstrategy getLoaderstrategy(LoaderEnum loaderEnum){
//        return imageloaderMap.get(loaderEnum);
//    }
//
//}
