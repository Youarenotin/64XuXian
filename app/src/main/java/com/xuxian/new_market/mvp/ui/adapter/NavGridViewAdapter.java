package com.xuxian.new_market.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.bumptech.glide.Glide;
import com.xuxian.new_market.R;
import com.xuxian.new_market.mvp.entity.CategoryInfo.DataEntity.DataEntity1;

import java.util.List;

/**
 * Created by youarenotin on 2016/10/21.
 */
public class NavGridViewAdapter extends BaseAdapter{
    private Context mContext;

    private List<DataEntity1> data;
    private View.OnClickListener listener;
    private final int screenWidth;

    public NavGridViewAdapter(Context context) {
        mContext=context;
        screenWidth = AbScreenUtils.getScreenHeight(context);
    }

    public void setData(List<DataEntity1> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return data==null || data.isEmpty()? 0: data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemOnClickListener(View.OnClickListener clickListener) {
        this.listener=clickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_nav_gridview, parent, false);
        ImageView iv = (ImageView) view.findViewById(R.id.item_grid_iv);
        if (iv==null )
            return null ;
        AbViewUtil.setViewSize(iv,iv.getLayoutParams().width, (int) AbViewUtil.dip2px(mContext,100));
        Glide.with(mContext).load(data.get(position).getIcon()).placeholder(R.drawable.sliding_left_icon).error(R.drawable.sliding_left_icon).into(iv);
        iv.setOnClickListener(listener);
        return view ;
    }
}
