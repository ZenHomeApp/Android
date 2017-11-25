package com.eduvilar.zenhome.callback;

import com.eduvilar.zenhome.model.User;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public interface LoginCallback {

    void success(User user);

    void fail(String error);

}
