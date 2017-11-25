package com.eduvilar.zenhome.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eduardovilar10 on 23/11/2017.
 */

public abstract class BaseFragment extends Fragment {

    public abstract void init();

    public abstract @LayoutRes int layout();

    public abstract void view(View view);

    public boolean viewReady;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(layout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view(view);
        viewReady = true;
    }

    public boolean isViewReady() {
        return viewReady;
    }

}
