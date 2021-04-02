package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticle;
import com.gs.lshly.biz.support.merchant.mapper.MerchantArticleMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantArticleView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantArticleRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantArticleService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantArticleVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-09
*/
@Component
public class MerchantArticleServiceImpl implements IMerchantArticleService {

    @Autowired
    private IMerchantArticleRepository repository;

    @Autowired
    private MerchantArticleMapper merchantArticleMapper;


    @Override
    public PageData<MerchantArticleVO.ListVO> pageData(MerchantArticleQTO.QTO qto ) {
        QueryWrapper<MerchantArticleView> wrapper = MybatisPlusUtil.query();
        IPage<MerchantArticleView> page = MybatisPlusUtil.pager(qto);
        if(StringUtils.isNotBlank(qto.getTitle())){
            wrapper.like("art.title", qto.getTitle());
        }

        if(ObjectUtils.isNotEmpty(qto.getState())){
            wrapper.eq("art.state", qto.getState());
        }

        if(StringUtils.isNotBlank(qto.getMerchantId())){
            wrapper.eq("art.merchant_id",qto.getMerchantId());
        }
        wrapper.orderByDesc("art.cdate");
        merchantArticleMapper.mapperPageList(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, MerchantArticleVO.ListVO.class, page);
    }

    @Override
    public void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto) {
        repository.removeByIds(dto.getIdList());
    }

    @Override
    public void applyMerchantArticle(MerchantArticleDTO.ApplyDTO dto) {
        MerchantArticle merchantArticle = new MerchantArticle();
        BeanCopyUtils.copyProperties(dto, merchantArticle);
        repository.updateById(merchantArticle);
    }

    @Override
    public MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto) {
        if(ObjectUtils.isEmpty(dto.getId())){
            throw new BusinessException("ID不能为空");
        }
        MerchantArticleView merchantArticleView = merchantArticleMapper.detail(dto.getId());
        MerchantArticleVO.DetailVO detailVo = new MerchantArticleVO.DetailVO();
        BeanCopyUtils.copyProperties(merchantArticleView, detailVo);
        return detailVo;
    }
}
