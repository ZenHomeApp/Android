package com.eduvilar.zenhome.fragments.login;

import android.view.View;
import android.widget.LinearLayout;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.fragments.flat.FlatContract;
import com.eduvilar.zenhome.fragments.flat.FlatPresenter;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private LinearLayout loadingView;
    private LinearLayout contentView;

    public static LoginFragment newInstance() {
        return new LoginFragment();
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
            presenter = new LoginPresenter(this);
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
