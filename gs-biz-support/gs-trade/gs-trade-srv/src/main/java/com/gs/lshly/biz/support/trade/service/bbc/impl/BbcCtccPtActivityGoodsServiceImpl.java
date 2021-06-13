package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.CtccActivityGoods;
import com.gs.lshly.biz.support.trade.repository.ICtccActivityGoodsRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcCtccPtActivityGoodsService;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月10日 下午3:18:45
 */
@Component
public class BbcCtccPtActivityGoodsServiceImpl implements IBbcCtccPtActivityGoodsService{
	
	@Autowired
	private ICtccActivityGoodsRepository ctccActivityGoodsRepository;
	
    @DubboReference
	private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
    

	@Override
	public List<BbcGoodsInfoVO.DetailVO> listCtccPtActivityGoods(Integer limit) {
		QueryWrapper<CtccActivityGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_state",GoodsStateEnum.已上架.getCode());
        queryWrapper.orderByAsc("idx");
        queryWrapper.last("limit 0,6");
        List<CtccActivityGoods> list = ctccActivityGoodsRepository.list(queryWrapper);
        List<BbcGoodsInfoVO.DetailVO> retList = new ArrayList<BbcGoodsInfoVO.DetailVO>();
        if(CollectionUtil.isNotEmpty(list)){
        	for(CtccActivityGoods ctccPtActivity:list){
        		String goodsId = ctccPtActivity.getGoodsId();
        		retList.add(bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId)));
        	}
        }
        
        return retList;
	}

	
    
    
    
}
