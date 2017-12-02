package com.eduvilar.zenhome.data;

import android.content.SharedPreferences;

import com.eduvilar.zenhome.App;
import com.eduvilar.zenhome.callback.ImageUploadCallback;
import com.eduvilar.zenhome.callback.SignUpCallback;
import com.eduvilar.zenhome.callback.UpdateUserCallback;
import com.eduvilar.zenhome.model.User;
import com.eduvilar.zenhome.utils.UploadImageUtils;
import com.eduvilar.zenhome.utils.UserUtils;
import com.efraespada.androidstringobfuscator.AndroidStringObfuscator;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class PostData implements DataContract.Post {

    private static String SIGNED_UP_USERS = "signed_up_users";
    private static String EMPTY_OBJECT = "{}";


    @Override
    public void signUpUser(final String email, final String password, String photoPath, final SignUpCallback callback) {
        UploadImageUtils.upload(photoPath, new ImageUploadCallback() {
            @Override
            public void success(String url) {
                User user = new User(email, password, url);
                String enEmail = AndroidStringObfuscator.encryptString(user.getEmail());
                String enPass = AndroidStringObfuscator.encryptString(user.getPassword());
                user.setPassword(enPass);

                try {
                    Gson gson = new Gson();
                    SharedPreferences shared = App.getContext().getSharedPreferences(SIGNED_UP_USERS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString(enEmail, AndroidStringObfuscator.encryptString(gson.toJson(user,User.class)));
                    editor.apply();
                    callback.success(user.getEmail());
                } catch (Exception e) {
                    callback.fail("error_signing_up_user");
                }
            }

            @Override
            public void error(String error) {
                callback.fail(error);
            }
        });
    }

    @Override
    public void saveUser(UpdateUserCallback callback) {
        Gson gson = new Gson();
        String enEmail = AndroidStringObfuscator.encryptString(UserUtils.user().getEmail());
        User u = null;
        try {
            SharedPreferences shared = App.getContext().getSharedPreferences(SIGNED_UP_USERS, MODE_PRIVATE);
            String enStoredUser = shared.getString(enEmail, null);
            if (enEmail != null && enStoredUser.length() > EMPTY_OBJECT.length()) {
                try {
                    String storedUser = AndroidStringObfuscator.decryptString(enStoredUser);
                    User user = gson.fromJson(storedUser, User.class);
                    u = callback.onUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.fail("error_reading_user");
                }
            } else {
                callback.fail("user_not_found");
            }

            SharedPreferences.Editor editor = shared.edit();
            editor.putString(enEmail, AndroidStringObfuscator.encryptString(gson.toJson(u, User.class)));
            editor.apply();

            callback.userUpdated(u);
        } catch (Exception e) {
            callback.fail(e.getLocalizedMessage());
        }



    }


}
