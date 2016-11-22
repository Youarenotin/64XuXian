package com.xuxian.new_market.mvp.ui.adapter.RecylerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.http.AbRequestParams;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.market.new_xuxian.greendao.ShoppingCartGoods;
import com.market.new_xuxian.greendaodao.ShoppingCartGoodsDao;
import com.xuxian.new_market.R;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.listener.Monitor.FunctionMonitor;
import com.xuxian.new_market.listener.Monitor.MonitorEnum;
import com.xuxian.new_market.listener.Monitor.ShoppingCarNumMonitor;
import com.xuxian.new_market.listener.OnItemClickListener;
import com.xuxian.new_market.mvp.entity.SectionGoodsEntity.DataEntity.GoodsEntity;
import com.xuxian.new_market.mvp.ui.fragment.ChildFragment;
import com.xuxian.new_market.respository.db.DBHelper;
import com.xuxian.new_market.respository.network.RetrofitManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by youarenotin on 2016/10/25.
 */

public class GoodsAdatper extends RecyclerView.Adapter<GoodsAdatper.BaseViewHolder> {
    public static final int ITEM_LAYOUT_LIST = 0;
    public static final int ITEM_LAYOUT_GRID = 1;

    private List<GoodsEntity> data;
    private OnItemClickListener listener;
    private Context mContext;
    private int screenWidth;
    private final float density;
    private int type =0;
    private GestureDetector gesture = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            AbRequestParams params = new AbRequestParams();
            params.put("goodsId", "1241");
            params.put("STORE_ID", "882");
            params.put("userId", "1609759");
//            params.put("token",params.getMd5());
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("goodsId", "1241");
            paramsMap.put("STORE_ID", "882");
            paramsMap.put("userId", "1609759");
            paramsMap.put("token", params.getMd5());
            RetrofitManager.getInstance().getGoodsInfo(ApiConstant.ver, "" + System.currentTimeMillis(), paramsMap);
            return super.onSingleTapUp(e);
        }
    });

    public GoodsAdatper(Context mContext) {
        this.mContext = mContext;
        type= Integer.parseInt(AbPreferenceUtils.loadPrefString(mContext, ApiConstant.goodslayout,"0"));
        screenWidth = AbScreenUtils.getScreenWidth(mContext);
        density = mContext.getResources().getDisplayMetrics().density;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addAll(List<GoodsEntity> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    public void add(List<GoodsEntity> list) {
        int startIndex = this.data.size();
        this.data.addAll(list);
        notifyItemRangeChanged(startIndex, list.size());
    }

    public void addPosition(GoodsEntity entity, int position) {
        data.add(position, entity);
        notifyItemInserted(position);
    }

    public void removePosition(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void replacePosition(GoodsEntity entity, int position) {
        data.remove(position);
        data.add(position, entity);
        notifyItemChanged(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_LAYOUT_LIST:
                view = getView(parent, R.layout.item_goods_list);
                GoodsListViewHolder viewHoler_list = new GoodsListViewHolder(view);
                return viewHoler_list;
            case ITEM_LAYOUT_GRID:
                view = getView(parent, R.layout.item_goods_grid);
                GoodsGridViewHolder viewHoler_grid = new GoodsGridViewHolder(view);
                return viewHoler_grid;
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return this.type;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof GoodsListViewHolder) {
            BindListViewHolder(holder, position);
        } else if (holder instanceof GoodsGridViewHolder) {
            BindGridViewHolder(holder, position);
        }
    }

    public void setType(int type) {
        this.type = type;
    }


    /**
     * 列表模式点击处理
     * @param viewHoler
     * @param position
     */
    private void setListItemClickEvent(final GoodsListViewHolder viewHoler, final int position) {
        GoodsEntity goodsEntity = data.get(position);
        listener.onItemClick(viewHoler,position,goodsEntity);
    }

    /**
     * 表格模式点击处理
     * @param holder
     * @param position
     */
    private void setGridItemClickEvent(GoodsGridViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private View getView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    /**
     * 创建表格模式viewholder
     * @param baseViewHolder
     * @param position
     */
    private void BindGridViewHolder(BaseViewHolder baseViewHolder, int position) {
        GoodsEntity goodsEntity = data.get(position);
        GoodsGridViewHolder holder = (GoodsGridViewHolder) baseViewHolder;
        setGridItemClickEvent(holder,position);
        setGridItemValues(holder,goodsEntity);
    }

    /**
     * 创建列表模式viewholder
     * @param baseViewHolder
     * @param position
     */
    private void BindListViewHolder(BaseViewHolder baseViewHolder, int position) {
        GoodsEntity goodsEntity = data.get(position);
        GoodsListViewHolder holder = (GoodsListViewHolder) baseViewHolder;
        setListItemClickEvent(holder, position);
        setListItemValues(holder, goodsEntity);
    }

    /**
     * 设置表格模式显示
     * @param holder
     * @param goodsEntity
     */
    private void setGridItemValues(GoodsGridViewHolder holder, GoodsEntity goodsEntity) {
        String url_imgGood = goodsEntity.getNewimg();
        String url_showImg = null;
        if (goodsEntity.getShow_imgs() != null && !goodsEntity.getShow_imgs().isEmpty()) {
            url_showImg = goodsEntity.getShow_imgs().get(0);
        }
        String url_tip = goodsEntity.getTipsimg();
        String main_name = goodsEntity.getMain_name();
        String title = goodsEntity.getTitle();
        String no_mem_price = goodsEntity.getPrice_info();
        String mem_price = goodsEntity.getPrice();

        AbViewUtil.setViewWH(holder.item_grid_container, screenWidth /2, (int) (screenWidth *0.65));
        AbViewUtil.setViewWH(holder.rl_imgContainer, screenWidth /2, screenWidth *3/8);

        holder.tv_main_name.setText(main_name);
        holder.tv_title.setText(title);
        holder.tv_no_mem_price.setText(no_mem_price);
        holder.tv_mem_price.setText(mem_price);

        //进入商品详情
        holder.itemView.setOnClickListener(new ChildFragment.ItemOnClickListener(mContext, goodsEntity));

        Glide.with(mContext)
                .load(url_imgGood)
                .error(R.drawable.default_newimg)
                .placeholder(R.drawable.default_newimg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_goodImg);
        if (url_showImg != null && !url_showImg.isEmpty()) {
            Glide.with(mContext)
                    .load(url_showImg)
                    .error(R.drawable.default_tipsimg)
                    .placeholder(R.drawable.default_newimg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.iv_showImg);
        }
        Glide.with(mContext)
                .load(url_tip)
                .error(R.drawable.default_newimg)
                .placeholder(R.drawable.default_newimg)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.iv_tip);
    }

    /**
     * 设置列表模式显示
     * @param holder
     * @param goodsEntity
     */
    private void setListItemValues(GoodsListViewHolder holder, GoodsEntity goodsEntity) {
        String url_imgGood = goodsEntity.getNewimg();
        String url_showImg = null;
        if (goodsEntity.getShow_imgs() != null && !goodsEntity.getShow_imgs().isEmpty()) {
            url_showImg = goodsEntity.getShow_imgs().get(0);
        }
        String url_tip = goodsEntity.getTipsimg();
        String main_name = goodsEntity.getMain_name();
        String title = goodsEntity.getTitle();
        String no_mem_price = goodsEntity.getPrice_info();
        String mem_price = goodsEntity.getPrice();

        AbViewUtil.setViewWH(holder.item_goods_container, screenWidth, screenWidth * 2 / 5);
        AbViewUtil.setViewWH(holder.item_img_container, screenWidth * 2 / 5, screenWidth * 2 / 5 * 5 / 7);

        holder.tv_main_name.setText(main_name);
        holder.tv_title.setText(title);
        holder.tv_nomem_price.setText(no_mem_price);
        holder.tv_mem_price.setText(mem_price);

        //进入商品详情
        holder.itemView.setOnClickListener(new ChildFragment.ItemOnClickListener(mContext, goodsEntity));

        Glide.with(mContext)
                .load(url_imgGood)
                .error(R.drawable.default_newimg)
                .placeholder(R.drawable.default_newimg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_goodImg);
        if (url_showImg != null && !url_showImg.isEmpty()) {
            Glide.with(mContext)
                    .load(url_showImg)
                    .error(R.drawable.default_tipsimg)
                    .placeholder(R.drawable.default_newimg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.iv_showImgs);
        }
        Glide.with(mContext)
                .load(url_tip)
                .error(R.drawable.default_newimg)
                .placeholder(R.drawable.default_newimg)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.iv_tip);
    }

    /**
     * 列表viewholder
     */
    public static class GoodsListViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_goodImg)
        public ImageView iv_goodImg;
        @BindView(R.id.iv_showImgs)
        public ImageView iv_showImgs;
        @BindView(R.id.main_name)
        public TextView tv_main_name;
        @BindView(R.id.tv_title)
        public TextView tv_title;
        @BindView(R.id.tv_mem_price)
        public TextView tv_mem_price;
        @BindView(R.id.tv_nomem_price)
        public TextView tv_nomem_price;
        @BindView(R.id.tv_classify)
        public TextView tv_classify;
        @BindView(R.id.iv_tip)
        public ImageView iv_tip;
        public ImageView iv_add;
        public LinearLayout item_btn_container;
        public ImageView iv_delete;
        public TextView iv_rectange;
        public RelativeLayout item_img_container;
        public LinearLayout item_goods_container;

        public GoodsListViewHolder(View itemView) {
            super(itemView);
            iv_goodImg = (ImageView) itemView.findViewById(R.id.iv_goodImg);
            iv_showImgs = (ImageView) itemView.findViewById(R.id.iv_showImgs);
            tv_main_name = (TextView) itemView.findViewById(R.id.main_name);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_mem_price = (TextView) itemView.findViewById(R.id.tv_mem_price);
            tv_nomem_price = (TextView) itemView.findViewById(R.id.tv_nomem_price);
            tv_classify = (TextView) itemView.findViewById(R.id.tv_classify);
            iv_tip = (ImageView) itemView.findViewById(R.id.iv_tip);
            iv_add = (ImageView) itemView.findViewById(R.id.iv_add);
            item_img_container = (RelativeLayout) itemView.findViewById(R.id.item_goods_image_container);
            item_goods_container = (LinearLayout) itemView.findViewById(R.id.item_goods_container);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
            iv_rectange = (TextView) itemView.findViewById(R.id.iv_rectange);
            item_btn_container = (LinearLayout) itemView.findViewById(R.id.ll_btn_add_delete);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 表格viewholder
     */
    public static class GoodsGridViewHolder extends BaseViewHolder {

        public  TextView tv_main_name;
        public  ImageView iv_goodImg;
        public  ImageView iv_showImg;
        public  ImageView iv_tip;
        public  TextView tv_title;
        public  TextView tv_mem_price;
        public  TextView tv_no_mem_price;
        public  RelativeLayout rl_imgContainer;
        public  LinearLayout item_grid_container;

        public GoodsGridViewHolder(View itemView) {
            super(itemView);

            tv_main_name = (TextView) itemView.findViewById(R.id.main_name_grid);
            iv_goodImg = (ImageView) itemView.findViewById(R.id.iv_grid_goodsimg);
            iv_showImg = (ImageView) itemView.findViewById(R.id.iv_grid_showimg);
            iv_tip = (ImageView) itemView.findViewById(R.id.iv_grid_tip);
            tv_title = (TextView) itemView.findViewById(R.id.tv__grid_title);
            tv_mem_price = (TextView) itemView.findViewById(R.id.tv_grid_mem_price);
            tv_no_mem_price = (TextView) itemView.findViewById(R.id.tv_grid_nomem_price);
            rl_imgContainer = (RelativeLayout) itemView.findViewById(R.id.showimgContainer);
            item_grid_container = (LinearLayout) itemView.findViewById(R.id.item_grid_container);
        }
    }

    /**
     * baseViewholder
     */
    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

}
