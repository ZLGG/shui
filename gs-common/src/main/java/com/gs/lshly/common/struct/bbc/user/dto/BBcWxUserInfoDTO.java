package com.gs.lshly.common.struct.bbc.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BBcWxUserInfoDTO implements Serializable {

    private String appId;

    private String openId;
    private String nickName;
    /**
     * 性别 0：未知、1：男、2：女
     */
    private String gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;

}
