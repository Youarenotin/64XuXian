package com.xuxian.new_market.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xuxian.new_market.R;
import com.xuxian.new_market.listener.OnShopCarItemClickListener;
import com.xuxian.new_market.mvp.entity.ShoppingCarInfo;
import com.xuxian.new_market.mvp.entity.ShoppingCarInfo.DataEntity.GoodssectionsEntity.GoodslistEntity;
import com.xuxian.new_market.mvp.ui.adapter.sectionadapter.SectionedBaseAdapter;
import com.xuxian.new_market.mvp.ui.widght.ShoppingCarListView;
import com.xuxian.new_market.utils.ImageLoad;

/**
 * Created by youarenotin on 2016/11/9.
 */

public class ShoppingCarGoodsAdapter extends SectionedBaseAdapter implements ShoppingCarListView.PinnedSectionListAdapter {
    private ShoppingCarInfo data;
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_TITLE = 1;
    private Context mContext;
    private int screenWidth;
    private boolean isCheckVisible;

    public ShoppingCarGoodsAdapter(Context mContext) {
        this.mContext = mContext;
        screenWidth = AbScreenUtils.getScreenWidth(mContext);
    }

    public void setOnItemClickListener(OnShopCarItemClickListener listener) {
        this.listener = listener;
    }

    public OnShopCarItemClickListener listener;

    public void setCheckVisible(boolean isVisible) {
        if (isVisible) {
            isCheckVisible = true;
            notifyDataSetChanged();
        } else {
            isCheckVisible = false;
            notifyDataSetChanged();
        }
    }

    public void setAllChecked(boolean isAllChecked) {
        if (isAllChecked) {
            for (GoodslistEntity entity : data.getData().getGoodssections().get(0).getGoodslist()) {
                entity.setIs_del("1");
            }
        }else {
            for (GoodslistEntity entity : data.getData().getGoodssections().get(0).getGoodslist()) {
                entity.setIs_del("0");
            }
        }
        notifyDataSetChanged();
    }

    public void deleteCheckedGoods(){
        for (GoodslistEntity entity : data.getData().getGoodssections().get(0).getGoodslist()) {
            entity.setIs_del("1");
        }
        notifyDataSetChanged();
    }

    public void setData(ShoppingCarInfo data) {
        this.data = data;
    }

    @Override
    public int getCountForSection(int section) {
        return data.getData().getGoodssections().get(section).getGoodslist().size();
    }

    @Override
    public Object getItem(int section, int postion) {
        return null;
    }

    @Override
    public long getItemId(int section, int postion) {
        return 0;
    }

    @Override
    public View getItemView(int section, final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        final GoodslistEntity entity = data.getData().getGoodssections().get(0).getGoodslist().get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_shoppingcartgoods, null);
            holder.item_shoppingcar = (LinearLayout) convertView.findViewById(R.id.item_shoppingcar);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_shopping_cart_checkbox);
            holder.btn_increase = (Button) convertView.findViewById(R.id.btn_increase);
            holder.btn_reduce = (Button) convertView.findViewById(R.id.btn_reduce);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_shopping_cart_img);
            holder.iv_tip = (ImageView) convertView.findViewById(R.id.iv_shop_car_tipsimg);
            holder.tv_main_name = (TextView) convertView.findViewById(R.id.tv_shopping_cart_main_name);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_shopping_cart_title);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_shopping_cart_price);
            holder.tv_count = (TextView) convertView.findViewById(R.id.tv_shopping_cart_count);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AbViewUtil.setViewWH(holder.iv, screenWidth / 4, screenWidth / 4 * 3 / 4);
        holder.tv_main_name.setText(entity.getMain_name());
        SpannableString spanText = new SpannableString("¥");
        spanText.setSpan(new ForegroundColorSpan(Color.parseColor("#ff000000")), 0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        SpannableString spanText1 = new SpannableString("/份");
        spanText1.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffbb33")), 0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.tv_price.setText("");
        holder.tv_price.append(spanText);
        holder.tv_price.append(entity.getPrice());
        holder.tv_price.append(spanText1);
        holder.tv_title.setText("规格: " + entity.getUnit());
        holder.tv_count.setText(entity.getCount());
        if (isCheckVisible) {
            holder.checkBox.setVisibility(View.VISIBLE);
            if (entity.getIs_del().equals("1")){
                holder.checkBox.setChecked(true);
            }
            else {
                holder.checkBox.setChecked(false);
            }
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    entity.setIs_del("1");
                } else {
                    entity.setIs_del("0");
                }
            }
        });
        ImageLoad.loadImage(mContext, entity.getTipsimg(), holder.iv_tip, R.drawable.default_tipsimg, R.drawable.default_tipsimg, DiskCacheStrategy.NONE);
        ImageLoad.loadImage(mContext, entity.getIcon(), holder.iv, R.drawable.default_newimg, R.drawable.default_newimg, DiskCacheStrategy.RESULT);
        final ViewHolder finalHolder = holder;
        if (listener != null) {
            holder.btn_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(v, finalHolder.tv_count, position, entity);
                }
            });
            holder.btn_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(v, finalHolder.tv_count, position, entity);
                }
            });
        }
        return convertView;
    }

    @Override
    public int getSectionCount() {
        if (data.getData().getGoodssections().size() == 0 || data.getData().getGoodssections() == null)
            return 0;
        return data.getData().getGoodssections().size();
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup viewGroup) {
        ViewHolderTitle holder = null;
        if (convertView == null) {
            holder = new ViewHolderTitle();
            holder.linearLayout = new LinearLayout(this.mContext);
            holder.linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            holder.linearLayout.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            holder.linearLayout.setPadding(20, 20, 20, 20);
            holder.textView_title = new TextView(this.mContext);
            holder.linearLayout.addView(holder.textView_title);
            convertView = holder.linearLayout;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderTitle) convertView.getTag();
        }
        convertView.setBackgroundColor(Color.parseColor("#ffedebeb"));
        if (data.getData().getGoodssections() != null && data.getData().getGoodssections().size() > 0) {
            holder.textView_title.setText(data.getData().getGoodssections().get(section).getSectionname());
        }
        return convertView;
    }

    @Override
    public int getCount() {
        if (data.getData().getGoodssections().size() == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < data.getData().getGoodssections().size(); i++) {
            count += data.getData().getGoodssections().get(i).getGoodslist().size();
        }
        count += data.getData().getGoodssections().size();
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == VIEW_TYPE_TITLE;
    }

    class ViewHolderTitle {
        CheckBox checkBox;
        LinearLayout linearLayout;
        TextView textView_title;

        ViewHolderTitle() {
        }
    }

    class ViewHolder {
        LinearLayout item_shoppingcar;
        CheckBox checkBox;
        ImageView iv;
        TextView tv_main_name;
        TextView tv_title;
        TextView tv_price;
        TextView tv_count;
        ImageView iv_tip;
        Button btn_reduce;
        Button btn_increase;
    }
}
