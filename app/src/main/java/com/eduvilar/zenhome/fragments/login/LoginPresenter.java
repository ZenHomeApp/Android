package com.eduvilar.zenhome.fragments.login;

import android.os.Handler;

import com.eduvilar.zenhome.callback.LoginCallback;
import com.eduvilar.zenhome.data.GetData;
import com.eduvilar.zenhome.fragments.flat.FlatContract;
import com.eduvilar.zenhome.model.User;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private GetData getData;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        getData = new GetData();
    }

    @Override
    public void init() {
        view.onViewReady();
    }

    @Override
    public void login(String email, String password) {
        getData.logInUser(email, password, new LoginCallback() {
            @Override
            public void success(User user) {
                view.success(user.getEmail());
            }

            @Override
            public void fail(String error) {
                view.error(error);
            }
        });
    }


}
