package com.xuxian.new_market.mvp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youarenotin on 2016/10/25.
 */

public class SectionGoodsEntity implements Serializable{
    private static final long serialVersionUID = 1;

    private StatusEntity status;

    private DataEntity data;

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class StatusEntity implements Serializable{
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataEntity implements Serializable {
        private static final long serialVersionUID = 1;
        private String title;
        private String viewlimit;

        private List<GoodsEntity> goods;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getViewlimit() {
            return viewlimit;
        }

        public void setViewlimit(String viewlimit) {
            this.viewlimit = viewlimit;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public static class GoodsEntity implements Serializable{
            private static final long serialVersionUID = 1;
            private String goods_type;
            private String favorite;
            private String sold_img;
            private String unit;
            private String newimg;
            private String id;
            private String title;
            private String price;
            private String icon;
            private String store_nums;
            private String sold_num;
            private String market_price;
            private String real_name;
            private String category_id;
            private String main_name;
            private String details2;

            private SellactsEntity sellacts;
            private String selltype;
            private String tipsimg;
            private String phonetips;
            private String show;
            private String starttime;
            private String endtime;
            private String laouser;
            private String fromid;

            private CategoryTipsEntity category_tips;
            private String newuserprice;
            private String price_info;
            private String vip_price_str;
            private String rulestips;
            private List<?> corver;
            private List<?> group_price;
            private List<String> show_imgs;

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getFavorite() {
                return favorite;
            }

            public void setFavorite(String favorite) {
                this.favorite = favorite;
            }

            public String getSold_img() {
                return sold_img;
            }

            public void setSold_img(String sold_img) {
                this.sold_img = sold_img;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getNewimg() {
                return newimg;
            }

            public void setNewimg(String newimg) {
                this.newimg = newimg;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getStore_nums() {
                return store_nums;
            }

            public void setStore_nums(String store_nums) {
                this.store_nums = store_nums;
            }

            public String getSold_num() {
                return sold_num;
            }

            public void setSold_num(String sold_num) {
                this.sold_num = sold_num;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getMain_name() {
                return main_name;
            }

            public void setMain_name(String main_name) {
                this.main_name = main_name;
            }

            public String getDetails2() {
                return details2;
            }

            public void setDetails2(String details2) {
                this.details2 = details2;
            }

            public SellactsEntity getSellacts() {
                return sellacts;
            }

            public void setSellacts(SellactsEntity sellacts) {
                this.sellacts = sellacts;
            }

            public String getSelltype() {
                return selltype;
            }

            public void setSelltype(String selltype) {
                this.selltype = selltype;
            }

            public String getTipsimg() {
                return tipsimg;
            }

            public void setTipsimg(String tipsimg) {
                this.tipsimg = tipsimg;
            }

            public String getPhonetips() {
                return phonetips;
            }

            public void setPhonetips(String phonetips) {
                this.phonetips = phonetips;
            }

            public String getShow() {
                return show;
            }

            public void setShow(String show) {
                this.show = show;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getLaouser() {
                return laouser;
            }

            public void setLaouser(String laouser) {
                this.laouser = laouser;
            }

            public String getFromid() {
                return fromid;
            }

            public void setFromid(String fromid) {
                this.fromid = fromid;
            }

            public CategoryTipsEntity getCategory_tips() {
                return category_tips;
            }

            public void setCategory_tips(CategoryTipsEntity category_tips) {
                this.category_tips = category_tips;
            }

            public String getNewuserprice() {
                return newuserprice;
            }

            public void setNewuserprice(String newuserprice) {
                this.newuserprice = newuserprice;
            }

            public String getPrice_info() {
                return price_info;
            }

            public void setPrice_info(String price_info) {
                this.price_info = price_info;
            }

            public String getVip_price_str() {
                return vip_price_str;
            }

            public void setVip_price_str(String vip_price_str) {
                this.vip_price_str = vip_price_str;
            }

            public String getRulestips() {
                return rulestips;
            }

            public void setRulestips(String rulestips) {
                this.rulestips = rulestips;
            }

            public List<?> getCorver() {
                return corver;
            }

            public void setCorver(List<?> corver) {
                this.corver = corver;
            }

            public List<?> getGroup_price() {
                return group_price;
            }

            public void setGroup_price(List<?> group_price) {
                this.group_price = group_price;
            }

            public List<String> getShow_imgs() {
                return show_imgs;
            }

            public void setShow_imgs(List<String> show_imgs) {
                this.show_imgs = show_imgs;
            }

            public static class SellactsEntity implements  Serializable {


                private StoreEntity store;

                public StoreEntity getStore() {
                    return store;
                }

                public void setStore(StoreEntity store) {
                    this.store = store;
                }

                public static class StoreEntity implements Serializable {
                    private String id;
                    private String user_type;
                    private String calculate_model;
                    private String starttime;
                    private String endtime;
                    private String store_id;
                    private String goods_id;
                    private String meal_no;
                    private String tipsimg;
                    private String phonetips;
                    private String restrictive;
                    private String sell_price;
                    private String onebuy;
                    private String intro;
                    private String limited;
                    private String system_id;
                    private String show;
                    private String down_count;
                    private String moretips;
                    private String oldphoneimg;
                    private List<String> show_imgs;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getUser_type() {
                        return user_type;
                    }

                    public void setUser_type(String user_type) {
                        this.user_type = user_type;
                    }

                    public String getCalculate_model() {
                        return calculate_model;
                    }

                    public void setCalculate_model(String calculate_model) {
                        this.calculate_model = calculate_model;
                    }

                    public String getStarttime() {
                        return starttime;
                    }

                    public void setStarttime(String starttime) {
                        this.starttime = starttime;
                    }

                    public String getEndtime() {
                        return endtime;
                    }

                    public void setEndtime(String endtime) {
                        this.endtime = endtime;
                    }

                    public String getStore_id() {
                        return store_id;
                    }

                    public void setStore_id(String store_id) {
                        this.store_id = store_id;
                    }

                    public String getGoods_id() {
                        return goods_id;
                    }

                    public void setGoods_id(String goods_id) {
                        this.goods_id = goods_id;
                    }

                    public String getMeal_no() {
                        return meal_no;
                    }

                    public void setMeal_no(String meal_no) {
                        this.meal_no = meal_no;
                    }

                    public String getTipsimg() {
                        return tipsimg;
                    }

                    public void setTipsimg(String tipsimg) {
                        this.tipsimg = tipsimg;
                    }

                    public String getPhonetips() {
                        return phonetips;
                    }

                    public void setPhonetips(String phonetips) {
                        this.phonetips = phonetips;
                    }

                    public String getRestrictive() {
                        return restrictive;
                    }

                    public void setRestrictive(String restrictive) {
                        this.restrictive = restrictive;
                    }

                    public String getSell_price() {
                        return sell_price;
                    }

                    public void setSell_price(String sell_price) {
                        this.sell_price = sell_price;
                    }

                    public String getOnebuy() {
                        return onebuy;
                    }

                    public void setOnebuy(String onebuy) {
                        this.onebuy = onebuy;
                    }

                    public String getIntro() {
                        return intro;
                    }

                    public void setIntro(String intro) {
                        this.intro = intro;
                    }

                    public String getLimited() {
                        return limited;
                    }

                    public void setLimited(String limited) {
                        this.limited = limited;
                    }

                    public String getSystem_id() {
                        return system_id;
                    }

                    public void setSystem_id(String system_id) {
                        this.system_id = system_id;
                    }

                    public String getShow() {
                        return show;
                    }

                    public void setShow(String show) {
                        this.show = show;
                    }

                    public String getDown_count() {
                        return down_count;
                    }

                    public void setDown_count(String down_count) {
                        this.down_count = down_count;
                    }

                    public String getMoretips() {
                        return moretips;
                    }

                    public void setMoretips(String moretips) {
                        this.moretips = moretips;
                    }

                    public String getOldphoneimg() {
                        return oldphoneimg;
                    }

                    public void setOldphoneimg(String oldphoneimg) {
                        this.oldphoneimg = oldphoneimg;
                    }

                    public List<String> getShow_imgs() {
                        return show_imgs;
                    }

                    public void setShow_imgs(List<String> show_imgs) {
                        this.show_imgs = show_imgs;
                    }
                }
            }

            public static class CategoryTipsEntity implements Serializable {
                private String txt;
                private String f_color;
                private String b_color;

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getF_color() {
                    return f_color;
                }

                public void setF_color(String f_color) {
                    this.f_color = f_color;
                }

                public String getB_color() {
                    return b_color;
                }

                public void setB_color(String b_color) {
                    this.b_color = b_color;
                }
            }
        }
    }
}
