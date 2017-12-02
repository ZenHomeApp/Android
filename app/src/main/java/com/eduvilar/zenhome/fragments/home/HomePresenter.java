package com.eduvilar.zenhome.fragments.home;

import android.os.Handler;

import com.eduvilar.zenhome.App;
import com.eduvilar.zenhome.callback.UpdateUserCallback;
import com.eduvilar.zenhome.data.GetData;
import com.eduvilar.zenhome.data.PostData;
import com.eduvilar.zenhome.model.House;
import com.eduvilar.zenhome.model.User;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private PostData postData;
    private GetData getData;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        postData = new PostData();
        getData = new GetData();
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
    public void createFlat(final House house) {
        postData.saveUser(new UpdateUserCallback() {
            @Override
            public User onUser(User user) {
                user.addHouse(house);
                return user;
            }

            @Override
            public void userUpdated(User user) {
                view.refreshList(user);
            }

            @Override
            public void fail(String error) {
                // TODO alert update user error
            }
        });
    }
}
