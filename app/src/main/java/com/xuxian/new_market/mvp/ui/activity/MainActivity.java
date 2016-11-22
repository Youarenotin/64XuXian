package com.xuxian.new_market.mvp.ui.activity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbImageUtil;
import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.SlideInAnimation;
import com.easyandroidanimations.library.SlideOutAnimation;
import com.simple.transformslibrary.Flip3DTransform;
import com.simple.transformslibrary.RotateDownTransformer;
import com.simple.transformslibrary.TransformUtil;
import com.simple.transformslibrary.ZoomBothTransform;
import com.xuxian.new_market.R;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.listener.Monitor.FunctionMonitor;
import com.xuxian.new_market.listener.Monitor.MonitorEnum;
import com.xuxian.new_market.listener.Monitor.ShoppingCarNumMonitor;
import com.xuxian.new_market.listener.OnShoppingCarNumChange;
import com.xuxian.new_market.mvp.entity.IndexConfig;
import com.xuxian.new_market.mvp.entity.IndexConfig.DataEntity.ConfigImgEntity.AllsendEntity;
import com.xuxian.new_market.mvp.entity.IndexConfig.DataEntity.ConfigImgEntity.CartEntity;
import com.xuxian.new_market.mvp.entity.IndexConfig.DataEntity.ConfigImgEntity.FirstEntity;
import com.xuxian.new_market.mvp.entity.IndexConfig.DataEntity.ConfigImgEntity.MyEntity;
import com.xuxian.new_market.mvp.entity.IndexConfig.DataEntity.ConfigImgEntity.ScanCodeEntity;
import com.xuxian.new_market.mvp.ui.activity.base.BaseActivity;
import com.xuxian.new_market.mvp.ui.adapter.viewpageradapter.CategoryPageAdapter;
import com.xuxian.new_market.mvp.ui.fragment.AllSendFragment;
import com.xuxian.new_market.mvp.ui.fragment.Category_one_fragment;
import com.xuxian.new_market.mvp.ui.fragment.Category_two_fragment;
import com.xuxian.new_market.mvp.ui.fragment.MainFragment;
import com.xuxian.new_market.mvp.ui.fragment.PersonalFragment;
import com.xuxian.new_market.mvp.ui.fragment.QRcodeFragment;
import com.xuxian.new_market.mvp.ui.fragment.ShoppingCarFragment;
import com.xuxian.new_market.mvp.ui.widght.NoSlideViewPager;
import com.xuxian.new_market.mvp.ui.widght.ViewPagerScroller;
import com.xuxian.new_market.respository.db.DBHelper;
import com.xuxian.new_market.respository.network.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements OnShoppingCarNumChange {
    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.btn_main_first)
    Button btn_shouye;
    @BindView(R.id.btn_main_second)
    Button btn_quanguosong;
    @BindView(R.id.btn_main_third)
    Button btn_shoppingCar;
    @BindView(R.id.btn_main_fourth)
    Button btn_scanCode;
    @BindView(R.id.btn_main_fiveth)
    Button btn_me;
    Button[] mButtons = new Button[5];
    private NoSlideViewPager nav_viewpager;
    private CategoryPageAdapter categoryPageAdapter;
    private List<Fragment> navFragments;
    private Button btn_fenlei;
    private Button btn_qingshi;
    private SlideInAnimation slideInAnim;
    private LinearLayout buttons;
    private SlideOutAnimation slideOutAnim;
    //购物车角标
    private TextView tv_tab_shopping_number;
    private int clickedNumber;
    private int index;
    //购物车fragment
    private ShoppingCarFragment mShoppingCarFragment;
    //二维码fragment
    private QRcodeFragment mQRcodeFragment;
    //全国送fragment
    private AllSendFragment mAllsendFragment;
    //我fragment
    private PersonalFragment mPersonalFragment;
    //首页fragment
    private MainFragment fragment_main;
    //当前显示的fragment
    private Fragment mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        btn_shouye = (Button) findViewById(R.id.btn_main_first);
        btn_quanguosong = (Button) findViewById(R.id.btn_main_second);
        btn_shoppingCar = (Button) findViewById(R.id.btn_main_third);
        btn_scanCode = (Button) findViewById(R.id.btn_main_fourth);
        btn_me = (Button) findViewById(R.id.btn_main_fiveth);
        nav_viewpager = (NoSlideViewPager) findViewById(R.id.nav_viewpager);
        btn_fenlei = (Button) findViewById(R.id.nav_fenlei);
        btn_qingshi = (Button) findViewById(R.id.nav_qingshi);

        mButtons[0] = btn_shouye;
        mButtons[1] = btn_quanguosong;
        mButtons[2] = btn_shoppingCar;
        mButtons[3] = btn_scanCode;
        mButtons[4] = btn_me;

        tv_tab_shopping_number = (TextView) findViewById(R.id.tv_tab_shopping_number);

        btn_shouye.setTypeface(Typeface.DEFAULT_BOLD);
        btn_shouye.setSelected(true);

        categoryPageAdapter = new CategoryPageAdapter(getSupportFragmentManager());

        navFragments = new ArrayList<>();
        navFragments.add(Category_one_fragment.getInstance());
        navFragments.add(Category_two_fragment.getInstance());
        categoryPageAdapter.setFragments(navFragments);
        nav_viewpager.setAdapter(categoryPageAdapter);
        ViewPagerScroller scroller=new ViewPagerScroller(this);
        scroller.initViewPagerScroll(nav_viewpager);
        TransformUtil.reverse(nav_viewpager,new Flip3DTransform());

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment_main = new MainFragment();
        mContent=fragment_main;
        fragment_main.setmDrawerLayout(mDrawerLayout);
        buttons = (LinearLayout) findViewById(R.id.buttons);
//        fragment_main.setButtons(buttons);
        transaction.add(R.id.fragment_container, fragment_main, MainFragment.class.getSimpleName()).commit();

        /*--------------11-02 显示或隐藏功能区 改用观察者模式----------------*/
        //region 功能区显示和隐藏监听注册
        FunctionMonitor.getInstance().registerFuntionMonitorCallback(MainActivity.class.getSimpleName(), new FunctionMonitor.ICallback() {
            @Override
            public void appOperation(MonitorEnum.FunctionEnum funcEnum) {
                switch (funcEnum) {
                    case HIDE_FUNCTION:
                        if (slideInAnim != null && slideInAnim.getAnimatorSet().isRunning()) {
//                            slideOutAnim.getAnimatorSet().end();
                            break;
                        }
                        slideOutAnim = new SlideOutAnimation(buttons).setListener(new AnimationListener() {
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                FunctionMonitor.getInstance().showFunction = false;
                            }
                        }).setDuration(500).setDirection(4);
                        slideOutAnim.animate();
                        break;
                    case SHOW_FUNCTION:
                        if (slideOutAnim != null && slideOutAnim.getAnimatorSet().isRunning()) {
//                            slideInAnim.getAnimatorSet().end();
                            break;
                        }
                        slideInAnim = new SlideInAnimation(buttons).setListener(new AnimationListener() {
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                FunctionMonitor.getInstance().showFunction = true;
                            }
                        }).setDuration(500).setDirection(4);
                        slideInAnim.animate();
                        break;
                }
            }
        });
        //endregion

        //region 购物车数量监听注册
        ShoppingCarNumMonitor.getInstance().registerShoppingCarNumMonitor(MainActivity.class.getSimpleName(), new ShoppingCarNumMonitor.ICallback() {
            @Override
            public void appOperation(MonitorEnum.ShoppingCarNumEnum mEnum, int num, boolean isIncrease) {
                String currentNum;
                switch (mEnum) {
                    case ONE_BY_ONE://在list模式下 添加或删除商品
                        currentNum = tv_tab_shopping_number.getText().toString();
                        if (isIncrease) {
                            tv_tab_shopping_number.setVisibility(View.VISIBLE);
                            Integer value = Integer.valueOf(currentNum);
                            tv_tab_shopping_number.setText(String.valueOf(value + 1));
                        } else if (Integer.valueOf(tv_tab_shopping_number.getText().toString()) > 1) {
                            Integer value = Integer.valueOf(currentNum);
                            tv_tab_shopping_number.setText(String.valueOf(value - 1));
                        } else {
                            tv_tab_shopping_number.setVisibility(View.GONE);
                            tv_tab_shopping_number.setText("0");
                        }
                        break;
                    case NOT_ONE_BY_ONE://在详情页面添加商品
                        currentNum = tv_tab_shopping_number.getText().toString();
                        Integer value = Integer.valueOf(currentNum);
                        tv_tab_shopping_number.setText(String.valueOf(value + num));
                        break;
                }
            }
        });
        //endregion
        /*--------------11-02 显示或隐藏功能区 改用观察者模式----------------*/
    }

    @Override
    protected void initEvent() {
        btn_fenlei.setSelected(true);
        btn_fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_qingshi.setTextColor(getResources().getColor(R.color.green_light));
                btn_fenlei.setTextColor(getResources().getColor(R.color.orange_light));
                btn_qingshi.setBackgroundResource(R.color.gray_pressed);
                btn_fenlei.setBackgroundResource(R.drawable.sliding_btn);
                nav_viewpager.setCurrentItem(0);
            }
        });
        btn_qingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_fenlei.setTextColor(getResources().getColor(R.color.green_light));
                btn_fenlei.setBackgroundResource(R.color.gray_pressed);
                btn_qingshi.setBackgroundResource(R.drawable.sliding_btn);
                btn_qingshi.setTextColor(getResources().getColor(R.color.orange_light));
                nav_viewpager.setCurrentItem(1);
            }
        });
    }

    @Override
    protected void initData() {
        //// TODO: 2016/11/2 图标初始化 购物车数量

        Call<IndexConfig> call = RetrofitManager.getInstance().getIndexConfig(ApiConstant.ver, String.valueOf(System.currentTimeMillis()), "110000", "1266", "");
        call.enqueue(new Callback<IndexConfig>() {
            @Override
            public void onResponse(Call<IndexConfig> call, Response<IndexConfig> response) {
                if (!response.isSuccessful()) {

                } else if (response.body().getStatus().getCode().equals("0")) {
                    configMy(response.body().getData().getConfig_img().getMy());
                    configCar(response.body().getData().getConfig_img().getCart());
                    configScan(response.body().getData().getConfig_img().getScan_code());
                    configQuanGuo(response.body().getData().getConfig_img().getAllsend());
                    configFirst(response.body().getData().getConfig_img().getFirst());
                } else {

                }
            }

            @Override
            public void onFailure(Call<IndexConfig> call, Throwable t) {

            }
        });

        //region 从db中初始化购物车数量
        /*-----------------------------从db中初始化购物车数量------------------------------------*/
        int account = 0;
        Cursor cursor = null;
        try {
            cursor = DBHelper.getInstance(this).getShoppingCartGoodsDao().getSession().getDatabase().rawQuery(" select sum(ACCOUNT) from SHOPPING_CART_GOODS where USERID='1609759' ", null);
            cursor.moveToNext();
            account = cursor.getInt(cursor.getColumnIndex("sum(ACCOUNT)"));

            if (account == 0) {
                tv_tab_shopping_number.setVisibility(View.GONE);
            } else
                tv_tab_shopping_number.setVisibility(View.VISIBLE);
            tv_tab_shopping_number.setText(account + "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        /*-------------------------------------------------------------------------------------*/
        //endregion

    }

    //region 初始化功能区图标和文字

    /**
     * 初始化首页图标文字
     *
     * @param first
     */
    private void configFirst(FirstEntity first) {

        btn_shouye.setText(first.getName());
        if (!first.getImg().isEmpty() && first.getImg() != null) {
            Drawable drawableCar = AbImageUtil.bitmapToDrawable(AbImageUtil.getBitmap(first.getImg()));
            btn_shouye.setCompoundDrawables(null, drawableCar, null, null);
        }
    }

    /**
     * 初始化全国送图标文字
     *
     * @param allsend
     */
    private void configQuanGuo(AllsendEntity allsend) {
        btn_quanguosong.setText(allsend.getName());
        if (!allsend.getImg().isEmpty() && allsend.getImg() != null) {
            Drawable drawableCar = AbImageUtil.bitmapToDrawable(AbImageUtil.getBitmap(allsend.getImg()));
            btn_quanguosong.setCompoundDrawables(null, drawableCar, null, null);
        }
    }

    /**
     * 初始化扫码图标和文字
     *
     * @param scan_code
     */
    private void configScan(ScanCodeEntity scan_code) {
        btn_scanCode.setText(scan_code.getName());
        if (!scan_code.getImg().isEmpty() && scan_code.getImg() != null) {
            Drawable drawableCar = AbImageUtil.bitmapToDrawable(AbImageUtil.getBitmap(scan_code.getImg()));
            btn_scanCode.setCompoundDrawables(null, drawableCar, null, null);
        }
    }

    /**
     * 初始化购物车图标和文字
     *
     * @param cart
     */
    private void configCar(CartEntity cart) {
        btn_shoppingCar.setText(cart.getName());
        if (!cart.getImg().isEmpty() && cart.getImg() != null) {
            Drawable drawableCar = AbImageUtil.bitmapToDrawable(AbImageUtil.getBitmap(cart.getImg()));
            btn_shoppingCar.setCompoundDrawables(null, drawableCar, null, null);
        }
    }

    /**
     * 初始化我的图标和文字
     *
     * @param my
     */
    private void configMy(MyEntity my) {
        btn_me.setText(my.getName());
        if (!my.getImg().isEmpty() && my.getImg() != null) {
            Drawable drawableMe = AbImageUtil.bitmapToDrawable(AbImageUtil.getBitmap(my.getImg()));
            btn_me.setCompoundDrawables(null, drawableMe, null, null);
        }
    }
    //endregion

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void appCompatStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup contentView = (ViewGroup) mDrawerLayout.getChildAt(0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            //4.4~5.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0+
                contentView.addView(createStatusBarView(this, getResources().getColor(R.color.orange_light)), 0);
                if (!(contentView instanceof LinearLayout) && contentView != null) {
                    contentView.getChildAt(1).setPadding(contentView.getChildAt(1).getPaddingLeft(), getStatusBarHeight(this), contentView.getChildAt(1).getPaddingRight(), contentView.getChildAt(1).getPaddingBottom());
                }
            }
            contentView.setFitsSystemWindows(false);
            contentView.setClipToPadding(false);
            mDrawerLayout.setFitsSystemWindows(false);
            mDrawerLayout.getChildAt(1).setFitsSystemWindows(false);
        }
    }

    //    @OnClick({R.id.btn_main_first, R.id.btn_main_second, R.id.btn_main_third, R.id.btn_main_fourth, R.id.btn_main_fiveth})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_first:
//                clearButtonCondition();
                mainFragment();
//                btn_shouye.setTypeface(Typeface.DEFAULT_BOLD);
//                btn_shouye.setSelected(true);
//                btn_shouye.setBackgroundResource(R.color.orange_light);
                break;
            case R.id.btn_main_second:
//                clearButtonCondition();
                mAllsendFragment();
//                btn_quanguosong.setTypeface(Typeface.DEFAULT_BOLD);
//                btn_quanguosong.setSelected(true);
//                btn_quanguosong.setBackgroundResource(R.color.orange_light);
                break;
            case R.id.btn_main_third:
//                clearButtonCondition();
//                btn_shoppingCar.setTypeface(Typeface.DEFAULT_BOLD);
//                btn_shoppingCar.setSelected(true);
                shoppingCarFragment();
//                btn_shoppingCar.setBackgroundResource(R.color.orange_light);
                break;
            case R.id.btn_main_fourth:
//                clearButtonCondition();
//                btn_scanCode.setTypeface(Typeface.DEFAULT_BOLD);
//                btn_scanCode.setSelected(true);
                qrCodeFragment();
//                btn_scanCode.setBackgroundResource(R.color.orange_light);
                break;
            case R.id.btn_main_fiveth:
//                clearButtonCondition();
//                btn_me.setTypeface(Typeface.DEFAULT_BOLD);
//                btn_me.setSelected(true);
                personalFragment();
//                btn_me.setBackgroundResource(R.color.orange_light);
                break;
            default:
                break;
        }
    }

    private void clearButtonCondition() {
        btn_me.setSelected(false);
        btn_me.setTypeface(Typeface.DEFAULT);
        btn_me.setBackgroundColor(Color.WHITE);
        btn_shoppingCar.setSelected(false);
        btn_shoppingCar.setTypeface(Typeface.DEFAULT);
        btn_shoppingCar.setBackgroundColor(Color.WHITE);
        btn_quanguosong.setSelected(false);
        btn_quanguosong.setTypeface(Typeface.DEFAULT);
        btn_quanguosong.setBackgroundColor(Color.WHITE);
        btn_shouye.setSelected(false);
        btn_shouye.setTypeface(Typeface.DEFAULT);
        btn_shouye.setBackgroundColor(Color.WHITE);
        btn_scanCode.setSelected(false);
        btn_scanCode.setTypeface(Typeface.DEFAULT);
        btn_scanCode.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void changeShoppingCarNum(boolean isAdd) {

    }

    /**
     * 切换首页fragment
     */
    private void mainFragment() {
        FunctionMonitor.getInstance().isSuspend=false;
        this.index = 0;
        if (fragment_main == null)
            fragment_main = new MainFragment();
        this.clickedNumber++;
        if (clickedNumber == 2) {
            clickedNumber = 0;
            //listview滑动到顶部
            fragment_main.scrollToTop();
        }
        switchContent(fragment_main, MainFragment.class.getSimpleName());
    }

    /**
     * 切换购物车fragment
     */
    private void shoppingCarFragment() {
        FunctionMonitor.getInstance().isSuspend=true;
        this.clickedNumber = 0;
        if (index != 2) {
            //// TODO: 16/8/2 从别的tab调到购物车刷新购物车 刷新购物车
        }
        index = 2;
        if (mShoppingCarFragment == null)
            mShoppingCarFragment = new ShoppingCarFragment();
        switchContent(mShoppingCarFragment, ShoppingCarFragment.class.getSimpleName());
    }

    /**
     * 切换个人中心fragment
     */
    private void personalFragment() {
        clickedNumber = 0;
        index = 4;
        if (mPersonalFragment == null)
            mPersonalFragment = new PersonalFragment();
        switchContent(mPersonalFragment, PersonalFragment.class.getSimpleName());
    }

    /**
     * 切换扫码fragment
     */
    private void qrCodeFragment() {
        clickedNumber = 0;
        index = 3;
        if (mQRcodeFragment == null) {
            mQRcodeFragment = new QRcodeFragment();
        }
        switchContent(mQRcodeFragment, QRcodeFragment.class.getSimpleName());
    }

    /**
     * 切换全国送fragment
     */
    private void mAllsendFragment() {
        clickedNumber = 0;
        index=1;
        if (mAllsendFragment == null) {
            mAllsendFragment = new AllSendFragment();
        }
        switchContent(mAllsendFragment, AllSendFragment.class.getSimpleName());
    }

    /**
     * 切换tab中fragment
     *
     * @param fragment
     * @param tag
     */
    private void switchContent(Fragment fragment, String tag) {
        try {
            if (mContent != fragment) {//点击为非当前fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (fragment.isAdded()) {
                    transaction.hide(mContent).show(fragment).commitAllowingStateLoss();
                } else {
                    transaction.hide(mContent).add(R.id.fragment_container, fragment, tag).show(fragment).commitAllowingStateLoss();
                }
                for (Button button : mButtons) {
                    button.setSelected(false);
                    button.setTypeface(Typeface.DEFAULT);
                }
                mButtons[index].setSelected(true);
                mButtons[index].setTypeface(Typeface.DEFAULT_BOLD);
                mContent = fragment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
