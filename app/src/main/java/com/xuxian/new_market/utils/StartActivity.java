package com.xuxian.new_market.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xuxian.new_market.mvp.entity.SectionGoodsEntity.DataEntity.GoodsEntity;
import com.xuxian.new_market.mvp.ui.activity.GoodsInfoActivity;

/**
 * Created by youarenotin on 2016/10/31.
 */

public class StartActivity {
    public static void startGoodsInfoActivity(Context context,GoodsEntity goodsEntity){
        Intent in = new Intent(context, GoodsInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("intent_object", goodsEntity);
        in.putExtra("intent_bundle", bundle);
        context.startActivity(in);
    }
}
