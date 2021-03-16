package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsExtendParams;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecInfoService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsSpecInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecInfoVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-09
*/
@Component
public class GoodsSpecInfoServiceImpl implements IGoodsSpecInfoService {

    @Autowired
    private IGoodsSpecInfoRepository repository;

    @Override
    public PageData<PCMerchGoodsSpecInfoVO.ListVO> pageData(PCMerchGoodsSpecInfoQTO.QTO qto) {
        QueryWrapper<GoodsSpecInfo> wq = new QueryWrapper<>();
        IPage<GoodsSpecInfo> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, PCMerchGoodsSpecInfoVO.ListVO.class, page);
    }

    @Override
    public String addGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto) {
        GoodsSpecInfo goodsSpecInfo = new GoodsSpecInfo();
        BeanUtils.copyProperties(eto, goodsSpecInfo);
        repository.save(goodsSpecInfo);
        return goodsSpecInfo.getId();
    }


    @Override
    public void deleteGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto) {
        GoodsSpecInfo goodsSpecInfo = new GoodsSpecInfo();
        BeanUtils.copyProperties(eto, goodsSpecInfo);
        repository.updateById(goodsSpecInfo);
    }

    @Override
    public PCMerchGoodsSpecInfoVO.DetailVO detailGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto) {
        GoodsSpecInfo goodsSpecInfo = repository.getById(dto.getId());
        PCMerchGoodsSpecInfoVO.DetailVO detailVo = new PCMerchGoodsSpecInfoVO.DetailVO();
        if(ObjectUtils.isEmpty(goodsSpecInfo)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(goodsSpecInfo, detailVo);
        return detailVo;
    }

    @Override
    public List<PCMerchGoodsSpecInfoVO.ListVO> specInfoListVO(PCMerchGoodsSpecInfoDTO.GoodIdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsSpecInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id",dto.getGoodId());
        List<GoodsSpecInfo> specInfos = repository.list(boost);
        if (ObjectUtils.isNotEmpty(specInfos)){
            List<PCMerchGoodsSpecInfoVO.ListVO> listVOS = new ArrayList<>();
            for (GoodsSpecInfo goodsSpecInfo : specInfos){
                PCMerchGoodsSpecInfoVO.ListVO listVO = new PCMerchGoodsSpecInfoVO.ListVO();
                BeanUtils.copyProperties(goodsSpecInfo,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }

}
