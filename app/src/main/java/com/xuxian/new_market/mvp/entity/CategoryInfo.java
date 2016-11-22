package com.xuxian.new_market.mvp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youarenotin on 2016/10/21.
 */

public class CategoryInfo implements Serializable{

    /**
     * code : 0
     * message : success
     */

    private StatusEntity status;
    /**
     * data : [{"img":"upload/2014/12/30/20141230021627244_100_100.jpg","parent_id":"1","classifyname":"苹果","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624223859922.png","classifyid":"3"},{"img":"upload/2014/12/30/20141230022427463_100_100.jpg","parent_id":"1","classifyname":"香蕉","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624223912417.png","classifyid":"4"},{"img":"upload/2014/12/30/20141230022509964_100_100.jpg","parent_id":"1","classifyname":"火龙果","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224648225.png","classifyid":"6"},{"img":"upload/2015/01/15/20150115062415437_100_100.jpg","parent_id":"1","classifyname":"葡萄","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224632483.png","classifyid":"10"},{"img":"upload/2014/12/30/20141230023651465_100_100.jpg","parent_id":"1","classifyname":"木瓜","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224459951.png","classifyid":"11"},{"img":"upload/2014/08/22/20140822042227797_100_100.jpg","parent_id":"1","classifyname":"橙子","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224407816.png","classifyid":"13"},{"img":"upload/2014/12/30/20141230022406208_100_100.jpg","parent_id":"1","classifyname":"梨","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624223930600.png","classifyid":"38"},{"img":"upload/2014/12/30/20141230023610207_100_100.jpg","parent_id":"1","classifyname":"柠檬","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224426893.png","classifyid":"39"},{"img":"upload/2014/12/30/20141230022548303_100_100.jpg","parent_id":"1","classifyname":"猕猴桃","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224621981.png","classifyid":"42"},{"img":"upload/2016/06/08/20160608081353898_400_300.jpg","parent_id":"1","classifyname":"芒果","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224731308.png","classifyid":"43"},{"img":"upload/2016/06/08/20160608081214119_400_300.jpg","parent_id":"1","classifyname":"荔枝","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224750326.png","classifyid":"45"},{"img":"upload/2014/12/30/20141230022437397_100_100.jpg","parent_id":"1","classifyname":"草莓","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624223810837.png","classifyid":"63"},{"img":"upload/2014/12/30/20141230024354218_100_100.jpg","parent_id":"1","classifyname":"柑橘","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224332683.png","classifyid":"69"},{"img":"upload/2014/08/22/20140822041732601_100_100.jpg","parent_id":"1","classifyname":"甜瓜","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224806747.png","classifyid":"71"},{"img":"upload/2014/12/30/20141230035843526_100_100.jpg","parent_id":"1","classifyname":"其他","icon":"http://imgcdn.xuxian.com/upload/2016/09/26/20160926114932856.png","classifyid":"96"},{"img":"upload/2014/12/30/20141230022258137_100_100.jpg","parent_id":"1","classifyname":"柚","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224220218.png","classifyid":"115"},{"img":"upload/2014/12/30/20141230022356433_100_100.jpg","parent_id":"1","classifyname":"菠萝","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224231329.png","classifyid":"127"},{"img":"upload/2015/01/15/20150115062607820_100_100.jpg","parent_id":"1","classifyname":"哈密瓜","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224245774.png","classifyid":"134"},{"img":"upload/2014/12/30/20141230022200237_100_100.jpg","parent_id":"1","classifyname":"桃子","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224509988.png","classifyid":"135"},{"img":"upload/2014/12/30/20141230022246656_100_100.jpg","parent_id":"1","classifyname":"西瓜","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224522807.png","classifyid":"136"},{"img":"upload/2016/04/08/20160408074810750_400_300.jpg","parent_id":"1","classifyname":"圣女果","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224309329.png","classifyid":"137"},{"img":"upload/2016/06/08/20160608081226361_400_300.jpg","parent_id":"1","classifyname":"山竹","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224355457.png","classifyid":"158"},{"img":"upload/2016/06/08/20160608081248478_400_300.jpg","parent_id":"1","classifyname":"椰子","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224441990.png","classifyid":"159"},{"img":"upload/2016/06/08/20160608081444162_400_300.jpg","parent_id":"1","classifyname":"榴莲","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224536547.png","classifyid":"160"},{"img":"upload/2016/06/08/20160608081517664_400_300.jpg","parent_id":"1","classifyname":"蓝莓","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224555968.png","classifyid":"161"},{"img":"upload/2016/06/12/20160612110509319_400_300.jpg","parent_id":"1","classifyname":"黄瓜","icon":"http://imgcdn.xuxian.com/upload/2016/06/24/20160624224834415.png","classifyid":"163"},{"img":"upload/2016/06/12/20160612110544759_400_300.jpg","parent_id":"1","classifyname":"李杏","icon":"http://imgcdn.xuxian.com/upload/2016/06/28/20160628182113657.png","classifyid":"164"},{"img":"","parent_id":"1","classifyname":"牛油果","icon":"http://imgcdn.xuxian.com/upload/2016/09/26/20160926115059101.png","classifyid":"206"},{"img":"","parent_id":"1","classifyname":"枣","icon":"http://imgcdn.xuxian.com/upload/2016/09/29/20160929180823922.png","classifyid":"207"},{"img":"","parent_id":"1","classifyname":"石榴","icon":"http://imgcdn.xuxian.com/upload/2016/09/29/20160929180901630.png","classifyid":"208"}]
     * name : 水果
     */

    private List<DataEntity> data;

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
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
        private String name;
        /**
         * img : upload/2014/12/30/20141230021627244_100_100.jpg
         * parent_id : 1
         * classifyname : 苹果
         * icon : http://imgcdn.xuxian.com/upload/2016/06/24/20160624223859922.png
         * classifyid : 3
         */

        private List<DataEntity1> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DataEntity1> getData() {
            return data;
        }

        public void setData(List<DataEntity1> data) {
            this.data = data;
        }

        public static class DataEntity1 {
            private String img;
            private String parent_id;
            private String classifyname;
            private String icon;
            private String classifyid;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getClassifyname() {
                return classifyname;
            }

            public void setClassifyname(String classifyname) {
                this.classifyname = classifyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getClassifyid() {
                return classifyid;
            }

            public void setClassifyid(String classifyid) {
                this.classifyid = classifyid;
            }
        }
    }
}
