package com.xuxian.new_market.mvp.entity;

import java.util.List;

/**
 * Created by youarenotin on 2016/11/11.
 */

public class ShoppingCarInfo {

    /**
     * code : 0
     * message : success
     */

    private StatusEntity status;
    /**
     * is_alert : alert
     * undoactions : {"info":[{"actname":"订单优惠","info":"满59减6;99减12;129减16(不与换购同时享受)","acttype":3}],"sectionname":"温馨提示"}
     * goodssections : [{"goodslist":[{"goods_type":"1","up_time":"2016-11-01 12:00:00","newprice":"0.00","down_time":"2020-11-01 01:00:00","is_del":"0","unit":"2个","newimg":"http://imgcdn.xuxian.com/upload/2016/11/01/20161101102730529.jpg","id":"599","soft_no":"99","title":"甜嫩汁水流淌于舌尖","price":"15.50","icon":"http://imgcdn.xuxian.com/upload/2016/11/01/20161101102724525_400_300.jpg","store_nums":"2147483647","sold_num":"10555","market_price":"25.90","real_name":"赣南脐橙","category_type":"1","store_num":2147473092,"corver":[],"selltype":"","tipsimg":"http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229952.png","phonetips":"http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229971.png","starttime":"2016-11-07 01:00:00","endtime":"2016-11-14 01:00:00","show":"1","group_price":[{"goods_id":"599","group_id":"5","price":"14.99","group_name":"VIP"}],"count":"1","amount":"1","store_goods":"yes","cart_goods_nums":"1","total_price_one":15.5,"main_name":"赣南脐橙"}],"sectionname":"参与优惠商品","sgprice":15.5,"accessabel":"yes"}]
     * prices : 15.5
     * order_amount : 15.5
     * downpay : 0
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
        private String is_alert;
        /**
         * info : [{"actname":"订单优惠","info":"满59减6;99减12;129减16(不与换购同时享受)","acttype":3}]
         * sectionname : 温馨提示
         */

        private UndoactionsEntity undoactions;
        private double prices;
        private double order_amount;
        private int downpay;
        /**
         * goodslist : [{"goods_type":"1","up_time":"2016-11-01 12:00:00","newprice":"0.00","down_time":"2020-11-01 01:00:00","is_del":"0","unit":"2个","newimg":"http://imgcdn.xuxian.com/upload/2016/11/01/20161101102730529.jpg","id":"599","soft_no":"99","title":"甜嫩汁水流淌于舌尖","price":"15.50","icon":"http://imgcdn.xuxian.com/upload/2016/11/01/20161101102724525_400_300.jpg","store_nums":"2147483647","sold_num":"10555","market_price":"25.90","real_name":"赣南脐橙","category_type":"1","store_num":2147473092,"corver":[],"selltype":"","tipsimg":"http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229952.png","phonetips":"http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229971.png","starttime":"2016-11-07 01:00:00","endtime":"2016-11-14 01:00:00","show":"1","group_price":[{"goods_id":"599","group_id":"5","price":"14.99","group_name":"VIP"}],"count":"1","amount":"1","store_goods":"yes","cart_goods_nums":"1","total_price_one":15.5,"main_name":"赣南脐橙"}]
         * sectionname : 参与优惠商品
         * sgprice : 15.5
         * accessabel : yes
         */

        private List<GoodssectionsEntity> goodssections;

        public String getIs_alert() {
            return is_alert;
        }

        public void setIs_alert(String is_alert) {
            this.is_alert = is_alert;
        }

        public UndoactionsEntity getUndoactions() {
            return undoactions;
        }

        public void setUndoactions(UndoactionsEntity undoactions) {
            this.undoactions = undoactions;
        }

        public double getPrices() {
            return prices;
        }

        public void setPrices(double prices) {
            this.prices = prices;
        }

        public double getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(double order_amount) {
            this.order_amount = order_amount;
        }

        public int getDownpay() {
            return downpay;
        }

        public void setDownpay(int downpay) {
            this.downpay = downpay;
        }

        public List<GoodssectionsEntity> getGoodssections() {
            return goodssections;
        }

        public void setGoodssections(List<GoodssectionsEntity> goodssections) {
            this.goodssections = goodssections;
        }

        public static class UndoactionsEntity {
            private String sectionname;
            /**
             * actname : 订单优惠
             * info : 满59减6;99减12;129减16(不与换购同时享受)
             * acttype : 3
             */

            private List<InfoEntity> info;

            public String getSectionname() {
                return sectionname;
            }

            public void setSectionname(String sectionname) {
                this.sectionname = sectionname;
            }

            public List<InfoEntity> getInfo() {
                return info;
            }

            public void setInfo(List<InfoEntity> info) {
                this.info = info;
            }

            public static class InfoEntity {
                private String actname;
                private String info;
                private int acttype;

                public String getActname() {
                    return actname;
                }

                public void setActname(String actname) {
                    this.actname = actname;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public int getActtype() {
                    return acttype;
                }

                public void setActtype(int acttype) {
                    this.acttype = acttype;
                }
            }
        }

        public static class GoodssectionsEntity {
            private String sectionname;
            private double sgprice;
            private String accessabel;
            /**
             * goods_type : 1
             * up_time : 2016-11-01 12:00:00
             * newprice : 0.00
             * down_time : 2020-11-01 01:00:00
             * is_del : 0
             * unit : 2个
             * newimg : http://imgcdn.xuxian.com/upload/2016/11/01/20161101102730529.jpg
             * id : 599
             * soft_no : 99
             * title : 甜嫩汁水流淌于舌尖
             * price : 15.50
             * icon : http://imgcdn.xuxian.com/upload/2016/11/01/20161101102724525_400_300.jpg
             * store_nums : 2147483647
             * sold_num : 10555
             * market_price : 25.90
             * real_name : 赣南脐橙
             * category_type : 1
             * store_num : 2147473092
             * corver : []
             * selltype :
             * tipsimg : http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229952.png
             * phonetips : http://imgcdn.xuxian.com/upload/2016/05/16/20160516165229971.png
             * starttime : 2016-11-07 01:00:00
             * endtime : 2016-11-14 01:00:00
             * show : 1
             * group_price : [{"goods_id":"599","group_id":"5","price":"14.99","group_name":"VIP"}]
             * count : 1
             * amount : 1
             * store_goods : yes
             * cart_goods_nums : 1
             * total_price_one : 15.5
             * main_name : 赣南脐橙
             */

            private List<GoodslistEntity> goodslist;

            public String getSectionname() {
                return sectionname;
            }

            public void setSectionname(String sectionname) {
                this.sectionname = sectionname;
            }

            public double getSgprice() {
                return sgprice;
            }

            public void setSgprice(double sgprice) {
                this.sgprice = sgprice;
            }

            public String getAccessabel() {
                return accessabel;
            }

            public void setAccessabel(String accessabel) {
                this.accessabel = accessabel;
            }

            public List<GoodslistEntity> getGoodslist() {
                return goodslist;
            }

            public void setGoodslist(List<GoodslistEntity> goodslist) {
                this.goodslist = goodslist;
            }

            public static class GoodslistEntity {
                private String goods_type;
                private String up_time;
                private String newprice;
                private String down_time;
                private String is_del;
                private String unit;
                private String newimg;
                private String id;
                private String soft_no;
                private String title;
                private String price;
                private String icon;
                private String store_nums;
                private String sold_num;
                private String market_price;
                private String real_name;
                private String category_type;
                private int store_num;
                private String selltype;
                private String tipsimg;
                private String phonetips;
                private String starttime;
                private String endtime;
                private String show;
                private String count;
                private String amount;
                private String store_goods;
                private String cart_goods_nums;
                private double total_price_one;
                private String main_name;
                private List<?> corver;
                /**
                 * goods_id : 599
                 * group_id : 5
                 * price : 14.99
                 * group_name : VIP
                 */

                private List<GroupPriceEntity> group_price;

                public String getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(String goods_type) {
                    this.goods_type = goods_type;
                }

                public String getUp_time() {
                    return up_time;
                }

                public void setUp_time(String up_time) {
                    this.up_time = up_time;
                }

                public String getNewprice() {
                    return newprice;
                }

                public void setNewprice(String newprice) {
                    this.newprice = newprice;
                }

                public String getDown_time() {
                    return down_time;
                }

                public void setDown_time(String down_time) {
                    this.down_time = down_time;
                }

                public String getIs_del() {
                    return is_del;
                }

                public void setIs_del(String is_del) {
                    this.is_del = is_del;
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

                public String getSoft_no() {
                    return soft_no;
                }

                public void setSoft_no(String soft_no) {
                    this.soft_no = soft_no;
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

                public String getCategory_type() {
                    return category_type;
                }

                public void setCategory_type(String category_type) {
                    this.category_type = category_type;
                }

                public int getStore_num() {
                    return store_num;
                }

                public void setStore_num(int store_num) {
                    this.store_num = store_num;
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

                public String getShow() {
                    return show;
                }

                public void setShow(String show) {
                    this.show = show;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getStore_goods() {
                    return store_goods;
                }

                public void setStore_goods(String store_goods) {
                    this.store_goods = store_goods;
                }

                public String getCart_goods_nums() {
                    return cart_goods_nums;
                }

                public void setCart_goods_nums(String cart_goods_nums) {
                    this.cart_goods_nums = cart_goods_nums;
                }

                public double getTotal_price_one() {
                    return total_price_one;
                }

                public void setTotal_price_one(double total_price_one) {
                    this.total_price_one = total_price_one;
                }

                public String getMain_name() {
                    return main_name;
                }

                public void setMain_name(String main_name) {
                    this.main_name = main_name;
                }

                public List<?> getCorver() {
                    return corver;
                }

                public void setCorver(List<?> corver) {
                    this.corver = corver;
                }

                public List<GroupPriceEntity> getGroup_price() {
                    return group_price;
                }

                public void setGroup_price(List<GroupPriceEntity> group_price) {
                    this.group_price = group_price;
                }

                public static class GroupPriceEntity {
                    private String goods_id;
                    private String group_id;
                    private String price;
                    private String group_name;

                    public String getGoods_id() {
                        return goods_id;
                    }

                    public void setGoods_id(String goods_id) {
                        this.goods_id = goods_id;
                    }

                    public String getGroup_id() {
                        return group_id;
                    }

                    public void setGroup_id(String group_id) {
                        this.group_id = group_id;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }

                    public String getGroup_name() {
                        return group_name;
                    }

                    public void setGroup_name(String group_name) {
                        this.group_name = group_name;
                    }
                }
            }
        }
    }
}
