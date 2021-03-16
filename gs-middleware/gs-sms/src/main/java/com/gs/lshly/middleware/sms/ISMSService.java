package com.gs.lshly.middleware.sms;

/**
 * 短信服务
 * @author lxus
 * @since 2020/11/07
 */
public interface ISMSService {

    String sendRegistrySMSCode(String phone);

    String sendLoginSMSCode(String phone);

    String sendPickUpSMSCode(String phone,String pickUpNum,String name);

    String sendSettlementSMSCode(String phone,String username,String pwd);

    String sendSettlementFaildSMSCode(String phone,String shopName,String siteName,String reason);

    String sendSettlementInformSMSCode(String phone,String shopName);
}
