package com.xuxian.new_market.mvp.entity;

import java.util.List;

/**
 * Created by youarenotin on 2016/10/27.
 */

public class GoodsInfo {
    /**
     * code : 0
     * message : success
     */

    private StatusEntity status;
    /**
     * goods : {"unit":"约400-500g","newimg":"http://imgcdn.xuxian.com/http://imgcdn.xuxian.com/upload/2016/10/09/20161009115221697.jpg","id":"1241","favorite":"0","title":"酸酸甜甜，白色橘络更营养","sold_img":"","price":"5.20","icon":"http://imgcdn.xuxian.com/upload/2016/09/30/20160930013249563_400_300.jpg","store_nums":"2147483647","sold_num":"468","market_price":"7.00","main_name":"湖北蜜桔","pt_ids":"0","store_num":2147483179,"corver":[],"details2":"http://mobile.xuxian.com/goods/getGoodsDetail/1241?ver=2015121401","selltype":"","tipsimg":"http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229952.png","phonetips":"http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229971.png","show":"1","starttime":"2016-10-24 01:00:00","endtime":"2016-10-31 01:00:00","laouser":"5.80","fromid":"","category_tips":{"txt":"水果","f_color":"ffd040","b_color":"ffffff"},"newuserprice":"0.00","group_price":[],"show_imgs":["http://imgcdn.xuxian.com/upload/2016/08/09/20160809110959444.png"],"price_info":"非会员价:￥5.80/份","rulestips":"","list_img":"http://imgcdn.xuxian.com/"}
     */

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

    public static class StatusEntity {
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

    public static class DataEntity {


        private GoodsEntity goods;

        public GoodsEntity getGoods() {
            return goods;
        }

        public void setGoods(GoodsEntity goods) {
            this.goods = goods;
        }

        public static class GoodsEntity {
            private String unit;
            private String newimg;
            private String id;
            private String favorite;
            private String title;
            private String sold_img;
            private String price;
            private String icon;
            private String store_nums;
            private String sold_num;
            private String market_price;
            private String main_name;
            private String pt_ids;
            private int store_num;
            private String details2;
            private String selltype;
            private String tipsimg;
            private String phonetips;
            private String show;
            private String starttime;
            private String endtime;
            private String laouser;
            private String fromid;
            /**
             * txt : 水果
             * f_color : ffd040
             * b_color : ffffff
             */

            private CategoryTipsEntity category_tips;
            private String newuserprice;
            private String price_info;
            private String rulestips;
            private String list_img;
            private List<?> corver;
            private List<?> group_price;
            private List<String> show_imgs;

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

            public String getFavorite() {
                return favorite;
            }

            public void setFavorite(String favorite) {
                this.favorite = favorite;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSold_img() {
                return sold_img;
            }

            public void setSold_img(String sold_img) {
                this.sold_img = sold_img;
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

            public String getMain_name() {
                return main_name;
            }

            public void setMain_name(String main_name) {
                this.main_name = main_name;
            }

            public String getPt_ids() {
                return pt_ids;
            }

            public void setPt_ids(String pt_ids) {
                this.pt_ids = pt_ids;
            }

            public int getStore_num() {
                return store_num;
            }

            public void setStore_num(int store_num) {
                this.store_num = store_num;
            }

            public String getDetails2() {
                return details2;
            }

            public void setDetails2(String details2) {
                this.details2 = details2;
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

            public String getRulestips() {
                return rulestips;
            }

            public void setRulestips(String rulestips) {
                this.rulestips = rulestips;
            }

            public String getList_img() {
                return list_img;
            }

            public void setList_img(String list_img) {
                this.list_img = list_img;
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

            public static class CategoryTipsEntity {
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
