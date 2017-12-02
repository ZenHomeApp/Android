package com.eduvilar.zenhome.data;

import com.eduvilar.zenhome.callback.LoginCallback;
import com.eduvilar.zenhome.callback.SignUpCallback;
import com.eduvilar.zenhome.model.User;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public interface DataContract {

    interface Get {

        void logInUser(String email, String password, LoginCallback callback);

    }

    interface Post {

        void signUpUser(String email, String password, String photoPath, SignUpCallback callback);

    }
}
