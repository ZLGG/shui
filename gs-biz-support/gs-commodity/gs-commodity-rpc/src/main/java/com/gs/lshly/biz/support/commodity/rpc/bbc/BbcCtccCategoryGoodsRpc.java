package com.gs.lshly.biz.support.commodity.rpc.bbc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.commodity.service.bbc.IBbcCtccCategoryGoodsService;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO.DTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalHomeVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcCtccCategoryGoodsRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月20日 下午2:44:22
 */
@DubboService
public class BbcCtccCategoryGoodsRpc implements IBbcCtccCategoryGoodsRpc {
    
	@Autowired
    private IBbcCtccCategoryGoodsService bbcCtccCategoryGoodsService;

	@Override
	public CtccInternationalHomeVO ctccInternationalHomeVO(DTO dto) {
		return bbcCtccCategoryGoodsService.ctccInternationalHome(dto);
	}


}