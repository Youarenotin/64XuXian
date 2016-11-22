package com.xuxian.new_market.mvp.entity;

import java.util.List;

/**
 * Created by youarenotin on 2016/10/25.
 */

public class SectionBlocksEntity {
    /**
     * code : 0
     * message : success
     */

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
        /**
         * id : 2042
         * city_id : 110000
         * starttime : 1475168400
         * endtime : 1601398800
         * width : 1.00
         * height : 0.35
         * a_position : 0_0
         * sort : 210
         * sa_id : 765,776,782,783,785,786
         * allheight : 0.35
         * show_type : 2
         * intro : 北京mini店banner
         * is_com : 1
         * p_sort : 0
         * sa_sort : 30,30,40,80,10,5
         * bannertype : 8
         * sale_as : [{"starttime":"2016-10-25 01:00:00","endtime":"2016-10-27 01:00:00","bannertype":"1","message":"851","mode_id":"0","bannerimg":"http://imgcdn.xuxian.com/upload/2016/10/25/20161025134250929.jpg","sale_name":"北京进口蓝莓banner","is_time":"0","is_directlyadd":"0"},{"starttime":"2016-10-21 11:22:54","endtime":"2020-10-19 01:00:00","bannertype":"8","message":"http://mobile.xuxian.com/banner/getActivityDetail/765/1266?ver=2016083101","mode_id":"0","bannerimg":"http://imgcdn.xuxian.com/upload/2016/10/25/20161025101054752.jpg","sale_name":"幂滋巧克力banner","is_time":"0","is_directlyadd":"0"},{"starttime":"2016-10-21 01:00:00","endtime":"2016-10-31 01:00:00","bannertype":"8","message":"http://mobile.xuxian.com/banner/getActivityDetail/776/1266?ver=2016083101","mode_id":"0","bannerimg":"http://imgcdn.xuxian.com/upload/2016/10/25/20161025122042989.jpg","sale_name":"暖心茶banner","is_time":"0","is_directlyadd":"0"},{"starttime":"2016-10-24 01:00:00","endtime":"2016-10-26 01:00:00","bannertype":"1","message":"620","mode_id":"0","bannerimg":"http://imgcdn.xuxian.com/upload/2016/10/24/20161024103759946.jpg","sale_name":"北京徐香猕猴桃banner","is_time":"0","is_directlyadd":"0"},{"starttime":"2016-10-24 01:00:00","endtime":"2016-11-01 01:00:00","bannertype":"8","message":"http://www.xuxian.com/index.php?controller=game_thousand&action=index&city_id=110000","mode_id":"0","bannerimg":"http://imgcdn.xuxian.com/upload/2016/10/24/20161024163238302.jpg","sale_name":"北京即买即送活动banner","is_time":"0","is_directlyadd":"0"}]
         */

        private List<List<BlocksEntity>> blocks;

        public List<List<BlocksEntity>> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<List<BlocksEntity>> blocks) {
            this.blocks = blocks;
        }

        public static class BlocksEntity {
            private String id;
            private String city_id;
            private String starttime;
            private String endtime;
            private String width;
            private String height;
            private String a_position;
            private String sort;
            private String sa_id;
            private String allheight;
            private String show_type;
            private String intro;
            private String is_com;
            private String p_sort;
            private String sa_sort;
            private String bannertype;
            /**
             * starttime : 2016-10-25 01:00:00
             * endtime : 2016-10-27 01:00:00
             * bannertype : 1
             * message : 851
             * mode_id : 0
             * bannerimg : http://imgcdn.xuxian.com/upload/2016/10/25/20161025134250929.jpg
             * sale_name : 北京进口蓝莓banner
             * is_time : 0
             * is_directlyadd : 0
             */

            private List<SaleAsEntity> sale_as;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
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

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getA_position() {
                return a_position;
            }

            public void setA_position(String a_position) {
                this.a_position = a_position;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getSa_id() {
                return sa_id;
            }

            public void setSa_id(String sa_id) {
                this.sa_id = sa_id;
            }

            public String getAllheight() {
                return allheight;
            }

            public void setAllheight(String allheight) {
                this.allheight = allheight;
            }

            public String getShow_type() {
                return show_type;
            }

            public void setShow_type(String show_type) {
                this.show_type = show_type;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getIs_com() {
                return is_com;
            }

            public void setIs_com(String is_com) {
                this.is_com = is_com;
            }

            public String getP_sort() {
                return p_sort;
            }

            public void setP_sort(String p_sort) {
                this.p_sort = p_sort;
            }

            public String getSa_sort() {
                return sa_sort;
            }

            public void setSa_sort(String sa_sort) {
                this.sa_sort = sa_sort;
            }

            public String getBannertype() {
                return bannertype;
            }

            public void setBannertype(String bannertype) {
                this.bannertype = bannertype;
            }

            public List<SaleAsEntity> getSale_as() {
                return sale_as;
            }

            public void setSale_as(List<SaleAsEntity> sale_as) {
                this.sale_as = sale_as;
            }

            public static class SaleAsEntity {
                private String starttime;
                private String endtime;
                private String bannertype;
                private String message;
                private String mode_id;
                private String bannerimg;
                private String sale_name;
                private String is_time;
                private String is_directlyadd;

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

                public String getBannertype() {
                    return bannertype;
                }

                public void setBannertype(String bannertype) {
                    this.bannertype = bannertype;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getMode_id() {
                    return mode_id;
                }

                public void setMode_id(String mode_id) {
                    this.mode_id = mode_id;
                }

                public String getBannerimg() {
                    return bannerimg;
                }

                public void setBannerimg(String bannerimg) {
                    this.bannerimg = bannerimg;
                }

                public String getSale_name() {
                    return sale_name;
                }

                public void setSale_name(String sale_name) {
                    this.sale_name = sale_name;
                }

                public String getIs_time() {
                    return is_time;
                }

                public void setIs_time(String is_time) {
                    this.is_time = is_time;
                }

                public String getIs_directlyadd() {
                    return is_directlyadd;
                }

                public void setIs_directlyadd(String is_directlyadd) {
                    this.is_directlyadd = is_directlyadd;
                }
            }
        }
    }
}
