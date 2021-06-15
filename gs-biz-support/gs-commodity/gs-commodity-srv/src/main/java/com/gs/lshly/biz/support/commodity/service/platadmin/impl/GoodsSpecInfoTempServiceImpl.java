package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfoTemp;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoTempRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecInfoTempService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecInfoVO;
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
public class GoodsSpecInfoTempServiceImpl implements IGoodsSpecInfoTempService {

    @Autowired
    private IGoodsSpecInfoTempRepository repository;

    @Override
    public String addGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto) {
        GoodsSpecInfoTemp goodsSpecInfo = new GoodsSpecInfoTemp();
        BeanUtils.copyProperties(eto, goodsSpecInfo);
        goodsSpecInfo.setCdate(new Date());
        goodsSpecInfo.setUdate(new Date());
        goodsSpecInfo.setFlag(false);
        repository.save(goodsSpecInfo);
        return goodsSpecInfo.getId();
    }

    @Override
    public List<PCMerchGoodsSpecInfoVO.ListVO> specInfoListVO(PCMerchGoodsSpecInfoDTO.GoodIdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsSpecInfoTemp> boost = MybatisPlusUtil.query();
        boost.eq("good_id",dto.getGoodId());
        List<GoodsSpecInfoTemp> specInfos = repository.list(boost);
        if (ObjectUtils.isNotEmpty(specInfos)){
            List<PCMerchGoodsSpecInfoVO.ListVO> listVOS = new ArrayList<>();
            for (GoodsSpecInfoTemp goodsSpecInfo : specInfos){
                PCMerchGoodsSpecInfoVO.ListVO listVO = new PCMerchGoodsSpecInfoVO.ListVO();
                BeanUtils.copyProperties(goodsSpecInfo,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }
}
