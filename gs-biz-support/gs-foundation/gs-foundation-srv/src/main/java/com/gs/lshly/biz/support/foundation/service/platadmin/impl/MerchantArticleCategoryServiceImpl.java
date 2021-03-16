package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.MerchantArticleCategory;
import com.gs.lshly.biz.support.foundation.repository.IMerchantArticleCategoryRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IMerchantArticleCategoryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.MerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.MerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleCategoryVO;
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
* @since 2020-10-29
*/
@Component
public class MerchantArticleCategoryServiceImpl implements IMerchantArticleCategoryService {

    @Autowired
    private IMerchantArticleCategoryRepository repository;

    @Override
    public PageData<MerchantArticleCategoryVO.ListVO> pageData(MerchantArticleCategoryQTO.QTO qto) {
        QueryWrapper<MerchantArticleCategory> wrapper =  MybatisPlusUtil.query();
        IPage<MerchantArticleCategory> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, MerchantArticleCategoryVO.ListVO.class, page);
    }

    @Override
    public void addMerchantArticleCategory(MerchantArticleCategoryDTO.ETO eto) {
        MerchantArticleCategory merchantArticleCategory = new MerchantArticleCategory();
        BeanUtils.copyProperties(eto, merchantArticleCategory);
        repository.save(merchantArticleCategory);
    }


    @Override
    public void deleteMerchantArticleCategory(MerchantArticleCategoryDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMerchantArticleCategory(MerchantArticleCategoryDTO.ETO eto) {
        MerchantArticleCategory merchantArticleCategory = new MerchantArticleCategory();
        BeanUtils.copyProperties(eto, merchantArticleCategory);
        repository.updateById(merchantArticleCategory);
    }

    @Override
    public MerchantArticleCategoryVO.DetailVO detailMerchantArticleCategory(MerchantArticleCategoryDTO.IdDTO dto) {
        MerchantArticleCategory merchantArticleCategory = repository.getById(dto.getId());
        MerchantArticleCategoryVO.DetailVO detailVo = new MerchantArticleCategoryVO.DetailVO();
        if(ObjectUtils.isEmpty(merchantArticleCategory)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(merchantArticleCategory, detailVo);
        return detailVo;
    }

}
