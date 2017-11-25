package com.eduvilar.zenhome.activities;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseActivity;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.fragments.HomeFragment;
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

public class MainActivity extends BaseActivity {

    @Override
    protected IDrawerItem[] getNavigationItems() {
        return new IDrawerItem[] {
                new PrimaryDrawerItem().withIdentifier(1).withName("Inicio"),
                new SecondaryDrawerItem().withIdentifier(2).withName("Vivienda"),
                new DividerDrawerItem(),
                new SecondaryDrawerItem().withName("Settings")
        };
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseFragment[] fragments() {
        return new BaseFragment[] {
                HomeFragment.newInstance()
        };
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


}
