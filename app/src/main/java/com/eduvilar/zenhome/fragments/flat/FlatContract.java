package com.eduvilar.zenhome.fragments.flat;

/**
 * Created by efraespada on 25/11/2017.
 */

public interface FlatContract {

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
