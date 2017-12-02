package com.eduvilar.zenhome.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.eduvilar.zenhome.App;
import com.eduvilar.zenhome.callback.DialogCallback;

/**
 * Created by efraespada on 02/12/2017.
 */

public class DialogUtils {

    private DialogUtils() {
        // nothing to do here
    }

    public static void dialog(Activity activity, String title, @LayoutRes int layout, final DialogCallback callback) {
        final View view = activity.getLayoutInflater().inflate(layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setView(callback.view(view))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.accept(dialog, view);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.cancel(dialog, view);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
