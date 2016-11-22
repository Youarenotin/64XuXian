package com.xuxian.new_market.mvp.ui.adapter.viewpageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.xuxian.new_market.mvp.entity.IndexConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youarenotin on 2016/10/24.
 */

public class IndexSlidePageAdapter extends FragmentPagerAdapter {
    private final FragmentManager fm;
    IndexConfig data;
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles=new ArrayList<>();
    private  FragmentTransaction transaction;

    public IndexSlidePageAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        transaction = fm.beginTransaction();
    }

    public void setFragments(List<Fragment> lists){
        this.fragments=lists;
    }

    public void setTitles(List<String> titles){
        this.titles=titles;
    }

    @Override
    public int getCount() {
        return fragments==null? 0 :fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /******************************适配viewpager只保存3个fragment**************************************/
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment fragment = (Fragment) super.instantiateItem(container,
//                position);
//        transaction.show(fragment).commit();
//        return fragment;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        Fragment fragment = fragments.get(position);
//        transaction.hide(fragment).commit();
//    }
}
