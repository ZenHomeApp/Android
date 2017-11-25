package com.eduvilar.zenhome.data;

import android.content.SharedPreferences;

import com.eduvilar.zenhome.App;
import com.eduvilar.zenhome.callback.LoginCallback;
import com.eduvilar.zenhome.model.User;
import com.efraespada.androidstringobfuscator.AndroidStringObfuscator;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by eduardovilar10 on 22/11/2017.
 */

public class GetData implements DataContract.Get {

    private static String SIGNED_UP_USERS = "signed_up_users";
    private static String EMPTY_OBJECT = "{}";

    @Override
    public void logInUser(String email, String password, LoginCallback callback) {
        Gson gson = new Gson();
        String enEmail = AndroidStringObfuscator.encryptString(email);
        String enPass = AndroidStringObfuscator.encryptString(password);
        SharedPreferences shared = App.getContext().getSharedPreferences(SIGNED_UP_USERS, MODE_PRIVATE);
        String enStoredUser = shared.getString(enEmail, null);
        if (enEmail != null && enEmail.length() > EMPTY_OBJECT.length()) {
            try {
                String storedUser = AndroidStringObfuscator.decryptString(enStoredUser);
                User user = gson.fromJson(storedUser, User.class);
                if (user.getPassword().equals(enPass)) {
                    callback.success(user);
                } else {
                    callback.fail("wrong_password");
                }
            } catch (Exception e) {
                e.printStackTrace();
                callback.fail("error_reading_user");
            }
        } else {
            callback.fail("user_not_found");
        }
    }
}
