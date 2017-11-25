package com.eduvilar.zenhome.fragments.home;

import android.os.Handler;

import com.eduvilar.zenhome.App;

/**
 * Created by efraespada on 25/11/2017.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
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
