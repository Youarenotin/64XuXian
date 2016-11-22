package com.xuxian.new_market.mvp.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.http.AbRequestParams;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.ab.util.AbToastUtil;
import com.ab.util.AbViewUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.market.new_xuxian.greendao.ShoppingCartGoods;
import com.nineoldandroids.view.ViewHelper;
import com.xuxian.new_market.R;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.listener.Monitor.MonitorEnum;
import com.xuxian.new_market.listener.Monitor.ShoppingCarNumMonitor;
import com.xuxian.new_market.listener.ScrollViewListener.ObservableScrollViewCallbacks;
import com.xuxian.new_market.listener.ScrollViewListener.ScrollState;
import com.xuxian.new_market.listener.ScrollViewListener.ScrollUtils;
import com.xuxian.new_market.mvp.entity.GoodsInfo;
import com.xuxian.new_market.mvp.entity.SectionGoodsEntity;
import com.xuxian.new_market.mvp.ui.activity.base.BaseActivity;
import com.xuxian.new_market.mvp.ui.widght.ObservableScrollView;
import com.xuxian.new_market.respository.db.DBHelper;
import com.xuxian.new_market.respository.network.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by youarenotin on 2016/10/31.
 */

public class GoodsInfoActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    private ObservableScrollView scrollView;
    private Toolbar mToolbarView;
    private int mParallaxImageHeight;
    private ImageView mImageView;
    private SectionGoodsEntity.DataEntity.GoodsEntity goodsEntity;
    private WebView webView;
    private ImageView iv_tips;
    private TextView tv_main_name;
    private TextView tv_title;
    private TextView tv_mem_price;
    private TextView tv_nomem_price;
    private TextView tv_unit;
    private int screenWidth;
    private ImageView mImageBeta;
    private View back;
    private View share;
    private Call<GoodsInfo> call;
    private ImageView iv_add;
    private ImageView iv_reduce;
    private TextView tv_num;
    private Button btn_addShopCar;
    private TextView toolBar_title;
    private String mTitleName;

    @Override
    protected void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbToastUtil.showToast(GoodsInfoActivity.this, "分享");
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(tv_num.getText().toString());
                tv_num.setText("" + (num + 1));
            }
        });
        iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(tv_num.getText().toString());
                if (num == 1)
                    return;
                tv_num.setText("" + (num - 1));
            }
        });
        btn_addShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 发出个更新购物车数量的信号
                ShoppingCartGoods shoppingCartGoods = new ShoppingCartGoods();
                shoppingCartGoods.setAccount(Integer.valueOf(tv_num.getText().toString()));
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
                DBHelper.getInstance(GoodsInfoActivity.this).getShoppingCartGoodsDao().insertOrReplaceInTx(shoppingCartGoods);
                ShoppingCarNumMonitor.getInstance().issueShoppingCarNumMonitor(MonitorEnum.ShoppingCarNumEnum.NOT_ONE_BY_ONE,shoppingCartGoods.getAccount(),false);
                onBackPressed();
            }
        });
    }

    @Override
    protected void initData() {
        Bundle extras = getIntent().getBundleExtra("intent_bundle");
        goodsEntity = (SectionGoodsEntity.DataEntity.GoodsEntity) extras.get("intent_object");

        AbRequestParams params = new AbRequestParams();
        params.put("goodsId", goodsEntity.getId());
        params.put("storeId", "882");
        params.put("userId", AbPreferenceUtils.loadPrefString(this,ApiConstant.USER_ID,"0"));
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("goodsId", goodsEntity.getId());
        paramsMap.put("storeId", "882");
        paramsMap.put("userId", AbPreferenceUtils.loadPrefString(this,ApiConstant.USER_ID,"0"));
        paramsMap.put("token", params.getMd5());
        call = RetrofitManager.getInstance().getGoodsInfo(ApiConstant.ver, "" + System.currentTimeMillis(), paramsMap);
        call.enqueue(new Callback<GoodsInfo>() {
            @Override
            public void onResponse(Call<GoodsInfo> call, Response<GoodsInfo> response) {

                if (!response.isSuccessful() || !(response.body().getStatus().getCode().equals("0"))) {
                    return;
                }
                GoodsInfo.DataEntity data = response.body().getData();
                Glide.with(GoodsInfoActivity.this).load(data.getGoods().getTipsimg()).error(R.drawable.default_tipsimg).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.default_tipsimg).into(iv_tips);
                Glide.with(GoodsInfoActivity.this).load(data.getGoods().getIcon()).error(R.drawable.default_newimg).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.default_newimg).into(mImageView);
                if (data.getGoods().getShow_imgs()!=null && data.getGoods().getShow_imgs().size()>0) {
                    Glide.with(GoodsInfoActivity.this).load(data.getGoods().getShow_imgs().get(0)).error(R.drawable.default_newimg).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.default_newimg).into(mImageBeta);
                }
                tv_main_name.setText(data.getGoods().getMain_name());
                mTitleName=data.getGoods().getMain_name();
                tv_title.setText(data.getGoods().getTitle());
                tv_mem_price.setText(data.getGoods().getPrice());
                tv_nomem_price.setText(data.getGoods().getPrice_info());
                tv_unit.setText("规格约: " + data.getGoods().getUnit());
                webView.loadUrl(data.getGoods().getDetails2());
            }

            @Override
            public void onFailure(Call<GoodsInfo> call, Throwable t) {

            }
        });

    }

    @Override
    protected void initViews() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        btn_addShopCar = (Button) findViewById(R.id.btn_addShopCar);
        back = findViewById(R.id.back);
        share = findViewById(R.id.share);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_main_name = (TextView) findViewById(R.id.tv_main_name);
        tv_mem_price = (TextView) findViewById(R.id.tv_mem_price);
        toolBar_title = (TextView) findViewById(R.id.tv_toolbartitle);
        tv_nomem_price = (TextView) findViewById(R.id.tv_nomem_price);
        tv_unit = (TextView) findViewById(R.id.tv_unit);
        tv_title = (TextView) findViewById(R.id.tv_title);
        mImageView = (ImageView) findViewById(R.id.image);
        mImageBeta = (ImageView) findViewById(R.id.imagebeta);
        View anchor =  findViewById(R.id.anchor);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_reduce = (ImageView) findViewById(R.id.iv_reduce);
        screenWidth = AbScreenUtils.getScreenWidth(this);
        AbViewUtil.setViewWH(mImageView, screenWidth, screenWidth * 3 / 4);
        AbViewUtil.setViewWH(mImageBeta, screenWidth, screenWidth * 3 / 4);
        AbViewUtil.setViewWH(anchor, screenWidth, screenWidth * 3 / 4);
        iv_tips = (ImageView) findViewById(R.id.iv_tips);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        scrollView.setScaleView(mImageView);
        scrollView.setScaleViewBeta(mImageBeta);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        scrollView.setScrollViewCallbacks(this);
        mToolbarView = (Toolbar) findViewById(R.id.toolbar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));
        mToolbarView.setTitle("");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarView.setElevation(5 * getResources().getDisplayMetrics().density);
        }

        mParallaxImageHeight = (int) (screenWidth * 3 / 4 - getResources().getDisplayMetrics().density * 56);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goosinfo;
    }

    @Override
    public void appCompatStatusBar() {

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.white);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, scrollY/2 );
        if (alpha>0.9)
            toolBar_title.setText(mTitleName);
        else {
            toolBar_title.setText("");
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!call.isCanceled())
            call.cancel();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
