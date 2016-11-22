package com.xuxian.new_market.listener;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by youarenotin on 2016/10/26.
 */

public interface OnRecyclerTouchScrollListener {
    public void onScrolled(RecyclerView recyclerView, int dx, int dy);

    public void onScrollStateChanged(RecyclerView recyclerView, int newState);

    public boolean onTouch(View v, MotionEvent event);

    public void showFunctions();

}
