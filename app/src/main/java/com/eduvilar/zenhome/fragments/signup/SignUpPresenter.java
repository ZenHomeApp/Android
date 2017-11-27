package com.eduvilar.zenhome.fragments.signup;

import com.eduvilar.zenhome.callback.SignUpCallback;
import com.eduvilar.zenhome.data.PostData;
import com.eduvilar.zenhome.model.User;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View view;
    private PostData postData;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        postData = new PostData();
    }

    @Override
    public void init() {
        // nothing to do here
    }

    @Override
    public void signUp(User user) {
        view.signingUp();
        postData.signUpUser(user, new SignUpCallback() {
            @Override
            public void success(String name) {
                view.signedUp(name);
            }

            @Override
            public void fail(String error) {
                view.error(error);
            }
        });
    }

}
