package com.eduvilar.zenhome.fragments.signup;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.fragments.login.LoginFragment;
import com.eduvilar.zenhome.model.User;
import com.eduvilar.zenhome.utils.ImageUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tapadoo.alerter.Alerter;

import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class SignUpFragment extends BaseFragment implements SignUpContract.View, View.OnClickListener {

    private SignUpContract.Presenter presenter;

    private RoundedImageView profile;
    private LinearLayout loadingView;
    private LinearLayout contentView;
    private File imageProfile;
    private EditText email;
    private EditText password;
    private Button signUp;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public int layout() {
        return R.layout.fragment_signup;
    }

    @Override
    public void view(View view) {
        loadingView = (LinearLayout) view.findViewById(R.id.loading_view);
        contentView = (LinearLayout) view.findViewById(R.id.content_view);
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        profile = (RoundedImageView) view.findViewById(R.id.image_profile);
        ImageUtils.load(activity().getString(R.string.default_image_profile), profile);
        profile.setOnClickListener(this);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        signUp = (Button) view.findViewById(R.id.signup);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        activity().showFragment(LoginFragment.class, true);
    }

    @Override
    public void init() {
        if (presenter == null) {
            presenter = new SignUpPresenter(this);
        }
        presenter.init();
    }



    @Override
    public void signedUp(String name) {
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        Alerter.create(activity())
                .setTitle("Bienvenido")
                .setText("Hola " + name + ", ya puedes iniciar sesión")
                .show();
        onBackPressed();
    }

    @Override
    public void signingUp() {
        loadingView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    @Override
    public void error(String error) {
        Alerter.create(activity())
                .setTitle("Error")
                .setText(error)
                .show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.signup:
                presenter.signUp(email.getText().toString(), password.getText().toString(), imageProfile.getAbsolutePath());
                break;

            case R.id.image_profile:
                EasyImage.openChooserWithGallery(SignUpFragment.this, "Seleccione una imagen", 0);
                break;

            default:
                // nothing to do here
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, activity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                Alerter.create(activity())
                        .setTitle("Error")
                        .setText("error taking image profile")
                        .show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                imageProfile = imageFile;
                profile.setImageURI(Uri.parse(imageProfile.toURI().toString()));
            }
        });
    }
}
