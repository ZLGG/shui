package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:11 2020/10/16
 * 是否将商品咨询展示在商品详情页
 */
public enum ShowQuizStateEnum implements EnumMessage{

        不显示(10,"不显示"),
        显示(20,"显示");

        private Integer code;
        private String remark;

    ShowQuizStateEnum(Integer code, String remark) {
            this.code = code;
            this.remark = remark;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getRemark() {
            return remark;
        }

}
