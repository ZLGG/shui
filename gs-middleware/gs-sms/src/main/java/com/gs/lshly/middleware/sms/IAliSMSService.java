package com.gs.lshly.middleware.sms;

public interface IAliSMSService {

	/**
	 * 发送阿里云短信
	 * @param telephon
	 * @param random6
	 * @return
	 */
	Boolean sendRegisterSMS(String telephon,String random6);

}
