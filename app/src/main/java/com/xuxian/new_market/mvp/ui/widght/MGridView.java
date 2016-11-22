package com.xuxian.new_market.mvp.ui.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by youarenotin on 2016/10/22.
 */

public class MGridView  extends GridView{
    public MGridView(Context context) {
        super(context);
    }

    public MGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


        @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST));
    }
}
