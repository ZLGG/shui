package com.gs.lshly.biz.support.user.mapper.view;

import com.gs.lshly.biz.support.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCardView extends User {


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



}
