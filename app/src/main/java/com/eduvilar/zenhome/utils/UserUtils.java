package com.eduvilar.zenhome.utils;

import com.eduvilar.zenhome.model.User;

/**
 * Created by efraespada on 02/12/2017.
 */

public class UserUtils {

    private static User user;

    private UserUtils() {
        // nothing to do here
    }

    public static User user() {
        return user;
    }

    public static void setUser(User u) {
        user = u;
    }
}
