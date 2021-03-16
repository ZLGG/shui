package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategorySpec;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategorySpecRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsCategorySpecService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategorySpecDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategorySpecQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategorySpecVO;
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
public class GoodsCategorySpecServiceImpl implements IGoodsCategorySpecService {

    @Autowired
    private IGoodsCategorySpecRepository repository;

    @Override
    public PageData<GoodsCategorySpecVO.ListVO> pageData(GoodsCategorySpecQTO.QTO qto) {
        QueryWrapper<GoodsCategorySpec> wrapper = new QueryWrapper<>();
        IPage<GoodsCategorySpec> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, GoodsCategorySpecVO.ListVO.class, page);
    }

    @Override
    public void addGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto) {
        GoodsCategorySpec goodsCategorySpec = new GoodsCategorySpec();
        BeanUtils.copyProperties(eto, goodsCategorySpec);
        repository.save(goodsCategorySpec);
    }


    @Override
    public void deleteGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto) {
        GoodsCategorySpec goodsCategorySpec = new GoodsCategorySpec();
        BeanUtils.copyProperties(eto, goodsCategorySpec);
        repository.updateById(goodsCategorySpec);
    }

    @Override
    public GoodsCategorySpecVO.DetailVO detailGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto) {
        GoodsCategorySpec goodsCategorySpec = repository.getById(dto.getId());
        GoodsCategorySpecVO.DetailVO detailVo = new GoodsCategorySpecVO.DetailVO();
        if(ObjectUtils.isEmpty(goodsCategorySpec)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(goodsCategorySpec, detailVo);
        return detailVo;
    }

}
