package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.DataArticleRelationCategory;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRelationCategoryRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataArticleRelationCategoryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleRelationCategoryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-19
*/
@Component
public class DataArticleRelationCategoryServiceImpl implements IDataArticleRelationCategoryService {

    @Autowired
    private IDataArticleRelationCategoryRepository repository;

    @Override
    public PageData<DataArticleRelationCategoryVO.ListVO> pageData(DataArticleRelationCategoryQTO.QTO qto) {
        QueryWrapper<DataArticleRelationCategory> wrapper =  MybatisPlusUtil.query();
        IPage<DataArticleRelationCategory> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, DataArticleRelationCategoryVO.ListVO.class, page);
    }

    @Override
    public void addDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto) {
        DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
        BeanUtils.copyProperties(eto, dataArticleRelationCategory);
        repository.save(dataArticleRelationCategory);
    }

    @Override
    public void deleteDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto) {
        DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
        BeanUtils.copyProperties(eto, dataArticleRelationCategory);
        repository.updateById(dataArticleRelationCategory);
    }

    @Override
    public DataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto) {
        DataArticleRelationCategory dataArticleRelationCategory = repository.getById(dto.getId());
        DataArticleRelationCategoryVO.DetailVO detailVo = new DataArticleRelationCategoryVO.DetailVO();
        if(ObjectUtils.isEmpty(dataArticleRelationCategory)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(dataArticleRelationCategory, detailVo);
        return detailVo;
    }

}
