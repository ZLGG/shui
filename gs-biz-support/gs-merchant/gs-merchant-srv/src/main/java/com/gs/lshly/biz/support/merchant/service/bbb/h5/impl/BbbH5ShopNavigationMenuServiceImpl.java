package com.gs.lshly.biz.support.merchant.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopNavigationMenu;
import com.gs.lshly.biz.support.merchant.repository.IShopNavigationMenuRepository;
import com.gs.lshly.biz.support.merchant.service.bbb.h5.IBbbH5ShopNavigationMenuService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopNavigationMenuQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopNavigationMenuVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-05
*/
@Component
public class BbbH5ShopNavigationMenuServiceImpl implements IBbbH5ShopNavigationMenuService {

    @Autowired
    private IShopNavigationMenuRepository repository;

    @Override
    public PageData<BbbH5ShopNavigationMenuVO.MenuListVO> pageData(BbbH5ShopNavigationMenuQTO.QTO qto) {
        QueryWrapper<ShopNavigationMenu> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<ShopNavigationMenu> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbbH5ShopNavigationMenuVO.MenuListVO.class, page);
    }


}
