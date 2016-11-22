package com.xuxian.new_market.listener;

import android.view.View;
import android.widget.TextView;

import com.xuxian.new_market.mvp.entity.ShoppingCarInfo;

/**
 * Created by youarenotin on 2016/11/18.
 */

public interface OnShopCarItemClickListener {
    void OnItemClick(View v, TextView tv_count, int position, ShoppingCarInfo.DataEntity.GoodssectionsEntity.GoodslistEntity entity);
}
