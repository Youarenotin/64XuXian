package com.xuxian.new_market.respository.network;

import com.ab.http.AbRequestParams;
import com.xuxian.new_market.App;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.mvp.entity.AddGoodsEntity;
import com.xuxian.new_market.mvp.entity.CategoryInfo;
import com.xuxian.new_market.mvp.entity.DelGoodsEntity;
import com.xuxian.new_market.mvp.entity.GoodsInfo;
import com.xuxian.new_market.mvp.entity.IndexConfig;
import com.xuxian.new_market.mvp.entity.SectionBlocksEntity;
import com.xuxian.new_market.mvp.entity.SectionGoodsEntity;
import com.xuxian.new_market.mvp.entity.ShoppingCarInfo;
import com.xuxian.new_market.respository.network.APIInterface.API_Main_Fragment;
import com.xuxian.new_market.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by youarenotin on 2016/10/20.
 */

public class RetrofitManager {
    API_Main_Fragment mMainFragmentService;
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";


//    private static SparseArray<RetrofitManager> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);


    private static volatile OkHttpClient mOkHttpClient;

    private static RetrofitManager instance;
    private RetrofitManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //注册api实现类
        mMainFragmentService = retrofit.create(API_Main_Fragment.class);

    }

    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                Cache cache = new Cache(new File(App.getAppContext().getCacheDir(),"HttpCache"),1024*1024*10);
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }

    public static RetrofitManager getInstance(){
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance=new RetrofitManager();
                }
            }
        }
        return instance;
    }
    private static volatile OkHttpClient sOkHttpClient;
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkAvailable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
//            KLog.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
//            KLog.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };
    /*---------------------------------------------在下面添加新增接口-----------------------------------------------*/

    /**
     * 获取nav菜单数据
     * @param id
     * @param ver
     * @param timeStamp
     * @return
     */
   public Call<CategoryInfo> getInfos(String id,String ver , String timeStamp){
        return mMainFragmentService.getInfos(id, ver,timeStamp);
    }

    /**
     * 获取主main初始化数据
     * @param ver
     * @param timeStamp
     * @param cityId
     * @param storeId
     * @param uid
     * @return
     */
    public Call<IndexConfig> getIndexConfig(String ver , String timeStamp,String cityId,String storeId,String uid){
        return mMainFragmentService.getIndexConfig(ver,timeStamp,cityId,storeId,uid);
    }

    /**
     * 获取blocks
     * @param ver
     * @param timeStamp
     * @param sectionId
     * @param storeId
     * @return
     */
    public Call<SectionBlocksEntity> getSectionBlocks(String ver, String timeStamp, String sectionId, String storeId) {
        return mMainFragmentService.getSectionBlocks(ver,timeStamp,sectionId,storeId);
    }

    /**
     * 获取商品列表
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    public Call<SectionGoodsEntity> getSectionGoods(String ver, String timeStamp, Map<String,String> paramsMap) {
        return mMainFragmentService.getSectionGoods(ver,timeStamp,paramsMap);
    }

    /**
     * 获取商品详情
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    public Call<GoodsInfo> getGoodsInfo(String ver,String timeStamp,Map<String,String> paramsMap){
        return mMainFragmentService.getGoodsInfo(ver,timeStamp,paramsMap);
    }

    /**
     * 添加到购物车
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    public Call<AddGoodsEntity> addGoods(String ver,String timeStamp , Map<String,String> paramsMap){
        return mMainFragmentService.addGoods(ver,timeStamp,paramsMap);
    }

    /**
     * 删除购物车
     * @param ver
     * @param timeStamp
     * @param parmasMap
     * @return
     */
    public Call<DelGoodsEntity> delGoods(String ver, String timeStamp, Map<String, String> parmasMap) {
        return mMainFragmentService.delGoods(ver, timeStamp, parmasMap);
    }

    /**
     * 获取购物车信息
     * @param ver
     * @param timeStamp
     * @param parmasMap
     * @return
     */
    public Call<ShoppingCarInfo> getShoppingCarInfo(String ver, String timeStamp, Map<String, String> parmasMap){
        return mMainFragmentService.getShoppingCarInfo(ver, timeStamp, parmasMap);
    }

    public Call<ShoppingCarInfo> navigateShoppingCar(String ver, String timeStamp, Map<String, String> map) {
        return mMainFragmentService.navigateShoppingCar(ver,timeStamp,map);
    }
}
