package com.eduvilar.zenhome.fragments.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseFragment;

/**
 * Created by efraespada on 25/11/2017.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeContract.Presenter presenter;

    private LinearLayout loadingView;
    private LinearLayout contentView;

    @Override
    public int layout() {
        return R.layout.fragment_home;
    }

    @Override
    public void view(View view) {
        loadingView = (LinearLayout) view.findViewById(R.id.loading_view);
        contentView = (LinearLayout) view.findViewById(R.id.content_view);
    }

    @Override
    public void init() {
        if (presenter == null) {
            presenter = new HomePresenter(this);
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
