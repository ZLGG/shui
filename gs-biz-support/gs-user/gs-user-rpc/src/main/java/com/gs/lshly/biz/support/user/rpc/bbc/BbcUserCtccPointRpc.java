package com.gs.lshly.biz.support.user.rpc.bbc;

import java.math.BigDecimal;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.user.service.bbc.IBbcUserCtccPointService;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserCtccPointDTO.CreateCtccPointDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserCtccPointDTO.SubCtccPointDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO.DetailVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserCtccPointRpc;


/**
 * 电信积分
 *
 * 
 * @author yingjun
 * @date 2021年3月30日 下午11:56:47
 */
@DubboService
public class BbcUserCtccPointRpc implements IBbcUserCtccPointRpc {

    @Autowired
    private IBbcUserCtccPointService bbcUserCtccPointService;

	@Override
	public DetailVO getCtccPointByUserId(String userId) {
		return bbcUserCtccPointService.getCtccPointByUserId(userId);
	}

	@Override
	public void subCtccPoint(SubCtccPointDTO dto) {
		bbcUserCtccPointService.subCtccPoint(dto);
		
	}

	@Override
	public void addCtccPoint(String userId,BigDecimal point) {
		bbcUserCtccPointService.addCtccPoint(userId,point);
		
	}

	@Override
	public void createCtccPoint(CreateCtccPointDTO dto) {
		bbcUserCtccPointService.createCtccPoint(dto);
		
	}
	
	

    
}
