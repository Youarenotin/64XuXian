package com.xuxian.new_market.mvp.entity;

import java.util.List;

/**
 * Created by youarenotin on 2016/10/24.
 */

public class IndexConfig {
    /**
     * code : 0
     * message : success
     */

    private StatusEntity status;
    /**
     * search : []
     * section_info : [{"id":"186","name":"首页","parent_id":"139","sort":"1","app_img":""},{"id":"157","name":"轻食","parent_id":"139","sort":"5","app_img":""},{"id":"140","name":"水果","parent_id":"139","sort":"10","app_img":""},{"id":"180","name":"便当","parent_id":"139","sort":"15","app_img":""},{"id":"199","name":"乳品","parent_id":"139","sort":"20","app_img":""},{"id":"200","name":"零食","parent_id":"139","sort":"30","app_img":""},{"id":"209","name":"干果","parent_id":"139","sort":"35","app_img":""},{"id":"153","name":"礼品","parent_id":"139","sort":"50","app_img":""},{"id":"147","name":"全球尖货","parent_id":"139","sort":"60","app_img":""},{"id":"144","name":"储值卡","parent_id":"139","sort":"70","app_img":""}]
     * new_store : 0
     * config_img : {"first":{"img":"","clickimg":"","name":"首页"},"cart":{"img":"","clickimg":"","name":"购物车"},"allsend":{"img":"","clickimg":"","name":"全国送","link":"http://mobile.xuxian.com/goods/getallsend/"},"my":{"img":"","clickimg":"","name":"我的"},"left":{"sendimg":"","tiimg":"g"},"classify":{"img":"http://imgcdn.xuxian.com/upload/app/2016082601/fenlei.png"},"search":{"img":"http://imgcdn.xuxian.com/upload/app/2016082601/search.png"},"gongge":{"img":"","clickimg":"","message":""},"scan_code":{"img":"","name":"扫码"}}
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
        private int new_store;
        /**
         * first : {"img":"","clickimg":"","name":"首页"}
         * cart : {"img":"","clickimg":"","name":"购物车"}
         * allsend : {"img":"","clickimg":"","name":"全国送","link":"http://mobile.xuxian.com/goods/getallsend/"}
         * my : {"img":"","clickimg":"","name":"我的"}
         * left : {"sendimg":"","tiimg":"g"}
         * classify : {"img":"http://imgcdn.xuxian.com/upload/app/2016082601/fenlei.png"}
         * search : {"img":"http://imgcdn.xuxian.com/upload/app/2016082601/search.png"}
         * gongge : {"img":"","clickimg":"","message":""}
         * scan_code : {"img":"","name":"扫码"}
         */

        private ConfigImgEntity config_img;
        private List<?> search;
        /**
         * id : 186
         * name : 首页
         * parent_id : 139
         * sort : 1
         * app_img :
         */

        private List<SectionInfoEntity> section_info;

        public int getNew_store() {
            return new_store;
        }

        public void setNew_store(int new_store) {
            this.new_store = new_store;
        }

        public ConfigImgEntity getConfig_img() {
            return config_img;
        }

        public void setConfig_img(ConfigImgEntity config_img) {
            this.config_img = config_img;
        }

        public List<?> getSearch() {
            return search;
        }

        public void setSearch(List<?> search) {
            this.search = search;
        }

        public List<SectionInfoEntity> getSection_info() {
            return section_info;
        }

        public void setSection_info(List<SectionInfoEntity> section_info) {
            this.section_info = section_info;
        }

        public static class ConfigImgEntity {
            /**
             * img :
             * clickimg :
             * name : 首页
             */

            private FirstEntity first;
            /**
             * img :
             * clickimg :
             * name : 购物车
             */

            private CartEntity cart;
            /**
             * img :
             * clickimg :
             * name : 全国送
             * link : http://mobile.xuxian.com/goods/getallsend/
             */

            private AllsendEntity allsend;
            /**
             * img :
             * clickimg :
             * name : 我的
             */

            private MyEntity my;
            /**
             * sendimg :
             * tiimg : g
             */

            private LeftEntity left;
            /**
             * img : http://imgcdn.xuxian.com/upload/app/2016082601/fenlei.png
             */

            private ClassifyEntity classify;
            /**
             * img : http://imgcdn.xuxian.com/upload/app/2016082601/search.png
             */

            private SearchEntity search;
            /**
             * img :
             * clickimg :
             * message :
             */

            private GonggeEntity gongge;
            /**
             * img :
             * name : 扫码
             */

            private ScanCodeEntity scan_code;

            public FirstEntity getFirst() {
                return first;
            }

            public void setFirst(FirstEntity first) {
                this.first = first;
            }

            public CartEntity getCart() {
                return cart;
            }

            public void setCart(CartEntity cart) {
                this.cart = cart;
            }

            public AllsendEntity getAllsend() {
                return allsend;
            }

            public void setAllsend(AllsendEntity allsend) {
                this.allsend = allsend;
            }

            public MyEntity getMy() {
                return my;
            }

            public void setMy(MyEntity my) {
                this.my = my;
            }

            public LeftEntity getLeft() {
                return left;
            }

            public void setLeft(LeftEntity left) {
                this.left = left;
            }

            public ClassifyEntity getClassify() {
                return classify;
            }

            public void setClassify(ClassifyEntity classify) {
                this.classify = classify;
            }

            public SearchEntity getSearch() {
                return search;
            }

            public void setSearch(SearchEntity search) {
                this.search = search;
            }

            public GonggeEntity getGongge() {
                return gongge;
            }

            public void setGongge(GonggeEntity gongge) {
                this.gongge = gongge;
            }

            public ScanCodeEntity getScan_code() {
                return scan_code;
            }

            public void setScan_code(ScanCodeEntity scan_code) {
                this.scan_code = scan_code;
            }

            public static class FirstEntity {
                private String img;
                private String clickimg;
                private String name;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getClickimg() {
                    return clickimg;
                }

                public void setClickimg(String clickimg) {
                    this.clickimg = clickimg;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class CartEntity {
                private String img;
                private String clickimg;
                private String name;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getClickimg() {
                    return clickimg;
                }

                public void setClickimg(String clickimg) {
                    this.clickimg = clickimg;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class AllsendEntity {
                private String img;
                private String clickimg;
                private String name;
                private String link;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getClickimg() {
                    return clickimg;
                }

                public void setClickimg(String clickimg) {
                    this.clickimg = clickimg;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }
            }

            public static class MyEntity {
                private String img;
                private String clickimg;
                private String name;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getClickimg() {
                    return clickimg;
                }

                public void setClickimg(String clickimg) {
                    this.clickimg = clickimg;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class LeftEntity {
                private String sendimg;
                private String tiimg;

                public String getSendimg() {
                    return sendimg;
                }

                public void setSendimg(String sendimg) {
                    this.sendimg = sendimg;
                }

                public String getTiimg() {
                    return tiimg;
                }

                public void setTiimg(String tiimg) {
                    this.tiimg = tiimg;
                }
            }

            public static class ClassifyEntity {
                private String img;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

            public static class SearchEntity {
                private String img;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

            public static class GonggeEntity {
                private String img;
                private String clickimg;
                private String message;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getClickimg() {
                    return clickimg;
                }

                public void setClickimg(String clickimg) {
                    this.clickimg = clickimg;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }
            }

            public static class ScanCodeEntity {
                private String img;
                private String name;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class SectionInfoEntity {
            private String id;
            private String name;
            private String parent_id;
            private String sort;
            private String app_img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getApp_img() {
                return app_img;
            }

            public void setApp_img(String app_img) {
                this.app_img = app_img;
            }
        }
    }
}
