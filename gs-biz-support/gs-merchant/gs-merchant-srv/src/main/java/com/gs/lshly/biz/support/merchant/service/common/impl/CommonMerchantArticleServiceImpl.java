package com.gs.lshly.biz.support.merchant.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticle;
import com.gs.lshly.biz.support.merchant.mapper.MerchantArticleMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantArticleView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantArticleRepository;
import com.gs.lshly.biz.support.merchant.service.common.ICommonMerchantArticleService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMerchantArticleDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantArticleQTO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantArticleVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Starry
 * @Date 17:40 2021/3/21
 */
@Component
public class CommonMerchantArticleServiceImpl  implements ICommonMerchantArticleService {

    @Autowired
    private IMerchantArticleRepository repository;
    @Autowired
    private MerchantArticleMapper merchantArticleMapper;

    @Override
    public PageData<CommonMerchantArticleVO.ListVO> pageMerchantArticleVO(CommonMerchantArticleQTO.QTO qto) {
        QueryWrapper<MerchantArticleView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("art.shop_id",qto.getShopId());
        wrapper.and(i->i.eq("art.`state`",20));
        if (ObjectUtils.isNotEmpty(qto.getPcShow())){
            wrapper.ne("art.pc_show",qto.getPcShow());
        }
        wrapper.orderByDesc("art.cdate","idx");
        IPage<MerchantArticleView> page = MybatisPlusUtil.pager(qto);
        merchantArticleMapper.mapperPageList(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, CommonMerchantArticleVO.ListVO.class, page);
    }

    @Override
    public CommonMerchantArticleVO.DetailVO detailMerchantArticle(CommonMerchantArticleDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数为空异常！");
        }
        MerchantArticle merchantArticle = repository.getById(dto.getId());
        CommonMerchantArticleVO.DetailVO detailVo = new CommonMerchantArticleVO.DetailVO();
        if(ObjectUtils.isEmpty(merchantArticle)){
            throw new BusinessException("ID不存在或者数据查询异常！");
        }
        BeanCopyUtils.copyProperties(merchantArticle, detailVo);
        return detailVo;
    }
}
