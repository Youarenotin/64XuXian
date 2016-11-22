package com.xuxian.new_market.mvp.ui.adapter.viewpageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xuxian.new_market.mvp.entity.CategoryInfo;
import com.xuxian.new_market.mvp.ui.fragment.Category_one_fragment;

import java.util.List;

/**
 * Created by youarenotin on 2016/10/21.
 */

public class CategoryPageAdapter extends FragmentPagerAdapter {
    private List<CategoryInfo> data;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    private List<Fragment> fragments;

    public CategoryPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<CategoryInfo> list) {
        this.data = list;
    }

    public void addData(CategoryInfo info) {
        if (data != null) {
            data.add(info);
        }
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}
