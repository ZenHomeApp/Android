package com.eduvilar.zenhome.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.eduvilar.zenhome.BuildConfig;
import com.eduvilar.zenhome.callback.ImageUploadCallback;
import com.eduvilar.zenhome.model.UploadImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by efraespada on 29/11/2017.
 */

public class UploadImageUtils {

    private UploadImageUtils() {
        // nothing to do here
    }

    public static void upload(final String path, final ImageUploadCallback callback) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = FirebaseStorage.getInstance().getReference().child(BuildConfig.BUILD_TYPE).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                callback.error(exception.getLocalizedMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                callback.success(taskSnapshot.getMetadata().getDownloadUrl().toString());
            }
        });
    }
}
