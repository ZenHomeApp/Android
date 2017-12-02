package com.eduvilar.zenhome.callback;

import android.content.DialogInterface;
import android.view.View;

/**
 * Created by efraespada on 02/12/2017.
 */

public interface DialogCallback {

    void accept(DialogInterface dialog, View view);

    void cancel(DialogInterface dialog, View view);

    View view(View view);
}
