package com.gs.lshly.biz.support.merchant.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopNavigationMenu;
import com.gs.lshly.biz.support.merchant.repository.IShopNavigationMenuRepository;
import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopNavigationMenuService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopNavigationMenuQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopNavigationMenuVO;
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
public class BbcShopNavigationMenuServiceImpl implements IBbcShopNavigationMenuService {

    @Autowired
    private IShopNavigationMenuRepository repository;

    @Override
    public PageData<BbcShopNavigationMenuVO.MenuListVO> pageData(BbcShopNavigationMenuQTO.QTO qto) {
        QueryWrapper<ShopNavigationMenu> wrapper = new QueryWrapper<>();
        IPage<ShopNavigationMenu> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcShopNavigationMenuVO.MenuListVO.class, page);
    }


}
