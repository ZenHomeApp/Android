package com.eduvilar.zenhome;

import android.app.Application;
import android.content.Context;

import com.efraespada.androidstringobfuscator.AndroidStringObfuscator;
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
    }

    public static Context getContext() {
        return context;
    }

}
