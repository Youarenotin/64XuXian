package com.xuxian.new_market.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuxian.new_market.R;
import com.xuxian.new_market.mvp.ui.fragment.base.BaseFragment;

/**
 * Created by youarenotin on 2016/11/8.
 */

public class PersonalFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fra_shoppingcarfragment,container,false);
    }

    @Override
    protected void initEvent() {

    }
}
