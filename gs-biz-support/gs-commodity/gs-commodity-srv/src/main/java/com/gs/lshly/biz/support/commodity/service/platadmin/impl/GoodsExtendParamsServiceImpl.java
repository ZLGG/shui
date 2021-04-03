package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsExtendParams;
import com.gs.lshly.biz.support.commodity.repository.IGoodsExtendParamsRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsExtendParamsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsExtendParamsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-09
*/
@Component
public class GoodsExtendParamsServiceImpl implements IGoodsExtendParamsService {

    @Autowired
    private IGoodsExtendParamsRepository repository;

    @Override
    public PageData<PCMerchGoodsExtendParamsVO.ListVO> pageData(PCMerchGoodsExtendParamsQTO.QTO qto) {
        QueryWrapper<GoodsExtendParams> wq = new QueryWrapper<>();
        IPage<GoodsExtendParams> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, PCMerchGoodsExtendParamsVO.ListVO.class, page);
    }

    @Override
    public String addGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto) {
        GoodsExtendParams goodsExtendParams = new GoodsExtendParams();
        BeanUtils.copyProperties(eto, goodsExtendParams);
        repository.save(goodsExtendParams);
        return goodsExtendParams.getId();
    }


    @Override
    public void deleteGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto) {
        GoodsExtendParams goodsExtendParams = new GoodsExtendParams();
        BeanUtils.copyProperties(eto, goodsExtendParams);
        repository.updateById(goodsExtendParams);
    }

    @Override
    public PCMerchGoodsExtendParamsVO.DetailVO detailGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto) {
        GoodsExtendParams goodsExtendParams = repository.getById(dto.getId());
        PCMerchGoodsExtendParamsVO.DetailVO detailVo = new PCMerchGoodsExtendParamsVO.DetailVO();
        if(ObjectUtils.isEmpty(goodsExtendParams)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(goodsExtendParams, detailVo);
        return detailVo;
    }

    @Override
    public List<PCMerchGoodsExtendParamsVO.ListVO> extendListVO(PCMerchGoodsExtendParamsDTO.GoodIdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsExtendParams> boost = MybatisPlusUtil.query();
        boost.eq("good_id",dto.getGoodId());
        List<GoodsExtendParams> goodsExtendParams = repository.list(boost);
        if (ObjectUtils.isNotEmpty(goodsExtendParams)){
            List<PCMerchGoodsExtendParamsVO.ListVO> listVOS = new ArrayList<>();
            for (GoodsExtendParams extendParams : goodsExtendParams){
                PCMerchGoodsExtendParamsVO.ListVO listVO = new PCMerchGoodsExtendParamsVO.ListVO();
                BeanUtils.copyProperties(extendParams,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }

}
