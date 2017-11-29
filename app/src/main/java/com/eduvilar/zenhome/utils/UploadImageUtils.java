package com.eduvilar.zenhome.utils;

import android.graphics.Bitmap;
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

    public static void upload(final UploadImageView view, final ImageUploadCallback callback) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = view.getDrawingCache();
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
                        view.end();
                    }
                });
            }
        };
        handler.postDelayed(runnable, 7000);

    }
}
