package com.gs.lshly.biz.support.user.mapper.view;

import lombok.Data;

@Data
public class UserLabelView {

    /**
     * 会员ID
     */
    private String user_id;

    /**
     * 标签ID
     */
    private String label_id;

    /**
     * 标签名称
     */
    private String lable_name;

    /**
     * 标签颜色
     */
    private String lable_color;
}
