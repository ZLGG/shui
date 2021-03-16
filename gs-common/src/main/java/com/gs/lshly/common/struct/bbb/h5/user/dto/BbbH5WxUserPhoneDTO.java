package com.gs.lshly.common.struct.bbb.h5.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BbbH5WxUserPhoneDTO implements Serializable {

    private String appId;
    private String openId;
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
}
