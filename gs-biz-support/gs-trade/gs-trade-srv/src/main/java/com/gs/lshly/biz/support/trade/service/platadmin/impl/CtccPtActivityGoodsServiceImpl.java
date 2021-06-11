package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.mapper.CtccCategoryMapper;
import com.gs.lshly.biz.support.trade.mapper.CtccPtActivityImagesMapper;
import com.gs.lshly.biz.support.trade.mapper.CtccPtActivityMapper;
import com.gs.lshly.biz.support.trade.repository.ICtccActivityGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccPtActivityRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityGoodsService;
import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityService;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author hanly
 * @create 2021/6/2 9:55
 */
@Component
public class CtccPtActivityGoodsServiceImpl implements ICtccPtActivityGoodsService {

    @Autowired
    private ICtccActivityGoodsRepository ctccActivityGoodsRepository;

    @Override
    public List<String> getList() {
        QueryWrapper<CtccActivityGoods> query = MybatisPlusUtil.query();
        List<CtccActivityGoods> activityGoods = ctccActivityGoodsRepository.list(query);
        List<String> goodsIdList = CollUtil.getFieldValues(activityGoods, "goodsId", String.class);
        return goodsIdList;
    }

	@Override
	public void underStateByGoodsId(List<String> goodsIds) {
		 QueryWrapper<CtccActivityGoods> query = MybatisPlusUtil.query();
		 query.in("goods_id", goodsIds);
		 List<CtccActivityGoods> list = ctccActivityGoodsRepository.list(query);
		 if(CollectionUtil.isNotEmpty(list)){
			 for(CtccActivityGoods goods:list){
				 goods.setGoodsState(10);
				 ctccActivityGoodsRepository.saveOrUpdate(goods);
			 }
		 }
		
	}
}
