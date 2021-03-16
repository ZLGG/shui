package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticle;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticleCategory;
import com.gs.lshly.biz.support.merchant.mapper.MerchantArticleMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantArticleView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantArticleCategoryRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantArticleRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantArticleService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleCategoryVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.foundation.IMerchantArticleCategoryRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.ibatis.annotations.Param;
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
* @author hyy
* @since 2020-11-07
*/
@Component
public class PCMerchMerchantArticleServiceImpl implements IPCMerchMerchantArticleService {

    @Autowired
    private IMerchantArticleRepository repository;

    @Autowired
    private MerchantArticleMapper merchantArticleMapper;

    @Autowired
    private IMerchantArticleCategoryRepository merchantArticleCategoryRepository;

    @DubboReference
    private IMerchantArticleCategoryRpc merchantArticleCategory;

    @Override
    public PageData<PCMerchMerchantArticleVO.ListVO> pageData(PCMerchMerchantArticleQTO.QTO qto) {
        QueryWrapper<MerchantArticleView> wrapper = MybatisPlusUtil.query();
        /*wrapper.eq("art.shop_id",qto.getJwtShopId());*/
        IPage<MerchantArticleView> page = MybatisPlusUtil.pager(qto);
        merchantArticleMapper.mapperPageList(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMerchantArticleVO.ListVO.class, page);
    }

    @Override
    public void addMerchantArticle(PCMerchMerchantArticleDTO.ETO eto) {
        MerchantArticle merchantArticle = new MerchantArticle();
        BeanCopyUtils.copyProperties(eto, merchantArticle);
        merchantArticle.setShopId(eto.getJwtShopId());
        merchantArticle.setMerchantId(eto.getJwtMerchantId());
        repository.save(merchantArticle);
    }


    @Override
    public void deleteMerchantArticle(PCMerchMerchantArticleDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("文章ID不能为空");
        }
        QueryWrapper<MerchantArticle> removeQueryWrapper = MybatisPlusUtil.query();
        removeQueryWrapper.eq("shop_id",dto.getJwtShopId());
        removeQueryWrapper.eq("id",dto.getId());
        repository.remove(removeQueryWrapper);
    }


    @Override
    public void editMerchantArticle(PCMerchMerchantArticleDTO.ETO eto) {
        MerchantArticle merchantArticle =repository.getById(eto.getId());
        if(null == merchantArticle){
            throw new BusinessException("商家文章不存在");
        }
        BeanCopyUtils.copyProperties(eto, merchantArticle);
        repository.updateById(merchantArticle);
    }

    @Override
    public PCMerchMerchantArticleVO.DetailVO detailMerchantArticle(PCMerchMerchantArticleDTO.IdDTO dto) {
        MerchantArticle merchantArticle = repository.getById(dto.getId());
        PCMerchMerchantArticleVO.DetailVO detailVo = new PCMerchMerchantArticleVO.DetailVO();
        if(ObjectUtils.isEmpty(merchantArticle)){
            throw new BusinessException("没有数据");
        }
        BeanCopyUtils.copyProperties(merchantArticle, detailVo);
        return detailVo;
    }

    @Override
    public PCMerchMerchantArticleVO.LinkListVO listLinkUrl(PCMerchMerchantArticleDTO.IdDTO dto) {
        PCMerchMerchantArticleVO.LinkListVO linkListVO = new PCMerchMerchantArticleVO.LinkListVO();
        return linkListVO;
    }

}
