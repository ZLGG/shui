package com.gs.lshly.rpc.api.bbc.ctcc;

import java.util.HashMap;

public interface IBbcCustUopCallRpc {
	
	HashMap<String,Object> qryPointBalance(String accNum);
}

