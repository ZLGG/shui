package com.gs.lshly.biz.support.merchant.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopFloor;
import com.gs.lshly.biz.support.merchant.repository.IShopFloorRepository;
import com.gs.lshly.biz.support.merchant.service.bbb.h5.IBbbH5ShopFloorService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopFloorVO;
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
public class BbbH5ShopFloorServiceImpl implements IBbbH5ShopFloorService {

    @Autowired
    private IShopFloorRepository repository;

    @Override
    public PageData<BbbH5ShopFloorVO.ListVO> pageData(BbbH5ShopFloorQTO.QTO qto) {
        QueryWrapper<ShopFloor> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<ShopFloor> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbbH5ShopFloorVO.ListVO.class, page);
    }


}
