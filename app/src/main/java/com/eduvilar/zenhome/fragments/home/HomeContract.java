package com.eduvilar.zenhome.fragments.home;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public interface HomeContract {

    interface View {
        void init();

        void onViewLoading();

        void onViewReady();
    }

    interface Presenter {

        void init();

        void createFlat();

    }
}
