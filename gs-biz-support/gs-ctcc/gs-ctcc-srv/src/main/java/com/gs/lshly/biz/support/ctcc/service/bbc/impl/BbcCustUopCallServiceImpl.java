package com.gs.lshly.biz.support.ctcc.service.bbc.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gs.lshly.biz.support.ctcc.service.bbc.IBbcCustUopCallService;
import com.zmq.service.ProvideService;

/**
* <p>
*  服务实现类
* </p>
* @author zzg
* @since 2020-11-02
*/
@Component
public class BbcCustUopCallServiceImpl implements IBbcCustUopCallService {

	@Reference(registry = "pay")
    private ProvideService provideService;
	
	@Override
	public String qryPointBalance(String accNum) {
		return provideService.sayHello("1234");
	}

   
}
