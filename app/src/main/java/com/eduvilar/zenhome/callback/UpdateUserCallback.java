package com.eduvilar.zenhome.callback;

import com.eduvilar.zenhome.model.User;

/**
 * Created by efraespada on 02/12/2017.
 */

public interface UpdateUserCallback {

    User onUser(User user);

    void userUpdated(User user);

    void fail(String error);

}
