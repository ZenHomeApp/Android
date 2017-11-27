package com.eduvilar.zenhome;

import android.app.Application;
import android.content.Context;

import com.efraespada.androidstringobfuscator.AndroidStringObfuscator;

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
    }

    public static Context getContext() {
        return context;
    }

}
