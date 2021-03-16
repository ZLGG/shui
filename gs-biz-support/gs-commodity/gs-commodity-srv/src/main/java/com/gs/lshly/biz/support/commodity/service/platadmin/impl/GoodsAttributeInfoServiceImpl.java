package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeInfoRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeInfoService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsAttributeInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeInfoVO;
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
public class GoodsAttributeInfoServiceImpl implements IGoodsAttributeInfoService {

    @Autowired
    private IGoodsAttributeInfoRepository repository;

    @Override
    public PageData<PCMerchGoodsAttributeInfoVO.ListVO> pageData(PCMerchGoodsAttributeInfoQTO.QTO qto) {
        QueryWrapper<GoodsAttributeInfo> wq = new QueryWrapper<>();
        IPage<GoodsAttributeInfo> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, PCMerchGoodsAttributeInfoVO.ListVO.class, page);
    }

    @Override
    public String addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO eto) {
        GoodsAttributeInfo goodsAttributeInfo = new GoodsAttributeInfo();
        BeanUtils.copyProperties(eto, goodsAttributeInfo);
        repository.save(goodsAttributeInfo);
        return goodsAttributeInfo.getId();
    }


    @Override
    public void deleteGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO eto) {
        GoodsAttributeInfo goodsAttributeInfo = new GoodsAttributeInfo();
        BeanUtils.copyProperties(eto, goodsAttributeInfo);
        repository.updateById(goodsAttributeInfo);
    }

    @Override
    public PCMerchGoodsAttributeInfoVO.DetailVO detailGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto) {
        GoodsAttributeInfo goodsAttributeInfo = repository.getById(dto.getId());
        PCMerchGoodsAttributeInfoVO.DetailVO detailVo = new PCMerchGoodsAttributeInfoVO.DetailVO();
        if(ObjectUtils.isEmpty(goodsAttributeInfo)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(goodsAttributeInfo, detailVo);
        return detailVo;
    }

    @Override
    public List<PCMerchGoodsAttributeInfoVO.ListVO> getListVO(PCMerchGoodsAttributeInfoDTO.GoodIdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsAttributeInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id",dto.getGoodId());
        List<GoodsAttributeInfo> attributeInfos = repository.list(boost);
        if (ObjectUtils.isNotEmpty(attributeInfos)){
            List<PCMerchGoodsAttributeInfoVO.ListVO> listVOS = new ArrayList<>();
            for (GoodsAttributeInfo goodsAttributeInfo : attributeInfos){
                PCMerchGoodsAttributeInfoVO.ListVO listVO = new PCMerchGoodsAttributeInfoVO.ListVO();
                BeanUtils.copyProperties(goodsAttributeInfo,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }

}
