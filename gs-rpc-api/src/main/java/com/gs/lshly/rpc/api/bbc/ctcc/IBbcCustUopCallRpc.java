package com.gs.lshly.rpc.api.bbc.ctcc;

import java.util.HashMap;

public interface IBbcCustUopCallRpc {
	
	/**
	 * 查询积分余额
	 * @param accNum
	 * @return
	 */
	HashMap<String,Object> qryPointBalance(String accNum);
}

