package com.xuxian.new_market.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbPreferenceUtils;
import com.xuxian.new_market.R;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.listener.OnShopCarItemClickListener;
import com.xuxian.new_market.mvp.entity.ShoppingCarInfo;
import com.xuxian.new_market.mvp.ui.adapter.ShoppingCarGoodsAdapter;
import com.xuxian.new_market.mvp.ui.fragment.base.BaseFragment;
import com.xuxian.new_market.mvp.ui.widght.ActivityStateView;
import com.xuxian.new_market.mvp.ui.widght.ShoppingCarListView;
import com.xuxian.new_market.respository.network.RetrofitManager;
import com.xuxian.new_market.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by youarenotin on 2016/11/8.
 */

public class ShoppingCarFragment extends BaseFragment implements OnShopCarItemClickListener {

    private TextView orderPrice;
    private ImageView iv_navigate;
    private ShoppingCarListView list;
    private ActivityStateView emptyView;
    private RelativeLayout rl_shopping_cart_delete;
    private RelativeLayout rl_shopping_cart_settlement;
    private TextView tv_userinfo;
    private Context mContext;
    private LinearLayout ll_default_shopping_car_img;
    private ShoppingCarGoodsAdapter adapter;
    private boolean isEdit=false;
    private CheckBox cb_all_selected;
    private Button btn_delete;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fra_shoppingcarfragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        orderPrice = (TextView) view.findViewById(R.id.shopping_cart_always_price);
        iv_navigate = (ImageView) view.findViewById(R.id.iv_shopping_cart_store_jiantou);
        list = (ShoppingCarListView) view.findViewById(R.id.list);
        emptyView = (ActivityStateView) view.findViewById(R.id.emptyview_state);
        rl_shopping_cart_delete = (RelativeLayout) view.findViewById(R.id.rl_shopping_cart_delete);
        rl_shopping_cart_settlement = (RelativeLayout) view.findViewById(R.id.rl_shopping_cart_settlement);
        tv_userinfo = (TextView) view.findViewById(R.id.tv_userinfo);
        ll_default_shopping_car_img = (LinearLayout) view.findViewById(R.id.ll_default_shopping_car_img);
        cb_all_selected = (CheckBox) view.findViewById(R.id.cb_all_selected);
        btn_delete = (Button) view.findViewById(R.id.btn_shopping_cart_delete);
        if (isEdit) {
            iv_navigate.setBackgroundResource(R.drawable.cancel_icon);
        }
        else {
            iv_navigate.setBackgroundResource(R.drawable.edit_icon);
        }
    }

    @Override
    protected void initEvent() {
        tv_userinfo.setText(AbPreferenceUtils.loadPrefString(mContext, ApiConstant.USER_PHONE,"")+"\n"+AbPreferenceUtils.loadPrefString(mContext,ApiConstant.store_address,""));
        emptyView.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
        emptyView.setLoadingPromt("购物车同步中....");
        emptyView.setVisibility(View.VISIBLE);

        Call<ShoppingCarInfo> initCall = initData();
        initCall.enqueue(new Callback<ShoppingCarInfo>() {
            @Override
            public void onResponse(Call<ShoppingCarInfo> call, Response<ShoppingCarInfo> response) {
                if (!response.isSuccessful()) {
                    emptyView.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                    return ;
                }
                emptyView.setVisibility(View.GONE);
                ll_default_shopping_car_img.setVisibility(View.GONE);
                adapter = new ShoppingCarGoodsAdapter(mContext);
                adapter.setData(response.body());
                adapter.setOnItemClickListener(ShoppingCarFragment.this);
                list.setAdapter(adapter);
                list.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ShoppingCarInfo> call, Throwable t) {

            }
        });

        iv_navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit=!isEdit;
                adapter.setCheckVisible(isEdit);
                if (isEdit) {
                    iv_navigate.setBackgroundResource(R.drawable.cancel_icon);
                    rl_shopping_cart_settlement.setVisibility(View.GONE);
                    rl_shopping_cart_delete.setVisibility(View.VISIBLE);
                }
                else {
                    iv_navigate.setBackgroundResource(R.drawable.edit_icon);
                    rl_shopping_cart_settlement.setVisibility(View.VISIBLE);
                    rl_shopping_cart_delete.setVisibility(View.GONE);
                }
            }
        });

        cb_all_selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adapter.setAllChecked(isChecked);
                }else {
                    adapter.setAllChecked(isChecked);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.deleteCheckedGoods();
            }
        });
    }

    private Call<ShoppingCarInfo> initData() {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("store_id", AbPreferenceUtils.loadPrefString(mContext, ApiConstant.STORE_ID,"0"));
        paramMap.put("user_id",AbPreferenceUtils.loadPrefString(mContext,ApiConstant.USER_ID,"0"));
        paramMap.put("city_id",AbPreferenceUtils.loadPrefString(mContext,ApiConstant.CITY_ID,"0"));
        paramMap.put("token", Utils.getRequestToken(paramMap));
        return  RetrofitManager.getInstance().getShoppingCarInfo(ApiConstant.ver, Utils.getTimeStampString(), paramMap);
    }

    /**
     * 购物车 + - 抽出的接口
     * @param v
     * @param tv_count
     * @param position
     * @param entity
     */
    @Override
    public void OnItemClick(View v, TextView tv_count, int position, ShoppingCarInfo.DataEntity.GoodssectionsEntity.GoodslistEntity entity) {
        String currentCount = tv_count.getText().toString();
        Map<String,String> paramsMap = new HashMap<>();
        switch (v.getId()) {
            case R.id.btn_reduce:
                int newDelCount = Integer.valueOf(currentCount) - 1;
                if (newDelCount<=0) newDelCount=0;
                paramsMap.put("goodsNum", String.valueOf(newDelCount));
                paramsMap.put("goodsId",entity.getId());
                paramsMap.put("storeId",AbPreferenceUtils.loadPrefString(getActivity(),"storeId","0"));
                paramsMap.put("type","del");
                paramsMap.put(ApiConstant.USER_ID,AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_ID,"0"));
                paramsMap.put("fromId","99");
                paramsMap.put("token",AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_TOKEN,"0"));
                final Call<ShoppingCarInfo> reduceCall = RetrofitManager.getInstance().navigateShoppingCar(ApiConstant.ver, Utils.getTimeStampString(), paramsMap);
                reduceCall.enqueue(new Callback<ShoppingCarInfo>() {
                    @Override
                    public void onResponse(Call<ShoppingCarInfo> call, Response<ShoppingCarInfo> response) {

                    }

                    @Override
                    public void onFailure(Call<ShoppingCarInfo> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_increase:
                int newAddCount = Integer.valueOf(currentCount) + 1;
                paramsMap.put("goodsNum", String.valueOf(newAddCount));
                paramsMap.put("goodsId",entity.getId());
                paramsMap.put("storeId",AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.STORE_ID,"0"));
                paramsMap.put("type","add");
                paramsMap.put(ApiConstant.USER_ID,AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_ID,"0"));
                paramsMap.put("fromId","99");
                paramsMap.put("token",AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_TOKEN,"0"));
                final Call<ShoppingCarInfo> addeCall = RetrofitManager.getInstance().navigateShoppingCar(ApiConstant.ver, Utils.getTimeStampString(), paramsMap);
                addeCall.enqueue(new Callback<ShoppingCarInfo>() {
                    @Override
                    public void onResponse(Call<ShoppingCarInfo> call, Response<ShoppingCarInfo> response) {
                        if (!response.isSuccessful()) {

                        }else {
                            adapter.setData(response.body());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ShoppingCarInfo> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
