package com.eduvilar.zenhome.fragments.flat;

import android.os.Handler;

import com.eduvilar.zenhome.fragments.home.HomeContract;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class FlatPresenter implements FlatContract.Presenter {

    private FlatContract.View view;

    public FlatPresenter(FlatContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.onViewLoading();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                view.onViewReady();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1500);
    }

    @Override
    public void createFlat() {

    }
}
