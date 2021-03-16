package com.gs.lshly.biz.support.merchant.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.h5.IH5MerchShopService;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-20
*/
@Component
public class H5MerchShopServiceImpl implements IH5MerchShopService {

    @Autowired
    private IShopRepository repository;

    @Override
    public List<H5MerchShopVO.ListVO> list(H5MerchShopQTO.QTO qto) {
        if(UserTypeEnum._2C商家主账号.getCode().equals(qto.getJwtUserType()) || UserTypeEnum._2B商家主账号.getCode().equals(qto.getJwtUserType())){
            QueryWrapper<Shop> wq = new QueryWrapper<>();
            wq.eq("merchant_id",qto.getJwtMerchantId());
            List<Shop> shopList = repository.list(wq);
            return ListUtil.listCover(H5MerchShopVO.ListVO.class,shopList);
        }
        return new ArrayList<>();
    }

    @Override
    public void editShop(H5MerchShopDTO.ETO eto) {
        Shop shop = new Shop();
        BeanCopyUtils.copyProperties(eto, shop);
        shop.setShopDesc(StringUtils.isBlank(eto.getShopDescribe()) ? "" : eto.getShopDescribe());
        repository.updateById(shop);
    }


    @Override
    public H5MerchShopVO.DetailVO detailShop(BaseDTO dto) {
        Shop shop = repository.getById(dto.getJwtShopId());
        H5MerchShopVO.DetailVO detailVo = new H5MerchShopVO.DetailVO();
        if(ObjectUtils.isEmpty(shop)){
            throw new BusinessException("没有数据");
        }
        BeanCopyUtils.copyProperties(shop, detailVo);
        detailVo.setShopDescribe(ObjectUtils.isEmpty(shop.getShopDesc())?"":shop.getShopDesc());
        return detailVo;
    }

    @Override
    public H5MerchShopVO.ShopSimpleVO innerShopSimple(String shopId) {
        if(StringUtils.isBlank(shopId)){
            return null;
        }
        Shop shop =  repository.getById(shopId);
        if(null == shop){
            return null;
        }
        H5MerchShopVO.ShopSimpleVO shopSimpleVO = new H5MerchShopVO.ShopSimpleVO();
        BeanUtils.copyProperties(shop,shopSimpleVO);
        return shopSimpleVO;
    }

}
