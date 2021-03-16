package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.DataArticleRelationCategory;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRelationCategoryRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataArticleRelationCategoryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleRelationCategoryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-13
*/
@Component
public class BbcDataArticleRelationCategoryServiceImpl implements IBbcDataArticleRelationCategoryService {

    @Autowired
    private IDataArticleRelationCategoryRepository repository;

    @Override
    public PageData<BbcDataArticleRelationCategoryVO.ListVO> pageData(BbcDataArticleRelationCategoryQTO.QTO qto) {
        QueryWrapper<DataArticleRelationCategory> wrapper =  MybatisPlusUtil.query();
        IPage<DataArticleRelationCategory> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcDataArticleRelationCategoryVO.ListVO.class, page);
    }

    @Override
    public void addDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto) {
        DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
        BeanUtils.copyProperties(eto, dataArticleRelationCategory);
        repository.save(dataArticleRelationCategory);
    }


    @Override
    public void deleteDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto) {
        DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
        BeanUtils.copyProperties(eto, dataArticleRelationCategory);
        repository.updateById(dataArticleRelationCategory);
    }

    @Override
    public BbcDataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto) {
        DataArticleRelationCategory dataArticleRelationCategory = repository.getById(dto.getId());
        BbcDataArticleRelationCategoryVO.DetailVO detailVo = new BbcDataArticleRelationCategoryVO.DetailVO();
        if(ObjectUtils.isEmpty(dataArticleRelationCategory)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(dataArticleRelationCategory, detailVo);
        return detailVo;
    }

}
