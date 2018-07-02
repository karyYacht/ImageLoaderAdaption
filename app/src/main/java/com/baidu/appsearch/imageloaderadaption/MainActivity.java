package com.baidu.appsearch.imageloaderadaption;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.appsearch.imageloader.GlideImageView;
import com.baidu.appsearch.imageloader.config.SimpleImageloadingListener;
import com.baidu.appsearch.imageloader.loader.ImageLoader;
import com.baidu.appsearch.imageloader.transformation.CircleTransformation;
import com.baidu.appsearch.imageloader.transformation.RadiusTransformation;
import com.bumptech.glide.request.RequestOptions;


/**
 * @author by sunfusheng on 2017/6/3.
 */
public class MainActivity extends Activity {


    GlideImageView glideImageView;

    GlideImageView glideImageView1;
    GlideImageView glideImageView2;
    GlideImageView glideImageView3;
    GlideImageView glideImageView4;

    String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497688355699&di=ea69a930b82ce88561c635089995e124&imgtype=0&src=http%3A%2F%2Fcms-bucket.nosdn.127.net%2Ff84e566bcf654b3698363409fbd676ef20161119091503.jpg";
    String url2 = "http://img1.imgtn.bdimg.com/it/u=4027212837,1228313366&fm=23&gp=0.jpg";
    String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529402445474&di=b5da3b2f6a466e618e1e32d4dd2bda4d&imgtype=0&src=http%3A%2F%2F2b.zol-img.com.cn%2Fproduct%2F133_500x2000%2F801%2Fce21ke76FRh4A.jpg";

    String gif1 = "http://img.zcool.cn/community/01e97857c929630000012e7e3c2acf.gif";
    String gif2 = "http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif";
    String gif3 = "http://img.zcool.cn/community/01d6dd554b93f0000001bf72b4f6ec.jpg";

    public static final String cat = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/cat.jpg";
    public static final String cat_thumbnail = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/cat_thumbnail.jpg";

    public static final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl.jpg";
    public static final String girl_thumbnail = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl_thumbnail.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glideImageView = findViewById(R.id.load_normal);
        ImageLoader.getInstance().displayImage(url1, glideImageView, new SimpleImageloadingListener() {
            @Override
            public void onLoadingFailed() {

            }

            @Override
            public void onLoadingSuccess() {
                Toast.makeText(MainActivity.this, "load success!", Toast.LENGTH_LONG).show();
            }
        });
        glideImageView1 = findViewById(R.id.load_circle);
        RequestOptions requestOptionsCircle = new RequestOptions().optionalTransform(new CircleTransformation());
        glideImageView1.load(url1, requestOptionsCircle);
        glideImageView2 = findViewById(R.id.load_radius);
        RequestOptions requestOptionsRadius = new RequestOptions().optionalTransform(new RadiusTransformation(getApplicationContext(), 10));
        glideImageView2.load(url1, requestOptionsRadius);
        glideImageView3 = findViewById(R.id.load_radius_single);
        RequestOptions requestOptionsRadiusSingle = new RequestOptions().optionalTransform(new RadiusTransformation(getApplicationContext(), 10, RadiusTransformation.CornerType.RIGHT_TOP));
        glideImageView3.load(url1, requestOptionsRadiusSingle);
        glideImageView4 = findViewById(R.id.load_gif);
        glideImageView4.load(gif1);
        }
}
