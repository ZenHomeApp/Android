package com.eduvilar.zenhome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseFragment;

/**
 * Created by efraespada on 25/11/2017.
 */

public class FlatFragment extends BaseFragment {

    public static FlatFragment newInstance() {
        return new FlatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
