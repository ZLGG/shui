package com.gs.lshly.common.struct.bbc.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BBcWxUserPhoneDTO implements Serializable {

    private String appId;
    private String openId;
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
}
