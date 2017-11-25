package com.eduvilar.zenhome.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.View;

/**
 * Created by Efra Espada on 14-Apr-16.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {

    private BaseFragment[] fragments;

    public FragmentAdapter(FragmentManager fm, BaseFragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        BaseFragment t = (BaseFragment) object;
        view.setTag(getFragmentPosition(t));
        return super.isViewFromObject(view, object);
    }

    public Integer getFragmentPosition(BaseFragment fragment) {
        Integer pos = null;

        for (int i = 0; i < fragments.length; i++) {
            if (fragments[i].getClass().getSimpleName().equalsIgnoreCase(fragment.getClass().getSimpleName())) {
                pos = i;
                break;
            }
        }

        return pos;
    }
}
