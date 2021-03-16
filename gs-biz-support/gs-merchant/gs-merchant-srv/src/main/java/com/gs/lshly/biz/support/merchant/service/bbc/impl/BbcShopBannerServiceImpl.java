package com.gs.lshly.biz.support.merchant.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopBanner;
import com.gs.lshly.biz.support.merchant.repository.IShopBannerRepository;
import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopBannerService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopBannerQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopBannerVO;
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
public class BbcShopBannerServiceImpl implements IBbcShopBannerService {

    @Autowired
    private IShopBannerRepository repository;

    @Override
    public PageData<BbcShopBannerVO.ListVO> pageData(BbcShopBannerQTO.QTO qto) {
        QueryWrapper<ShopBanner> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("shop_id",qto.getShopId());
        IPage<ShopBanner> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcShopBannerVO.ListVO.class, page);
    }


}
