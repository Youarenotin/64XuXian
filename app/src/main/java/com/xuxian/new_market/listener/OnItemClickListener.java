package com.xuxian.new_market.listener;

import android.view.View;

import com.xuxian.new_market.mvp.entity.SectionGoodsEntity;
import com.xuxian.new_market.mvp.ui.adapter.RecylerAdapter.GoodsAdatper;

/**
 * Created by youarenotin on 2016/10/25.
 */

public interface OnItemClickListener {
    void onItemClick(GoodsAdatper.BaseViewHolder viewHolder, int position, SectionGoodsEntity.DataEntity.GoodsEntity goodsEntity);
}
