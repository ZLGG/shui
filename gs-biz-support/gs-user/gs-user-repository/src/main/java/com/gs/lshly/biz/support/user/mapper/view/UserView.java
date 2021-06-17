package com.gs.lshly.biz.support.user.mapper.view;

import com.gs.lshly.biz.support.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserView extends User {


    /**
     * 标签名称
     */
    private String labelNames;

    /**
     * 标签ID
     */
    private String labelIds;

    /**
     * 标签颜色
     */
    private String colors;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 积分
     */
    private Integer pointBalance;


}
