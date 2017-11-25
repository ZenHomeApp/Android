package com.eduvilar.zenhome.fragments.login;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public interface LoginContract {

    interface View {

        void init();

        void success(String name);

        void error(String error);

        void onViewLoading();

        void onViewReady();
    }

    interface Presenter {

        void init();

        void login(String email, String password);

    }
}
