package com.gs.lshly.biz.support.commodity.service.test.impl;

import cn.hutool.json.JSONUtil;
import com.gs.lshly.biz.support.commodity.elasticsearch.document.EsGoodsInfo;
import com.gs.lshly.biz.support.commodity.elasticsearch.respository.EsGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.service.test.IESTestSrv;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class ESTestSrvImpl implements IESTestSrv {

    @Autowired
    EsGoodsInfoRepository esGoodsInfoRepository;

    @Autowired
    IGoodsInfoRepository goodsInfoRepository;

    @Override
    public void importGoodsInfoById(String goodsId) {
        GoodsInfo goodsInfo = goodsInfoRepository.getById(goodsId);
        if (goodsInfo == null) {
            throw new BusinessException("未找到商品");
        }
        EsGoodsInfo esGoodsInfo = new EsGoodsInfo();
        BeanCopyUtils.copyProperties(goodsInfo, esGoodsInfo);
        log.info("import:" + JSONUtil.toJsonPrettyStr(esGoodsInfo));
        esGoodsInfoRepository.save(esGoodsInfo);
    }

    @Override
    public List<BbcGoodsInfoVO.GoodsListVO> searchByNameOrTitle(String name, String title) {
        Page<EsGoodsInfo> esList = esGoodsInfoRepository.findByGoodsNameOrGoodsTitle(name, title, Pageable.unpaged());
        log.info("es:"+JSONUtil.toJsonPrettyStr(esList));
        List<BbcGoodsInfoVO.GoodsListVO> list = new ArrayList<>();
        for (EsGoodsInfo es : esList) {
            BbcGoodsInfoVO.GoodsListVO goodsInfo = new BbcGoodsInfoVO.GoodsListVO();
            BeanCopyUtils.copyProperties(es, goodsInfo);
            list.add(goodsInfo);
        }
        log.info("list:"+JSONUtil.toJsonPrettyStr(list));
        return list;
    }
}
