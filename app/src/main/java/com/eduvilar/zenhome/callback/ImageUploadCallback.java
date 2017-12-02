package com.eduvilar.zenhome.callback;

/**
 * Created by efraespada on 29/11/2017.
 */

public interface ImageUploadCallback {

    void success(String url);

    void error(String error);

}
