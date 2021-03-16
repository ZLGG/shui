package com.gs.lshly.biz.support.merchant.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopFloor;
import com.gs.lshly.biz.support.merchant.repository.IShopFloorRepository;
import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopFloorService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopFloorQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopFloorVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-05
*/
@Component
public class BbcShopFloorServiceImpl implements IBbcShopFloorService {

    @Autowired
    private IShopFloorRepository repository;

    @Override
    public PageData<BbcShopFloorVO.ListVO> pageData(BbcShopFloorQTO.QTO qto) {
        QueryWrapper<ShopFloor> wrapper = new QueryWrapper<>();
        IPage<ShopFloor> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcShopFloorVO.ListVO.class, page);
    }


}
