package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.DataArticleRelationCategory;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRelationCategoryRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataArticleRelationCategoryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleRelationCategoryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-13
*/
@Component
public class BbbH5DataArticleRelationCategoryServiceImpl implements IBbbH5DataArticleRelationCategoryService {

    @Autowired
    private IDataArticleRelationCategoryRepository repository;

    @Override
    public PageData<BbbH5DataArticleRelationCategoryVO.ListVO> pageData(BbbH5DataArticleRelationCategoryQTO.QTO qto) {
        QueryWrapper<DataArticleRelationCategory> wrapper =  MybatisPlusUtil.query();
        IPage<DataArticleRelationCategory> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbbH5DataArticleRelationCategoryVO.ListVO.class, page);
    }

    @Override
    public void addDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.ETO eto) {
        DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
        BeanUtils.copyProperties(eto, dataArticleRelationCategory);
        repository.save(dataArticleRelationCategory);
    }


    @Override
    public void deleteDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.ETO eto) {
        DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
        BeanUtils.copyProperties(eto, dataArticleRelationCategory);
        repository.updateById(dataArticleRelationCategory);
    }

    @Override
    public BbbH5DataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.IdDTO dto) {
        DataArticleRelationCategory dataArticleRelationCategory = repository.getById(dto.getId());
        BbbH5DataArticleRelationCategoryVO.DetailVO detailVo = new BbbH5DataArticleRelationCategoryVO.DetailVO();
        if(ObjectUtils.isEmpty(dataArticleRelationCategory)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(dataArticleRelationCategory, detailVo);
        return detailVo;
    }

}
