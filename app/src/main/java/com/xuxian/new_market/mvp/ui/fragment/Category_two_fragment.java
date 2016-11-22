package com.xuxian.new_market.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.xuxian.new_market.R;
import com.xuxian.new_market.mvp.entity.CategoryInfo;
import com.xuxian.new_market.mvp.ui.adapter.NavGridViewAdapter;
import com.xuxian.new_market.mvp.ui.fragment.base.BaseFragment;
import com.xuxian.new_market.mvp.ui.widght.ActivityStateView;
import com.xuxian.new_market.respository.network.RetrofitManager;
import com.xuxian.new_market.utils.NetUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by youarenotin on 2016/10/21.
 */

public class Category_two_fragment extends BaseFragment {

    private GridView gridview;

    private  static Category_two_fragment instance;
    private NavGridViewAdapter adapter;
    private ActivityStateView emptyView;

    public static Category_two_fragment getInstance(){
        if (instance == null) {
            instance=new Category_two_fragment();
        }
        return instance;
    }

    private Category_two_fragment( ) {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        gridview = (GridView) view.findViewById(R.id.nav_gridview);
        emptyView = (ActivityStateView) view.findViewById(R.id.emptyview_state);
        adapter = new NavGridViewAdapter(getActivity());
        gridview.setAdapter(adapter);
        requestData();
        adapter.setOnItemOnClickListener(new ItemOnClickListener());
    }

    private void requestData() {
        Call<CategoryInfo> call = RetrofitManager.getInstance().getInfos("188", "2016083101", System.currentTimeMillis() + "");
        call.enqueue(new Callback<CategoryInfo>() {
            @Override
            public void onResponse(Call<CategoryInfo> call, Response<CategoryInfo> response) {
                if (!response.isSuccessful()){
                    emptyView.setState(ActivityStateView.ACTIVITY_STATE_NODATA);
                    emptyView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            emptyView.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
                            requestData();
                        }
                    });
                }
                else if (response.isSuccessful() && response.body().getStatus().getCode().equals("0") && response.body().getData().get(0).getName().equals("轻食")){
                    List<CategoryInfo.DataEntity.DataEntity1> datas = response.body().getData().get(0).getData();
                    adapter.setData(datas);
                    emptyView.setVisibility(View.VISIBLE);
                }else {
                    emptyView.setState(ActivityStateView.ACTIVITY_STATE_NODATA);
                    emptyView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            emptyView.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
                            requestData();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                emptyView.setState(ActivityStateView.ACTIVITY_STATE_NETWORK_ERROR);
                emptyView.setNetErrorText("网络错误,请点击重试!");
                emptyView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emptyView.setState(ActivityStateView.ACTIVITY_STATE_LOADING);
                        requestData();
                    }
                });
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    class ItemOnClickListener implements View.OnClickListener{

        public ItemOnClickListener() {
        }

        @Override
        public void onClick(View v) {

        }
    }
}
