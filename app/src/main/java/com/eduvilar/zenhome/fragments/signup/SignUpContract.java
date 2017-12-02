package com.eduvilar.zenhome.fragments.signup;

import com.eduvilar.zenhome.callback.SignUpCallback;
import com.eduvilar.zenhome.model.User;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public interface SignUpContract {

    interface View {

        void init();

        void signedUp(String name);

        void signingUp();

        void error(String message);
    }

    interface Presenter {

        void init();

        void signUp(String email, String password, String photoPath);

    }
}
