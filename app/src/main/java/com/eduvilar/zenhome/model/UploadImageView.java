package com.eduvilar.zenhome.model;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by efraespada on 29/11/2017.
 */

public class UploadImageView extends android.support.v7.widget.AppCompatImageView {
    private String mSomeProperty;

    public UploadImageView(Context context) {
        super(context);
    }

    public UploadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UploadImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void end() {
        setDrawingCacheEnabled(false); // clear the cache here
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setDrawingCacheEnabled(true); // cache
    }

}
