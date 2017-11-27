package com.eduvilar.zenhome.fragments.login;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.activities.MainActivity;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.fragments.signup.SignUpFragment;
import com.tapadoo.alerter.Alerter;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.Presenter presenter;

    private LinearLayout loadingView;
    private LinearLayout contentView;
    private EditText email;
    private EditText password;
    private Button login;
    private Button signup;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void view(View view) {
        loadingView = (LinearLayout) view.findViewById(R.id.loading_view);
        contentView = (LinearLayout) view.findViewById(R.id.content_view);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        login = (Button) view.findViewById(R.id.login);
        login.setOnClickListener(this);
        signup = (Button) view.findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        activity().finish();
    }

    @Override
    public void init() {
        if (presenter == null) {
            presenter = new LoginPresenter(this);
        }
        presenter.init();
    }

    @Override
    public void success(String email) {
        Alerter.create(activity())
                .setTitle("Bienvenido")
                .setText(email)
                .show();

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity(), MainActivity.class);
                activity().startActivity(intent);
                activity().finish();
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void error(String error) {
        Alerter.create(activity())
                .setTitle("Error")
                .setText(error)
                .show();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.login:
                presenter.login(email.getText().toString(), password.getText().toString());
                break;

            case R.id.signup:
                activity().showFragment(SignUpFragment.class, true);
                break;

            default:
                // nothing to do here
                break;
        }
    }
}
