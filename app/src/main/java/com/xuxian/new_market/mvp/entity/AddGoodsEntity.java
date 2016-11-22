package com.xuxian.new_market.mvp.entity;

/**
 * Created by youarenotin on 2016/11/11.
 */

public class AddGoodsEntity {

    /**
     * code : 0
     * message : 加入购物车成功
     */

    private StatusEntity status;

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
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
}
