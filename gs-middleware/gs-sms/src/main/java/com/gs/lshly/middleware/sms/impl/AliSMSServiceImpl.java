package com.gs.lshly.middleware.sms.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.gs.lshly.common.struct.sms.MessageReq;
import com.gs.lshly.middleware.sms.IAliSMSService;
import com.gs.lshly.middleware.sms.utils.SendMessageUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//@PropertySource("classpath:config.properties")
public class AliSMSServiceImpl implements IAliSMSService {

//	@Value("${ALISMS_ADMIN_SIGNNAME}")
	private String adminSignName ="临港慧管家";
	
//	@Value("${ALISMS_VERICODE_TEMPLET}")
	private String vericodeTemplet ="SMS_205137259";

	@Override
	public Boolean sendRegisterSMS(String telephon, String random6) {
		try {
			JSONObject param = new JSONObject();
			param.put("varicode", random6);
			MessageReq message = new MessageReq(telephon, adminSignName, vericodeTemplet, "" + param);
	
			SendSmsResponse sendSmsResponse = SendMessageUtil.sendSms(message);
		
			String msg = sendSmsResponse.getMessage();
			log.info("[AliSMSServiceImpl][sendRegisterSMS][mes][{}]",msg);
			if ("OK".equalsIgnoreCase(sendSmsResponse.getCode())) {
				return true;
			} else {
				return false;
			}
		} catch (ClientException e) {
			e.printStackTrace();
//			throw new CommonException(ReturnMessage.VARICODE_SEND_ERROR);
			return false;
		}
	}
	
	public static void main(String args[]){
		AliSMSServiceImpl sms = new AliSMSServiceImpl();
		sms.sendRegisterSMS("13675899916","888888");
	}
}
