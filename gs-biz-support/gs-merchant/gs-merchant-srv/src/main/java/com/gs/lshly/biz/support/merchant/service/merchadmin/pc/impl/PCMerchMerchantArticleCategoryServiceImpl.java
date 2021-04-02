package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticle;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticleCategory;
import com.gs.lshly.biz.support.merchant.repository.IMerchantArticleCategoryRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantArticleRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantArticleCategoryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleCategoryVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-07
*/
@Component
public class PCMerchMerchantArticleCategoryServiceImpl implements IPCMerchMerchantArticleCategoryService {

    @Autowired
    private IMerchantArticleCategoryRepository repository;
    @Autowired
    private IMerchantArticleRepository merchantArticleRepository;

    @Override
    public PageData<PCMerchMerchantArticleCategoryVO.ListVO> pageData(PCMerchMerchantArticleCategoryQTO.QTO qto) {
        QueryWrapper<MerchantArticleCategory> wrapper = MybatisPlusUtil.queryContainShopId(qto);
        wrapper.orderByDesc("cdate");
        IPage<MerchantArticleCategory> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMerchantArticleCategoryVO.ListVO.class, page);
    }

    @Override
    public void addMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto) {
        MerchantArticleCategory merchantArticleCategory = new MerchantArticleCategory();
        BeanCopyUtils.copyProperties(eto, merchantArticleCategory);
        repository.save(merchantArticleCategory);
    }

    @Override
    public void deleteMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.IdDTO dto) {
        QueryWrapper<MerchantArticle> countQueryWrapper = MybatisPlusUtil.query();
        countQueryWrapper.eq("category_id",dto.getId());
        int count = merchantArticleRepository.count(countQueryWrapper);
        if(count > 0){
            throw new BusinessException("文章分类下有文章,不能删除");
        }
        QueryWrapper<MerchantArticleCategory> queryWrapper = MybatisPlusUtil.queryContainShopId(dto);
        queryWrapper.eq("id",dto.getId());
        repository.remove(queryWrapper);
}

    @Override
    public void editMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto) {
        QueryWrapper<MerchantArticleCategory> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",eto.getJwtShopId());
        queryWrapper.eq("id",eto.getId());
        MerchantArticleCategory merchantArticleCategory = repository.getOne(queryWrapper);
        if(null == merchantArticleCategory){
            throw new BusinessException("文章栏目不存在");
        }
        BeanCopyUtils.copyProperties(eto, merchantArticleCategory);
        repository.updateById(merchantArticleCategory);
    }

}
