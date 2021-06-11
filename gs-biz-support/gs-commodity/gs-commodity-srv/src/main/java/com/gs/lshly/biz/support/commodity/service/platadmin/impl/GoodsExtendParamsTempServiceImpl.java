package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsExtendParams;
import com.gs.lshly.biz.support.commodity.entity.GoodsExtendParamsTemp;
import com.gs.lshly.biz.support.commodity.repository.IGoodsExtendParamsRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsExtendParamsTempRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsExtendParamsTempService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenyang
 */
@Component
public class GoodsExtendParamsTempServiceImpl implements IGoodsExtendParamsTempService {


    @Autowired
    private IGoodsExtendParamsTempRepository repository;

    @Override
    public String addGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto) {
        GoodsExtendParamsTemp goodsExtendParams = new GoodsExtendParamsTemp();
        BeanUtils.copyProperties(eto, goodsExtendParams);
        repository.save(goodsExtendParams);
        return goodsExtendParams.getId();
    }

    @Override
    public List<PCMerchGoodsExtendParamsVO.ListVO> extendListVO(PCMerchGoodsExtendParamsDTO.GoodIdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsExtendParamsTemp> boost = MybatisPlusUtil.query();
        boost.eq("good_id",dto.getGoodId());
        List<GoodsExtendParamsTemp> goodsExtendParams = repository.list(boost);
        if (ObjectUtils.isNotEmpty(goodsExtendParams)){
            List<PCMerchGoodsExtendParamsVO.ListVO> listVOS = new ArrayList<>();
            for (GoodsExtendParamsTemp extendParams : goodsExtendParams){
                PCMerchGoodsExtendParamsVO.ListVO listVO = new PCMerchGoodsExtendParamsVO.ListVO();
                BeanUtils.copyProperties(extendParams,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }
}
