package com.eduvilar.zenhome;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.eduvilar.zenhome.utils.ImageUtils;
import com.efraespada.androidstringobfuscator.AndroidStringObfuscator;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by eduardovilar10 on 22/11/2017.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidStringObfuscator.init(this);
        context = getApplicationContext();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageUtils.load(uri.toString(), imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                //
            }

        });
    }

    public static Context getContext() {
        return context;
    }

}
