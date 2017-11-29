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
import com.eduvilar.zenhome.callback.FragmentChangeCallback;
import com.eduvilar.zenhome.model.Pager;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ContainerDrawerItem;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

/**
 * Created by eduardovilar10 on 23/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected IDrawerItem[] getNavigationItems() {
        return new IDrawerItem[0];
    }

    protected abstract @LayoutRes int getLayout();

    protected abstract BaseFragment[] fragments();

    protected long SETTINGS = 44;

    protected Drawer sidebar;

    private Pager pager;

    private static final int ACTION_ITEM = 3;

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

        sidebar = builder.build();

        pager = (Pager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(fragments().length);
        pager.init(this, fragments(), getFragmentManager(), new FragmentChangeCallback() {
            @Override
            public void fragmentChanged(Class<? extends BaseFragment> clazz) {
                /*List<IDrawerItem> drawerItems = sidebar.getDrawerItems();
                IDrawerItem[] navigationItems = getNavigationItems();
                IDrawerItem[] actionItems = pager.getCurrentFragment().getActionItems();

                for (int i = 0; i < drawerItems.size(); i++) {
                    if (i >= navigationItems.length && drawerItems.get(i).getIdentifier() != SETTINGS) {
                        sidebar.removeItemByPosition(i);
                    }
                }

                sidebar.addItemAtPosition(new SectionDrawerItem().withName("Acciones"), navigationItems.length + 1);

                for (int i = 0; i < actionItems.length; i++) {
                    sidebar.addItemAtPosition(actionItems[i], i + 1 + navigationItems.length);
                }*/
            }
        });
        pager.setCurrentItem(fragments()[0].getClass());
    }

    @Override
    public void onBackPressed() {
        pager.getCurrentFragment().onBackPressed();
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
        showFragment(clazz, false);
    }

    public void showFragment(Class<? extends BaseFragment> clazz, boolean transition) {
        pager.setCurrentItem(clazz, transition);
    }

}

