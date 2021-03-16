package com.gs.lshly.biz.support.commodity.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsLabel;
import com.gs.lshly.biz.support.commodity.entity.GoodsRelationLabel;
import com.gs.lshly.biz.support.commodity.repository.IGoodsLabelRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsRelationLabelRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsLabelService;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsLabelQTO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-11-11
*/
@Component
public class BbbH5GoodsLabelServiceImpl implements IBbbH5GoodsLabelService {

    @Autowired
    private IGoodsLabelRepository repository;

    @Autowired
    private IGoodsRelationLabelRepository relationLabelRepository;


    @Override
    public List<String> listGoodsId(BbbH5GoodsLabelQTO.QTO qto) {

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
}
