package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopChooseCoupon;
import com.gs.lshly.biz.support.merchant.repository.IShopChooseCouponRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopChooseCouponService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopChooseCouponDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopChooseCouponQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopChooseCouponVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-23
*/
@Component
public class PCMerchShopChooseCouponServiceImpl implements IPCMerchShopChooseCouponService {

    @Autowired
    private IShopChooseCouponRepository repository;

    @Override
    public PageData<PCMerchShopChooseCouponVO.ListVO> pageData(PCMerchShopChooseCouponQTO.QTO qto) {
        QueryWrapper<ShopChooseCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_id",qto.getJwtShopId());
        wrapper.orderByDesc("cdate");
        IPage<ShopChooseCoupon> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchShopChooseCouponVO.ListVO.class, page);
    }

    @Override
    public void addShopChooseCoupon(PCMerchShopChooseCouponDTO.ETO eto) {
        ShopChooseCoupon shopChooseCoupon = new ShopChooseCoupon();
        BeanCopyUtils.copyProperties(eto, shopChooseCoupon);
        repository.save(shopChooseCoupon);
    }


    @Override
    public void deleteShopChooseCoupon(PCMerchShopChooseCouponDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editShopChooseCoupon(PCMerchShopChooseCouponDTO.ETO eto) {
        ShopChooseCoupon shopChooseCoupon = new ShopChooseCoupon();
        BeanCopyUtils.copyProperties(eto, shopChooseCoupon);
        repository.updateById(shopChooseCoupon);
    }

    @Override
    public PCMerchShopChooseCouponVO.DetailVO detailShopChooseCoupon(PCMerchShopChooseCouponDTO.IdDTO dto) {
        ShopChooseCoupon shopChooseCoupon = repository.getById(dto.getId());
        PCMerchShopChooseCouponVO.DetailVO detailVo = new PCMerchShopChooseCouponVO.DetailVO();
        if(ObjectUtils.isEmpty(shopChooseCoupon)){
            throw new BusinessException("没有数据");
        }
        BeanCopyUtils.copyProperties(shopChooseCoupon, detailVo);
        return detailVo;
    }

}
