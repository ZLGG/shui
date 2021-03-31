package com.gs.lshly.rpc.api.bbc.user;

import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月30日 下午11:57:52
 */
public interface IBbcUserCtccPointRpc {
    
	
	BbcUserCtccPointVO.DetailVO getCtccPointByUserId(String userId);
}
