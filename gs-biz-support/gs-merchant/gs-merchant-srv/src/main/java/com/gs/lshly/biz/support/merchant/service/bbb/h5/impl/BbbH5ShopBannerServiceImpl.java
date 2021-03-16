package com.gs.lshly.biz.support.merchant.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopBanner;
import com.gs.lshly.biz.support.merchant.repository.IShopBannerRepository;
import com.gs.lshly.biz.support.merchant.service.bbb.h5.IBbbH5ShopBannerService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopBannerQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopBannerVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-24
*/
@Component
public class BbbH5ShopBannerServiceImpl implements IBbbH5ShopBannerService {

    @Autowired
    private IShopBannerRepository repository;

    @Override
    public PageData<BbbH5ShopBannerVO.ListVO> pageData(BbbH5ShopBannerQTO.QTO qto) {
        QueryWrapper<ShopBanner> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("shop_id",qto.getShopId());
        IPage<ShopBanner> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbbH5ShopBannerVO.ListVO.class, page);
    }


}
