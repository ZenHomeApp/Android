package com.eduvilar.zenhome.data;

import android.content.SharedPreferences;

import com.eduvilar.zenhome.App;
import com.eduvilar.zenhome.callback.SignUpCallback;
import com.eduvilar.zenhome.model.User;
import com.efraespada.androidstringobfuscator.AndroidStringObfuscator;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class PostData implements DataContract.Post {

    private static String SIGNED_UP_USERS = "signed_up_users";

    @Override
    public void signUpUser(User user, SignUpCallback callback) {
        String enEmail = AndroidStringObfuscator.encryptString(user.getEmail());
        String enPass = AndroidStringObfuscator.encryptString(user.getPassword());
        user.setPassword(enPass);

        try {
            Gson gson = new Gson();
            SharedPreferences shared = App.getContext().getSharedPreferences(SIGNED_UP_USERS, MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString(enEmail, AndroidStringObfuscator.encryptString(gson.toJson(user,User.class)));
            editor.apply();
            callback.success(user.getName());
        } catch (Exception e) {
            callback.fail("error_signing_up_user");
        }

    }



}
