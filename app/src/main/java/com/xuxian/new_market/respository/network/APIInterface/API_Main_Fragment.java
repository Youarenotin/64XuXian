package com.xuxian.new_market.respository.network.APIInterface;

import com.ab.http.AbRequestParams;
import com.xuxian.new_market.mvp.entity.AddGoodsEntity;
import com.xuxian.new_market.mvp.entity.CategoryInfo;
import com.xuxian.new_market.mvp.entity.DelGoodsEntity;
import com.xuxian.new_market.mvp.entity.GoodsInfo;
import com.xuxian.new_market.mvp.entity.IndexConfig;
import com.xuxian.new_market.mvp.entity.SectionBlocksEntity;
import com.xuxian.new_market.mvp.entity.SectionGoodsEntity;
import com.xuxian.new_market.mvp.entity.ShoppingCarInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by youarenotin on 2016/10/21.
 */

public interface API_Main_Fragment {

//    void  getUserInfo(@Path(cityId))

    /**
     * 获取nav菜单信息
     * @param id
     * @param ver
     * @param timeStamp
     * @return
     */
    @GET("category/getinfos/{id}/")
    Call<CategoryInfo> getInfos(
            @Path("id") String id,
           @Query("ver") String ver,
           @Query("_t") String timeStamp
    );

    /**
     * 获取初始化tab信息和图标吧好像
     * @param ver
     * @param timeStamp
     * @param cityId
     * @param storeId
     * @param uid
     * @return
     */
    @FormUrlEncoded
    @POST("index/config/")
    Call<IndexConfig> getIndexConfig(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @Field("cityId") String cityId,
            @Field("storeId") String storeId,
            @Field("userId") String uid
    );

    /**
     * 获取blocks
     * @param ver
     * @param timeStamp
     * @param sectionListId
     * @param storeId
     * @return
     */
    @FormUrlEncoded
    @POST("/index/getSectionBlocks")
    Call<SectionBlocksEntity> getSectionBlocks(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @Field("sectionlistId") String sectionListId,
            @Field("STORE_ID") String storeId
    );

    /**
     * 查询tab的goods
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    @FormUrlEncoded
    @POST("/index/getSectionGoods")
    Call<SectionGoodsEntity> getSectionGoods(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @FieldMap Map<String,String> paramsMap
            );

    /**
     * 商品详情
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    @FormUrlEncoded
    @POST("goods/getGoodsInfo")
    Call<GoodsInfo> getGoodsInfo(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @FieldMap Map<String,String> paramsMap
    );

    /**
     * 删除商品
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    @FormUrlEncoded
    @POST("/order/newGoodsCart")
    Call<DelGoodsEntity> delGoods(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @FieldMap Map<String,String> paramsMap
    );

    /**
     * 添加商品
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    @FormUrlEncoded
    @POST("/order/newGoodsCart")
    Call<AddGoodsEntity> addGoods(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @FieldMap Map<String,String> paramsMap
    );

    /**
     * 获取购物车信息
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    @FormUrlEncoded
    @POST("/order/newCartGoods")
    Call<ShoppingCarInfo> getShoppingCarInfo(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @FieldMap Map<String,String> paramsMap
    );

    /**
     * 增删改购物车内已有商品
     * @param ver
     * @param timeStamp
     * @param paramsMap
     * @return
     */
    @FormUrlEncoded
    @POST("/order/newGoodsCart")
    Call<ShoppingCarInfo> navigateShoppingCar(
            @Query("ver") String ver,
            @Query("_t") String timeStamp,
            @FieldMap Map<String,String> paramsMap
    );
}
