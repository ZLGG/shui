package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorGoodsRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorGoodsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 大飞船
 * @since 2020-09-29
 */
@Component
public class SiteFloorGoodsServiceImpl implements ISiteFloorGoodsService {

    @Autowired
    private ISiteFloorGoodsRepository repository;

    @Override
    public PageData<SiteFloorGoodsVO.ListVO> pageData(SiteFloorGoodsQTO.QTO qto) {
        QueryWrapper<SiteFloorGoods> wq =  MybatisPlusUtil.query();
        IPage<SiteFloorGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, SiteFloorGoodsVO.ListVO.class, page);
    }

    @Override
    public void addSiteFloorGoods(SiteFloorGoodsDTO.ADTO eto) {
        SiteFloorGoods siteFloorGoods = new SiteFloorGoods();
        BeanUtils.copyProperties(eto, siteFloorGoods);
        repository.save(siteFloorGoods);
    }

    @Override
    public void deleteSiteFloorGoods(SiteFloorGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

}
