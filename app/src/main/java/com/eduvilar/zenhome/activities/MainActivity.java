package com.eduvilar.zenhome.activities;

import android.view.View;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseActivity;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.fragments.flat.FlatFragment;
import com.eduvilar.zenhome.fragments.home.HomeFragment;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends BaseActivity {

    @Override
    protected IDrawerItem[] getNavigationItems() {
        return new IDrawerItem[] {
                new PrimaryDrawerItem().withIdentifier(1).withName("Inicio").withIcon(CommunityMaterial.Icon.cmd_home_variant).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        showFragment(HomeFragment.class);
                        return false;
                    }
                }),
                new SecondaryDrawerItem().withIdentifier(2).withName("Viviendas").withIcon(CommunityMaterial.Icon.cmd_home_modern).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        showFragment(FlatFragment.class);
                        return false;
                    }
                }),
                new DividerDrawerItem(),
                new SecondaryDrawerItem().withIdentifier(SETTINGS).withName("Settings")
        };
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseFragment[] fragments() {
        return new BaseFragment[] {
                HomeFragment.newInstance(),
                FlatFragment.newInstance()
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
