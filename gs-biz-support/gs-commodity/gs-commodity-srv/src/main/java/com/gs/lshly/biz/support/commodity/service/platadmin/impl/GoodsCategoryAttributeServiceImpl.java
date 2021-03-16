package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategoryAttribute;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryAttributeRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsCategoryAttributeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryAttributeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryAttributeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryAttributeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-27
*/
@Component
public class GoodsCategoryAttributeServiceImpl implements IGoodsCategoryAttributeService {

    @Autowired
    private IGoodsCategoryAttributeRepository repository;

    @Override
    public PageData<GoodsCategoryAttributeVO.ListVO> pageData(GoodsCategoryAttributeQTO.QTO qto) {
        QueryWrapper<GoodsCategoryAttribute> wrapper = new QueryWrapper<>();
        IPage<GoodsCategoryAttribute> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, GoodsCategoryAttributeVO.ListVO.class, page);
    }

    @Override
    public void addGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto) {
        GoodsCategoryAttribute goodsCategoryAttribute = new GoodsCategoryAttribute();
        BeanUtils.copyProperties(eto, goodsCategoryAttribute);
        repository.save(goodsCategoryAttribute);
    }


    @Override
    public void deleteGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto) {
        GoodsCategoryAttribute goodsCategoryAttribute = new GoodsCategoryAttribute();
        BeanUtils.copyProperties(eto, goodsCategoryAttribute);
        repository.updateById(goodsCategoryAttribute);
    }

    @Override
    public GoodsCategoryAttributeVO.DetailVO detailGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto) {
        GoodsCategoryAttribute goodsCategoryAttribute = repository.getById(dto.getId());
        GoodsCategoryAttributeVO.DetailVO detailVo = new GoodsCategoryAttributeVO.DetailVO();
        if(ObjectUtils.isEmpty(goodsCategoryAttribute)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(goodsCategoryAttribute, detailVo);
        return detailVo;
    }

}
