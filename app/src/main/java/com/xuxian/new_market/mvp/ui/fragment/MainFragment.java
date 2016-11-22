package com.xuxian.new_market.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.SlideInAnimation;
import com.easyandroidanimations.library.SlideOutAnimation;
import com.xuxian.new_market.R;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.listener.Monitor.FunctionMonitor;
import com.xuxian.new_market.listener.Monitor.MonitorEnum;
import com.xuxian.new_market.listener.Monitor.TransitionMonitor;
import com.xuxian.new_market.listener.OnRecyclerTouchScrollListener;
import com.xuxian.new_market.mvp.entity.IndexConfig;
import com.xuxian.new_market.mvp.entity.IndexConfig.DataEntity.SectionInfoEntity;
import com.xuxian.new_market.mvp.ui.adapter.viewpageradapter.IndexSlidePageAdapter;
import com.xuxian.new_market.mvp.ui.fragment.base.BaseFragment;
import com.xuxian.new_market.mvp.ui.widght.ActivityStateView;
import com.xuxian.new_market.mvp.ui.widght.SlideTabLayout;
import com.xuxian.new_market.respository.network.RetrofitManager;
import com.xuxian.new_market.utils.ImageLoad;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by youarenotin on 16/9/6.
 */
public class MainFragment extends BaseFragment implements OnRecyclerTouchScrollListener {
    ViewPager mChildFragmentViewPager;

    private int disY = 0;
    LinearLayout buttons;

    private RelativeLayout container;
    private int minSlop;
    private boolean isSlideing;
    private float downY;
    private float moveY;
    private boolean showFuntions = true;
    int screenHight;
    int minActionDis = -1;
    int totalScrollDis = 0;
    String scrollDirection = "down";
    DrawerLayout mDrawerLayout;
    private ImageView iv_classify;
    private SlideTabLayout mSlideTabLayout;
    private LinearLayout title;
    private List<Fragment> mSectionFragments = new ArrayList<>();
    private List<String> mSectionTitles = new ArrayList<>();
    private IndexConfig.DataEntity mIndexData;
    private ActivityStateView emptyView;
    private LinearLayout mSlideContainer;
    private SlideInAnimation slideInAnim;
    private SlideOutAnimation slideOutAnim;
    private ImageView iv_transition;
    private ImageView iv_sign;

    public void setmDrawerLayout(DrawerLayout mDrawerLayout) {
        this.mDrawerLayout = mDrawerLayout;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fra_main, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        minSlop = ViewConfiguration.getTouchSlop();
        initView(view);

        ViewGroup.LayoutParams params = title.getLayoutParams();
        screenHight = AbScreenUtils.getScreenHeight(getActivity());
        minActionDis = screenHight / 4;
        params.height = screenHight / 8;
        title.setLayoutParams(params);
        requestData();

    }

    private void requestData() {
        Call<IndexConfig> call = RetrofitManager.getInstance().getIndexConfig(ApiConstant.ver, String.valueOf(System.currentTimeMillis()), "110000", "882", AbPreferenceUtils.loadPrefString(getActivity(),ApiConstant.USER_ID));
        call.enqueue(new Callback<IndexConfig>() {
            @Override
            public void onResponse(Call<IndexConfig> call, Response<IndexConfig> response) {
                if (!response.isSuccessful()) {

                } else if (response.body().getStatus().getCode().equals("0")) {
                    configClassify(response.body().getData().getConfig_img().getClassify());
                    emptyView.setVisibility(View.GONE);
                    mChildFragmentViewPager.setVisibility(View.VISIBLE);
                    mSlideContainer.setVisibility(View.VISIBLE);
                    mIndexData = response.body().getData();
                    IndexSlidePageAdapter adapter = new IndexSlidePageAdapter(getChildFragmentManager());
                    for (SectionInfoEntity entity : mIndexData.getSection_info()) {
                        final ChildFragment childFragment = new ChildFragment(entity.getId());

                        childFragment.setIv_sign(iv_sign);
                        childFragment.setContainer(container);
                        childFragment.setOnScrollListener(MainFragment.this);
                        mSectionFragments.add(childFragment);
                        mSectionTitles.add(entity.getName());
                    }
                    adapter.setFragments(mSectionFragments);
                    adapter.setTitles(mSectionTitles);
                    mChildFragmentViewPager.setAdapter(adapter);
                    mChildFragmentViewPager.setOffscreenPageLimit(mSectionTitles.size());
                    mSlideTabLayout.setViewPager(mChildFragmentViewPager);
                } else {

                }
            }

            @Override
            public void onFailure(Call<IndexConfig> call, Throwable t) {

            }
        });
    }

    private void configClassify(IndexConfig.DataEntity.ConfigImgEntity.ClassifyEntity classify) {
        if (!classify.getImg().isEmpty() && classify.getImg() != null) {
            ImageLoad.loadImage(getActivity(), classify.getImg(), iv_classify, DiskCacheStrategy.ALL);
        }
    }

    public void setButtons(LinearLayout rl) {
        this.buttons = rl;
    }

    private void initView(View view) {
        title = (LinearLayout) view.findViewById(R.id.titleBar);
        iv_sign= (ImageView) view.findViewById(R.id.iv_sign);
        mChildFragmentViewPager = (ViewPager) view.findViewById(R.id.fragment_containerViewPager);
        mSlideContainer = (LinearLayout) view.findViewById(R.id.slideContainer);
        iv_classify = (ImageView) view.findViewById(R.id.iv_classify);
        mSlideTabLayout = (SlideTabLayout) view.findViewById(R.id.slideTabLayout);
        container = (RelativeLayout) view.findViewById(R.id.container);
        emptyView = (ActivityStateView) view.findViewById(R.id.emptyview_state);
        iv_transition = (ImageView) view.findViewById(R.id.iv_titleBar_trasition);
        String value = AbPreferenceUtils.loadPrefString(getActivity(), ApiConstant.goodslayout, "0");
        if (value.equals("0")) {
            iv_transition.setBackgroundResource(R.drawable.place_listview);
        } else {
            iv_transition.setBackgroundResource(R.drawable.place_gridview);
        }

        emptyView.setVisibility(View.VISIBLE);
        emptyView.setState(ActivityStateView.ACTIVITY_STATE_LOADING);


    }

    @Override
    protected void initEvent() {


        iv_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });


        iv_transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionMonitor.getInstance().issueTransitionMonitor(null);
                if (TransitionMonitor.getInstance().mLayout == MonitorEnum.TransitionEnum.LIST_LAYOUT) {
                    iv_transition.setBackgroundResource(R.drawable.place_listview);
                    iv_transition.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in));
                }
                else {
                    iv_transition.setBackgroundResource(R.drawable.place_gridview);
                    iv_transition.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in));

                }
            }
        });
    }

    int dY;
    int scorllState;
    int flingDy;
    /**
     * Callback method to be invoked when the RecyclerView has been scrolled. This will be
     * called after the scroll has completed.
     * <p>
     * This callback will also be called if visible item range changes after a layout
     * calculation. In that case, dx and dy will be 0.
     *
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx The amount of horizontal scroll.
     * @param dy The amount of vertical scroll.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (scorllState==RecyclerView.SCROLL_STATE_SETTLING && dy<0){
            this.dY+=Math.abs(dy);
            if (dY+totalScrollDis>minActionDis){//dy<0
                int ptop = container.getPaddingTop()-dy;
                if (ptop>=0)
                    ptop=0;
                container.setPadding(container.getPaddingLeft(),ptop,container.getPaddingRight(),container.getPaddingBottom());
                float alpha = 1 - (float) ((1.0 * container.getPaddingTop() / (-screenHight / 8)));
                if (alpha <= 0.3)
                    alpha = (float) 0.3;
                title.setAlpha(alpha);
            }
        }
        if (scorllState==RecyclerView.SCROLL_STATE_SETTLING && dy>0){
            this.dY+=Math.abs(dy);

            if (dY+totalScrollDis>minActionDis){//dy>0
                int ptop = container.getPaddingTop()-(dy);
                if (ptop <= (-screenHight / 8))
                    ptop=-screenHight / 8;
                container.setPadding(container.getPaddingLeft(),ptop,container.getPaddingRight(),container.getPaddingBottom());
                float alpha = 1 - (float) ((1.0 * container.getPaddingTop() / (-screenHight / 8)));
                if (alpha <= 0.3)
                    alpha = (float) 0.3;
                title.setAlpha(alpha);
            }
        }
//        if (dy <0) {//下滑
//            disY+=Math.abs(dy);
//            totalScrollDis +=Math.abs(dy);
//            scrollDirection = "down";
//            if (totalScrollDis > minActionDis) {
//                if (container.getPaddingTop() < 0) {
//                    int paddingTop = container.getPaddingTop() +disY;
//                    if (paddingTop >= 0)
//                        paddingTop = 0;
//                    container.setPadding(container.getPaddingLeft(), paddingTop, container.getPaddingRight(), container.getPaddingBottom());
//                    float alpha = 1 - (float) ((1.0 * container.getPaddingTop() / (-screenHight / 8)));
//                    if (alpha <= 0.3)
//                        alpha = (float) 0.3;
//                    title.setAlpha(alpha);
//                }
//            }
//        } else if (dy > 0) {//上滑
////            disY = (int) (moveY - downY);
////            disY = disY * 5 / 10;
//            disY+=Math.abs(dy);
//            scrollDirection = "up";
//            if (container.getPaddingTop() >= (-screenHight / 8)) {
//                int paddingTop = container.getPaddingTop() - disY;
//                if (paddingTop <= -screenHight / 8) {
//                    paddingTop = -screenHight / 8;
//                    totalScrollDis = 0;
//                }
//                container.setPadding(container.getPaddingLeft(), paddingTop, container.getPaddingRight(), container.getPaddingBottom());
//                float alpha = 1 - (float) ((1.0 * container.getPaddingTop() / (-screenHight / 8)));
//                if (alpha <= 0.3)
//                    alpha = (float) 0.3;
//                title.setAlpha(alpha);
//            }
//        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
        this.scorllState=scrollState;
        if (scrollDirection.equals("down") && scrollState == RecyclerView.SCROLL_STATE_IDLE && !FunctionMonitor.getInstance().showFunction) {
//            if (slideOutAnim!=null && slideOutAnim.getAnimatorSet().isRunning()) {
//                slideOutAnim.getAnimatorSet().end();
//            }
//            slideInAnim = new SlideInAnimation(buttons).setListener(new AnimationListener() {
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    totalScrollDis = 0;
//                }
//            }).setDuration(600).setDirection(4);
//            slideInAnim.animate();
            FunctionMonitor.getInstance().issueFunctionMonitor(MonitorEnum.FunctionEnum.SHOW_FUNCTION);
            this.dY=0;
        }
        if (scrollDirection.equals("up") && scrollState == RecyclerView.SCROLL_STATE_IDLE && FunctionMonitor.getInstance().showFunction) {
//            if (slideInAnim!=null && slideInAnim.getAnimatorSet().isRunning()) {
//                slideInAnim.getAnimatorSet().end();
//            }
//            slideOutAnim = new SlideOutAnimation(buttons).setListener(new AnimationListener() {
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                }
//            }).setDuration(600).setDirection(4);
//            slideOutAnim.animate();
            FunctionMonitor.getInstance().issueFunctionMonitor(MonitorEnum.FunctionEnum.HIDE_FUNCTION);
            this.dY=0;
        }
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            this.dY=0;
        }
    }

    boolean isFirst = true;

    /**
     * 每个子fragment中抽出的ontouch
     * @param v
     * @param ev
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dY=0;
                downY = ev.getRawY();
                return false;
            case MotionEvent.ACTION_MOVE:
                if (isFirst) {
                    isFirst = false;
                    downY = ev.getRawY();
                }
                disY = 0;
                moveY = ev.getRawY();
                if (moveY - downY > minSlop) {//下滑
                    disY = (int) (moveY - downY);
                    totalScrollDis += disY;
                    scrollDirection = "down";
                    if (totalScrollDis > minActionDis) {
                        disY = disY * 10 / 10;
                        if (container.getPaddingTop() < 0) {
                            int paddingTop = container.getPaddingTop() + disY;
                            if (paddingTop >= 0)
                                paddingTop = 0;
                            container.setPadding(container.getPaddingLeft(), paddingTop, container.getPaddingRight(), container.getPaddingBottom());
                            float alpha = 1 - (float) ((1.0 * container.getPaddingTop() / (-screenHight / 8)));
                            if (alpha <= 0.3)
                                alpha = (float) 0.3;
                            title.setAlpha(alpha);
                        }
                    }
                } else if (downY - moveY > minSlop) {//上滑
                    disY = (int) (moveY - downY);
                    disY = disY * 5 / 10;
                    scrollDirection = "up";
                    if (container.getPaddingTop() >= (-screenHight / 8)) {
                        int paddingTop = container.getPaddingTop() + disY;
                        if (paddingTop <= -screenHight / 8) {
                            paddingTop = -screenHight / 8;
                            totalScrollDis = 0;
                        }
                        container.setPadding(container.getPaddingLeft(), paddingTop, container.getPaddingRight(), container.getPaddingBottom());
                        float alpha = 1 - (float) ((1.0 * container.getPaddingTop() / (-screenHight / 8)));
                        if (alpha <= 0.3)
                            alpha = (float) 0.3;
                        title.setAlpha(alpha);
                    }
                }
                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                downY = 0;
                moveY = 0;
                disY = 0;
                isFirst = true;
                break;
        }

        return false;
    }

    @Override
    public void showFunctions() {
        if (!showFuntions) {
            slideInAnim = new SlideInAnimation(buttons).setListener(new AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    showFuntions = true;
                    totalScrollDis = 0;
                }
            }).setDuration(600).setDirection(4);
            slideInAnim.animate();
        }
    }

    public void scrollToTop() {

    }
}

