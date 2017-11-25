package com.eduvilar.zenhome.fragments.flat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.fragments.home.HomeContract;

/**
 * Created by efraespada on 25/11/2017.
 */

public class FlatFragment extends BaseFragment implements FlatContract.View {

    private FlatContract.Presenter presenter;

    private LinearLayout loadingView;
    private LinearLayout contentView;

    public static FlatFragment newInstance() {
        return new FlatFragment();
    }

    @Override
    public int layout() {
        return R.layout.fragment_flat;
    }

    @Override
    public void view(View view) {
        loadingView = (LinearLayout) view.findViewById(R.id.loading_view);
        contentView = (LinearLayout) view.findViewById(R.id.content_view);
    }

    @Override
    public void init() {
        if (presenter == null) {
            presenter = new FlatPresenter(this);
        }
        presenter.init();
    }

    @Override
    public void onViewLoading() {
        loadingView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    @Override
    public void onViewReady() {
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

}
