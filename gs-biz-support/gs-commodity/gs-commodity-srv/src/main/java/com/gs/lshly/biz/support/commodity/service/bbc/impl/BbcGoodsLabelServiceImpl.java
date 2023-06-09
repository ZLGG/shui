package com.gs.lshly.biz.support.commodity.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsLabel;
import com.gs.lshly.biz.support.commodity.entity.GoodsRelationLabel;
import com.gs.lshly.biz.support.commodity.repository.IGoodsLabelRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsRelationLabelRepository;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsLabelService;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsLabelQTO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-11-11
*/
@Component
public class BbcGoodsLabelServiceImpl implements IBbcGoodsLabelService {

    @Autowired
    private IGoodsLabelRepository repository;

    @Autowired
    private IGoodsRelationLabelRepository relationLabelRepository;


    @Override
    public List<String> listGoodsId(BbcGoodsLabelQTO.QTO qto) {

        QueryWrapper<GoodsLabel> boost = MybatisPlusUtil.query();
        boost.eq("label_name","推荐");
        GoodsLabel labels = repository.getOne(boost);
        if (ObjectUtils.isEmpty(labels)){
            return new ArrayList<>();
        }
        //查询商品与标签建立的列表
        QueryWrapper<GoodsRelationLabel> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("label_id",labels.getId());
        List<GoodsRelationLabel> goodsRelationLabels = relationLabelRepository.list(wrapperBoost);
        if (ObjectUtils.isNotEmpty(goodsRelationLabels)){
            List<String> strings = ListUtil.getIdList(GoodsRelationLabel.class,goodsRelationLabels,"goodsId");
            return strings;
        }
        return new ArrayList<>();
    }


	@Override
	public List<String> listGoodsLabelByGoodsId(String goodsId) {
		List<String> retList =new ArrayList<String>();
		QueryWrapper<GoodsRelationLabel> boost = MybatisPlusUtil.query();
        boost.eq("goods_id",goodsId);
        List<GoodsRelationLabel> list = relationLabelRepository.list(boost);
        if(CollectionUtils.isNotEmpty(list)){
        	for(GoodsRelationLabel goodsRelationLabel:list){
        		String labelId = goodsRelationLabel.getLabelId();
        		GoodsLabel goodsLabel = repository.getById(labelId);
        		if(goodsLabel!=null)
        			retList.add(goodsLabel.getLabelName());
        	}
        }
		return retList;
	}
}
