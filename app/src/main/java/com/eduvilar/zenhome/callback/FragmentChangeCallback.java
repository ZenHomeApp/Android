package com.eduvilar.zenhome.callback;

import com.eduvilar.zenhome.base.BaseFragment;

/**
 * Created by efraespada on 29/11/2017.
 */

public interface FragmentChangeCallback {

    void fragmentChanged(Class<? extends BaseFragment> clazz);

}
