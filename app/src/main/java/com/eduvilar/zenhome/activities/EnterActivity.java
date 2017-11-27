package com.eduvilar.zenhome.activities;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseActivity;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.fragments.login.LoginFragment;
import com.eduvilar.zenhome.fragments.signup.SignUpFragment;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class EnterActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_enter;
    }

    @Override
    protected BaseFragment[] fragments() {
        return new BaseFragment[]{
                LoginFragment.newInstance(),
                SignUpFragment.newInstance()
        };
    }
}
