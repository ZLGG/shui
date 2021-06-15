package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeInfoTemp;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeInfoTempRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeInfoTempService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeInfoVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chenyang
 */
@Component
public class GoodsAttributeInfoTempServiceImpl implements IGoodsAttributeInfoTempService {

    @Autowired
    private IGoodsAttributeInfoTempRepository repository;

    @Override
    public String addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO dto) {
        GoodsAttributeInfoTemp goodsAttributeInfo = new GoodsAttributeInfoTemp();
        BeanUtils.copyProperties(dto, goodsAttributeInfo);
        goodsAttributeInfo.setCdate(new Date());
        goodsAttributeInfo.setUdate(new Date());
        goodsAttributeInfo.setFlag(false);
        repository.save(goodsAttributeInfo);
        return goodsAttributeInfo.getId();
    }

    @Override
    public List<PCMerchGoodsAttributeInfoVO.ListVO> getListVO(PCMerchGoodsAttributeInfoDTO.GoodIdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsAttributeInfoTemp> boost = MybatisPlusUtil.query();
        boost.eq("good_id",dto.getGoodId());
        List<GoodsAttributeInfoTemp> attributeInfos = repository.list(boost);
        if (ObjectUtils.isNotEmpty(attributeInfos)){
            List<PCMerchGoodsAttributeInfoVO.ListVO> listVOS = new ArrayList<>();
            for (GoodsAttributeInfoTemp goodsAttributeInfo : attributeInfos){
                PCMerchGoodsAttributeInfoVO.ListVO listVO = new PCMerchGoodsAttributeInfoVO.ListVO();
                BeanUtils.copyProperties(goodsAttributeInfo,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }
}
