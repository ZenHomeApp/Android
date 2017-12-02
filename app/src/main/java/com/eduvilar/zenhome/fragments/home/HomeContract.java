package com.eduvilar.zenhome.fragments.home;

import com.eduvilar.zenhome.model.House;
import com.eduvilar.zenhome.model.User;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public interface HomeContract {

    interface View {
        void init();

        void onViewLoading();

        void onViewReady();

        void refreshList(User user);
    }

    interface Presenter {

        void init();

        void createFlat(House house);

    }
}
