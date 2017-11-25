package com.eduvilar.zenhome.data;

import com.eduvilar.zenhome.callback.LoginCallback;

/**
 * Created by efraespada on 25/11/2017.
 */

public interface DataContract {

    interface Get {

        void logInUser(String email, String password, LoginCallback callback);

    }

    interface Post {

        void singUpUser();

    }
}
