package com.eduvilar.zenhome.fragments.signup;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public interface SignUpContract {

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
