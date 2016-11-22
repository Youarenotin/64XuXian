package com.xuxian.new_market.mvp.ui.fragment;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ab.http.AbRequestParams;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.ab.util.AbToastUtil;
import com.ab.util.AbViewUtil;
import com.market.new_xuxian.greendao.ShoppingCartGoods;
import com.market.new_xuxian.greendaodao.ShoppingCartGoodsDao;
import com.xuxian.new_market.R;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.listener.Monitor.FunctionMonitor;
import com.xuxian.new_market.listener.Monitor.MonitorEnum;
import com.xuxian.new_market.listener.Monitor.ShoppingCarNumMonitor;
import com.xuxian.new_market.listener.Monitor.TransitionMonitor;
import com.xuxian.new_market.listener.OnItemClickListener;
import com.xuxian.new_market.listener.OnRecyclerTouchScrollListener;
import com.xuxian.new_market.mvp.entity.AddGoodsEntity;
import com.xuxian.new_market.mvp.entity.SectionGoodsEntity;
import com.xuxian.new_market.mvp.ui.adapter.RecylerAdapter.GoodsAdatper;
import com.xuxian.new_market.mvp.ui.adapter.RecylerAdapter.GoodsAdatper.GoodsGridViewHolder;
import com.xuxian.new_market.mvp.ui.adapter.RecylerAdapter.GoodsAdatper.GoodsListViewHolder;
import com.xuxian.new_market.mvp.ui.fragment.base.BaseLazyLoadFragment;
import com.xuxian.new_market.mvp.ui.widght.ActivityStateView;
import com.xuxian.new_market.mvp.ui.widght.CustomRecyclerView;
import com.xuxian.new_market.mvp.ui.widght.refreshlayout.XuXianNormalRefreshViewHolder;
import com.xuxian.new_market.mvp.ui.widght.refreshlayout.XuXianRefreshLayout;
import com.xuxian.new_market.respository.db.DBHelper;
import com.xuxian.new_market.respository.network.RetrofitManager;
import com.xuxian.new_market.utils.StartActivity;
import com.xuxian.new_market.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by youarenotin on 2016/10/24.
 */

public class ChildFragment extends BaseLazyLoadFragment implements XuXianRefreshLayout.XuXianRefreshLayoutDelegate, OnItemClickListener {
    private XuXianRefreshLayout bla_goods;
    private CustomRecyclerView list;
    private ActivityStateView emptyView;

    private OnRecyclerTouchScrollListener listener;

    public void setIv_sign(ImageView iv_sign) {
        this.iv_sign = iv_sign;
    }

    private ImageView iv_sign;
    private Context mContext;
    private GoodsAdatper goodsAdatper;

    public ChildFragment(String id) {
        this.id = id;
        mContext = getActivity();

    }

    public void setOnScrollListener(OnRecyclerTouchScrollListener listener) {
        this.listener = listener;
    }
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return View.inflate(getActivity(),R.layout.fra_childfragment,null);
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        bla_goods = (XuXianRefreshLayout) view.findViewById(R.id.bla_goods);
        bla_goods.setDelegate(this);
        bla_goods.setRefreshViewHolder(new XuXianNormalRefreshViewHolder(getActivity(), true));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        list = (CustomRecyclerView) view.findViewById(R.id.recyclerLists);
//        list.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                gesture.onTouchEvent(e);
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
        emptyView = (ActivityStateView) view.findViewById(R.id.emptyview_state);
//        iv_sign = (ImageView) view.findViewById(R.id.iv_sign);

        emptyView.setVisibility(View.VISIBLE);
        emptyView.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fra_childfragment, container, false);
    }

    @Override
    protected void onInVisible() {
    }

    int page = 1;

    @Override
    protected void onLoadData() {
        TransitionMonitor.getInstance().registerTransitionMonitor(this.getTag(), new TransitionMonitor.ICallback() {
            @Override
            public void appOperation(MonitorEnum.TransitionEnum mEnum) {
                switch (mEnum) {
                    case LIST_LAYOUT:
                        layoutTransition(mEnum);
                        break;
                    case GRID_LAYOUT:
                        layoutTransition(mEnum);
                        break;
                }
            }
        });

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("sectionlistId", id);
        paramsMap.put("page", String.valueOf(page));
        paramsMap.put("storeId", AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.STORE_ID));
        paramsMap.put("userId", AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_ID,"0"));
        paramsMap.put("token", Utils.getRequestToken(paramsMap));
        Call<SectionGoodsEntity> call = RetrofitManager.getInstance().getSectionGoods(ApiConstant.ver, String.valueOf(System.currentTimeMillis()), paramsMap);
        call.enqueue(new Callback<SectionGoodsEntity>() {
            @Override
            public void onResponse(Call<SectionGoodsEntity> call, Response<SectionGoodsEntity> response) {
                if (!response.isSuccessful()) {
                    emptyView.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                    return;
                }
                bla_goods.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                goodsAdatper = new GoodsAdatper(getActivity());
                goodsAdatper.setListener(ChildFragment.this);
                String value = AbPreferenceUtils.loadPrefString(getActivity(), ApiConstant.goodslayout, "0");
                if (value.equals("0")) {
                    list.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {

                    list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                }
                list.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (listener != null) {
//                           MotionEvent obtain = MotionEvent.obtain(event);
//                           obtain.offsetLocation(obtain.getX(),list.downY);
                            return listener.onTouch(v, event);
                        }
                        return false;
                    }
                });
                list.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (listener != null) {
                            listener.onScrolled(recyclerView, dx, dy);
                        }
                    }

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (listener != null) {
                            listener.onScrollStateChanged(recyclerView, newState);
                        }
                    }
                });
                list.setAdapter(goodsAdatper);

                goodsAdatper.addAll(response.body().getData().getGoods());
            }

            @Override
            public void onFailure(Call<SectionGoodsEntity> call, Throwable t) {

            }
        });
    }

    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onXuXianRefreshLayoutBeginRefreshing(final XuXianRefreshLayout refreshLayout) {
        page = 1;
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("sectionlistId", id);
        paramsMap.put("page", String.valueOf(page));
        paramsMap.put("storeId", AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.STORE_ID));
        paramsMap.put("userId", AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_ID,"0"));
        paramsMap.put("token", Utils.getRequestToken(paramsMap));
        Call<SectionGoodsEntity> call = RetrofitManager.getInstance().getSectionGoods(ApiConstant.ver, String.valueOf(System.currentTimeMillis()), paramsMap);
        call.enqueue(new Callback<SectionGoodsEntity>() {
            @Override
            public void onResponse(Call<SectionGoodsEntity> call, Response<SectionGoodsEntity> response) {
                if (!response.isSuccessful()) {
                    emptyView.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                    return;
                }
                goodsAdatper.addAll(response.body().getData().getGoods());
                refreshLayout.endRefreshing();
            }

            @Override
            public void onFailure(Call<SectionGoodsEntity> call, Throwable t) {
                refreshLayout.endRefreshing();
            }
        });
    }

    @Override
    public boolean onXuXianRefreshLayoutBeginLoadingMore(final XuXianRefreshLayout refreshLayout) {
        page++;

        requestLoadMore(page, refreshLayout);
        return true;
    }

    private void requestLoadMore(int page, final XuXianRefreshLayout refreshLayout) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("sectionlistId", id);
        paramsMap.put("page", String.valueOf(page));
        paramsMap.put("storeId", AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.STORE_ID));
        paramsMap.put("userId", AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_ID,"0"));
        paramsMap.put("token", Utils.getRequestToken(paramsMap));
        Call<SectionGoodsEntity> call = RetrofitManager.getInstance().getSectionGoods(ApiConstant.ver, String.valueOf(System.currentTimeMillis()), paramsMap);
        call.enqueue(new Callback<SectionGoodsEntity>() {
            @Override
            public void onResponse(Call<SectionGoodsEntity> call, Response<SectionGoodsEntity> response) {
                if (!response.isSuccessful()) {
                    emptyView.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                    return;
                }
                if (response.body().getData() != null)
                    goodsAdatper.add(response.body().getData().getGoods());
                refreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(Call<SectionGoodsEntity> call, Throwable t) {
                refreshLayout.endLoadingMore();
                AbToastUtil.showToast(getActivity(), "加载更多失败");
            }
        });
    }


    private RelativeLayout container;

    public void setContainer(RelativeLayout container) {
        this.container = container;
    }

    private void startAnimToShoppingCar(final View v) {
        final Rect rect = new Rect();
        v.getLocalVisibleRect(rect);

        Path path = new Path();
        final int[] startLoc = new int[2];
        v.getLocationInWindow(startLoc);
//        startLoc[1] -= Math.abs(container.getPaddingTop());
//        startLoc[1] -= 45 * getResources().getDisplayMetrics().density;
//        if (container.getPaddingTop()<0){
//            startLoc[1]=startLoc[1]-(AbScreenUtils.getScreenHeight(getActivity())/8-Math.abs(container.getPaddingTop()));
//        }
//        startLoc[1]-=AbScreenUtils.getScreenHeight(getActivity())/8;
        final float[] mCurrentPosition = new float[2];
//        startLoc[0] = (int) v.getX();
//        startLoc[1]= (int) v.;
        int screenWidth = AbScreenUtils.getScreenWidth(getActivity());
        int screenHeight = AbScreenUtils.getScreenHeight(getActivity());
        path.moveTo(startLoc[0]-v.getWidth(), startLoc[1]-container.getPaddingTop()-v.getHeight());
//        path.moveTo(rect.left,rect.top- AbScreenUtils.getStatusHeight(getActivity()));
        path.quadTo((float) ((screenWidth *0.4  + startLoc[0]) / 2), startLoc[1], (float) (screenWidth *0.4 + iv_sign.getLayoutParams().width / 2), screenHeight- AbViewUtil.dip2px(getActivity(),45));
//        path.quadTo((screenWidth / 2 + rect.left) / 2, rect.top- AbScreenUtils.getStatusHeight(getActivity()), screenWidth / 2 - iv_sign.getLayoutParams().width / 2, screenHeight);
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        ValueAnimator animator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        animator.setDuration(700);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                iv_sign.setVisibility(View.VISIBLE);
                iv_sign.setTranslationX(startLoc[0]);
                iv_sign.setTranslationY(startLoc[1]);
//                iv_sign.setTranslationX(rect.left);
//                iv_sign.setTranslationY(rect.top- AbScreenUtils.getStatusHeight(getActivity()));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv_sign.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                pathMeasure.getPosTan(value, mCurrentPosition, null);
                iv_sign.setTranslationX(mCurrentPosition[0]);
                iv_sign.setTranslationY(mCurrentPosition[1]);
            }
        });
        animator.start();
    }

    @Override
    public void onItemClick(final GoodsAdatper.BaseViewHolder viewHolder, final int position, final SectionGoodsEntity.DataEntity.GoodsEntity goodsEntity) {
        if (viewHolder instanceof GoodsListViewHolder) {
            //region 添加按钮点击
            ((GoodsListViewHolder) viewHolder).iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: 2016/11/12 是否已经登录处理

                    Map<String,String> paramsMap =new HashMap<String, String>();
                    paramsMap.put("goodsId",goodsEntity.getId());
                    paramsMap.put("storeId",AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.STORE_ID));
                    paramsMap.put("type","add");
                    paramsMap.put(ApiConstant.USER_ID,AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_ID));
                    paramsMap.put(ApiConstant.GOODS_NUM,"1");
//                    paramsMap.put("fromId","52");
                    paramsMap.put(ApiConstant.TOKEN,Utils.getRequestToken(paramsMap));
                    Call<AddGoodsEntity> addCall = RetrofitManager.getInstance().addGoods(ApiConstant.ver, Utils.getTimeStampString(), paramsMap);
                    addCall.enqueue(new Callback<AddGoodsEntity>() {
                        @Override
                        public void onResponse(Call<AddGoodsEntity> call, Response<AddGoodsEntity> response) {
                            if (response.isSuccessful()) {

                            }
                        }

                        @Override
                        public void onFailure(Call<AddGoodsEntity> call, Throwable t) {

                        }
                    });

                    startAnimToShoppingCar(v);


                    if (!FunctionMonitor.getInstance().showFunction) {
                        FunctionMonitor.getInstance().issueFunctionMonitor(MonitorEnum.FunctionEnum.SHOW_FUNCTION);
                    }
                    ShoppingCarNumMonitor.getInstance().issueShoppingCarNumMonitor(MonitorEnum.ShoppingCarNumEnum.ONE_BY_ONE, 0, true);

                    List<ShoppingCartGoods> queryResult = DBHelper.getInstance(mContext).getShoppingCartGoodsDao().queryBuilder().where(ShoppingCartGoodsDao.Properties.Id.eq(goodsEntity.getId())).build().list();
                    if (queryResult == null || queryResult.size() == 0) {//没有该商品进行insert
                        ShoppingCartGoods shoppingCartGoods = new ShoppingCartGoods();
                        shoppingCartGoods.setAccount(Integer.valueOf("1"));
                        shoppingCartGoods.setEndtime(goodsEntity.getEndtime());
                        shoppingCartGoods.setGoods_type(goodsEntity.getGoods_type());
                        shoppingCartGoods.setIcon(goodsEntity.getIcon());
                        shoppingCartGoods.setId(Integer.valueOf(goodsEntity.getId()));
                        shoppingCartGoods.setMarket_price(goodsEntity.getMarket_price());
                        shoppingCartGoods.setNewimg(goodsEntity.getNewimg());
                        shoppingCartGoods.setPhonetips(goodsEntity.getPhonetips());
                        shoppingCartGoods.setPrice(goodsEntity.getPrice());
                        shoppingCartGoods.setShow(goodsEntity.getShow());
                        shoppingCartGoods.setSold_num(Integer.valueOf(goodsEntity.getSold_num()));
                        shoppingCartGoods.setStarttime(goodsEntity.getStarttime());
                        shoppingCartGoods.setStore_num(Integer.valueOf(goodsEntity.getStore_nums()));
                        shoppingCartGoods.setStore_nums(Integer.valueOf(goodsEntity.getStore_nums()));
                        shoppingCartGoods.setTipsimg(goodsEntity.getTipsimg());
                        shoppingCartGoods.setTitle(goodsEntity.getTitle());
                        shoppingCartGoods.setUnit(goodsEntity.getUnit());
                        shoppingCartGoods.setUserid("1609759");//// TODO: 通过user表查出uid
                        DBHelper.getInstance(mContext).getShoppingCartGoodsDao().insertOrReplace(shoppingCartGoods);
                    } else if (queryResult.size() == 1) {//查到db中有该商品 进行update
                        ShoppingCartGoods cartGoods = queryResult.get(0);
                        cartGoods.setAccount(cartGoods.getAccount() + 1);
                        DBHelper.getInstance(mContext).getShoppingCartGoodsDao().updateInTx(cartGoods);
                    } else {
                        new IllegalArgumentException("数据库中同一商品有俩条或以上记录");
                    }


                    ((GoodsListViewHolder) viewHolder).iv_delete.setVisibility(View.VISIBLE);
                    ((GoodsListViewHolder) viewHolder).iv_rectange.setVisibility(View.VISIBLE);
                    String value = ((GoodsListViewHolder) viewHolder).iv_rectange.getText().toString();
                    if (value.equals("0") || value.isEmpty()) {
                        ((GoodsListViewHolder) viewHolder).iv_rectange.setText("1");
                    } else {
                        ((GoodsListViewHolder) viewHolder).iv_rectange.setText(String.valueOf(Integer.valueOf(value) + 1));
                    }
                }
            });
            //endregion
            listener.showFunctions();
            //region 删除按钮点击
            ((GoodsListViewHolder)viewHolder).iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShoppingCarNumMonitor.getInstance().issueShoppingCarNumMonitor(MonitorEnum.ShoppingCarNumEnum.ONE_BY_ONE, 0, false);


                    List<ShoppingCartGoods>  queryResult = DBHelper.getInstance(mContext).getShoppingCartGoodsDao().queryBuilder().where(ShoppingCartGoodsDao.Properties.Id.eq(goodsEntity.getId())).build().list();
                    if (queryResult == null || queryResult.size() == 0) {//没有该商品进行insert
                        new IllegalArgumentException("db中没有该商品");
                    } else if (queryResult.size() == 1) {//查到db中有该商品 进行update
                        ShoppingCartGoods cartGoods = queryResult.get(0);
                        int updateValue = cartGoods.getAccount() - 1;
                        if (updateValue<0)
                            throw  new IllegalArgumentException("商品数为负数");
                        cartGoods.setAccount(updateValue);
                        DBHelper.getInstance(mContext).getShoppingCartGoodsDao().updateInTx(cartGoods);
                    }else {
                        new IllegalArgumentException("数据库中同一商品有俩条或以上记录");
                    }

                    ((GoodsListViewHolder)viewHolder).iv_delete.setVisibility(View.VISIBLE);
                    ((GoodsListViewHolder)viewHolder).iv_rectange.setVisibility(View.VISIBLE);
                    String value = ((GoodsListViewHolder)viewHolder).iv_rectange.getText().toString();
                    if (value.equals("1") || value.isEmpty()) {
                        ((GoodsListViewHolder)viewHolder).iv_rectange.setText("0");
                        ((GoodsListViewHolder)viewHolder).iv_delete.setVisibility(View.INVISIBLE);
                        ((GoodsListViewHolder)viewHolder).iv_rectange.setVisibility(View.INVISIBLE);
                    } else {
                        ((GoodsListViewHolder)viewHolder).iv_rectange.setText(String.valueOf(Integer.valueOf(value) - 1));
                    }
                }
            });
            //endregion
        }

        if (viewHolder instanceof GoodsGridViewHolder) {

        }
    }

    public static class ItemOnClickListener implements View.OnClickListener {
        SectionGoodsEntity.DataEntity.GoodsEntity goodsEntity;
        Context mContext;

        public ItemOnClickListener(Context mContext, SectionGoodsEntity.DataEntity.GoodsEntity position) {
            this.goodsEntity = position;
            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            StartActivity.startGoodsInfoActivity(mContext, goodsEntity);
        }
    }

    /**
     * 呼应mainfragment观察者模式该变该childfragment中adapger布局
     */
    public void layoutTransition(MonitorEnum.TransitionEnum eenum) {
        switch (eenum) {
            case LIST_LAYOUT:
//                if(goodsAdatper==null)
//                    return;
                goodsAdatper.setType(GoodsAdatper.ITEM_LAYOUT_LIST);
                list.setLayoutManager(new LinearLayoutManager(getActivity()));
                goodsAdatper.notifyDataSetChanged();
                startAnimation(list, R.anim.fade_out);
                break;

            case GRID_LAYOUT:
//                if(goodsAdatper==null)
//                    return;
                goodsAdatper.setType(GoodsAdatper.ITEM_LAYOUT_GRID);
                list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                goodsAdatper.notifyDataSetChanged();
                startAnimation(list, R.anim.fade_in);
                break;
        }
    }

    /**
     * recyclerview 布局切换动画
     *
     * @param rv
     * @param anim
     */
    private void startAnimation(RecyclerView rv, int anim) {
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(), anim));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        rv.setLayoutAnimation(lac);
        rv.startLayoutAnimation();
    }
}
