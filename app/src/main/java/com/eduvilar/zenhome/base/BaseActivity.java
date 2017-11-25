package com.eduvilar.zenhome.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.model.Pager;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 * Created by eduardovilar10 on 23/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract IDrawerItem[] getNavigationItems();

    protected abstract @LayoutRes int getLayout();

    protected abstract BaseFragment[] fragments();

    private Pager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSelectionListEnabledForSingleProfile(false)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Eduardo Vilar").withEmail("eduardovilar10@gmail.com").withIcon(getResources().getDrawable(R.mipmap.ic_launcher_round))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        DrawerBuilder builder = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult);

        builder.addDrawerItems(getNavigationItems());

        builder.build();

        pager = (Pager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(fragments().length);
        pager.init(this, fragments(), getFragmentManager());
        pager.setCurrentItem(fragments()[0].getClass());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ...
    }

    @Override
    protected void onDestroy() {
        // ...
        super.onDestroy();
    }

    public void showFragment(Class<? extends BaseFragment> clazz) {
        pager.setCurrentItem(clazz);
    }

}

