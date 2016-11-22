package com.xuxian.new_market.mvp.ui.widght;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xuxian.new_market.listener.OnRecyclerTouchScrollListener;

/**
 * Created by youarenotin on 2016/10/27.
 */

public class CustomRecyclerView extends RecyclerView   {
    public float downY;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            this.downY=e.getRawY();
        }
        return super.onInterceptTouchEvent(e);
    }


}
