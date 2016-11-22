package com.xuxian.new_market.mvp.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by youarenotin on 2016/10/25.
 */

public abstract class BaseLazyLoadFragment extends Fragment {
    protected String id;
    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(String.valueOf(id),"setUserVisibleHint");
        if (getUserVisibleHint()) {
            isVisible=true;
            loadData();
        }else {
            isVisible=false;
//            onInVisible();
        }
    }

    private void loadData() {
        if(!isPrepared || !isVisible || !isFirst){
            return;
        }
        isFirst = false;
        onLoadData();
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.d(String.valueOf(id),"getUserVisibleHint");
        return super.getUserVisibleHint();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(String.valueOf(id),"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(String.valueOf(id),"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(String.valueOf(id),"onCreateView");
       return initView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(String.valueOf(id),"onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(String.valueOf(id),"onActivityCreated");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(String.valueOf(id),"onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(String.valueOf(id),"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(String.valueOf(id),"onDestroy");
    }

    @Override
    public void onResume() {
        super.onResume();
        isPrepared=true;
        if (getUserVisibleHint() && isFirst ) {
            setUserVisibleHint(true);
        }
    }

    /**
     * 子fragment的布局view
     * @return
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void onInVisible();

    protected abstract void onLoadData();
}
