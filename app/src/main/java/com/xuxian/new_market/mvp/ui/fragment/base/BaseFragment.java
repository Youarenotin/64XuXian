package com.xuxian.new_market.mvp.ui.fragment.base;

import android.support.v4.app.Fragment;

/**
 * Created by youarenotin on 2016/10/20.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
        initEvent();
    }

    protected abstract void initEvent();
}
