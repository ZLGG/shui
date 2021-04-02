package com.gs.lshly.biz.support.ctcc.rpc.bbc;
import java.util.HashMap;

import org.apache.dubbo.config.annotation.DubboService;

import com.gs.lshly.rpc.api.bbc.ctcc.IBbcCustUopCallRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月2日 下午3:34:17
 */
@DubboService
public class BbcCustUopCallRpc implements IBbcCustUopCallRpc{
	
	

	@Override
	public HashMap<String, Object> qryPointBalance(String accNum) {
		// TODO Auto-generated method stub
		return null;
	}

    

}