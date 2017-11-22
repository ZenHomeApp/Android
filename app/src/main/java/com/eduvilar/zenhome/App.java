package com.eduvilar.zenhome;

import android.app.Application;
import android.content.Context;

/**
 * Created by efraespada on 22/11/2017.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
