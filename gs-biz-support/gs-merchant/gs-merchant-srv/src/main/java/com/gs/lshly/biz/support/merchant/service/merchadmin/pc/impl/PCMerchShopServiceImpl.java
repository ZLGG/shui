package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.Merchant;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.entity.ShopDeliveryStyle;
import com.gs.lshly.biz.support.merchant.entity.ShopVisits;
import com.gs.lshly.biz.support.merchant.repository.*;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopService;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchSettingsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchSettingsVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-13
*/
@Component
public class PCMerchShopServiceImpl implements IPCMerchShopService {

    @Autowired
    private IShopRepository repository;
    @Autowired
    private IMerchantAccountRepository merchantAccountRepository;
    @Autowired
    private IMerchantRepository iMerchantRepository;
    @Autowired
    private IShopVisitsRepository iShopVisitsRepository;

    @Override
    public List<PCMerchShopVO.ListVO> list(PCMerchShopQTO.QTO qto) {
        if(UserTypeEnum._2C商家主账号.getCode().equals(qto.getJwtUserType()) || UserTypeEnum._2B商家主账号.getCode().equals(qto.getJwtUserType())){
            QueryWrapper<Shop> wq = new QueryWrapper<>();
            wq.eq("merchant_id",qto.getJwtMerchantId());
            wq.orderByDesc("cdate");
            List<Shop> shopList = repository.list(wq);
            return ListUtil.listCover(PCMerchShopVO.ListVO.class,shopList);
        }
        return new ArrayList<>();
    }

    @Override
    public void editShop(PCMerchShopDTO.ETO eto) {
        Shop shop = new Shop();
        BeanCopyUtils.copyProperties(eto, shop);
        //TODO 临时设置商户号
        if (ObjectUtils.isNotEmpty(eto.getLakalaNo())) {
            Merchant merchant = iMerchantRepository.getById(eto.getJwtMerchantId());
            if (ObjectUtils.isNotEmpty(merchant)) {
                merchant.setLakalaNo(eto.getLakalaNo());
                iMerchantRepository.updateById(merchant);
            }
        }
        shop.setId(eto.getJwtShopId());
        shop.setShopDesc(StringUtils.isBlank(eto.getShopDescribe()) ? "" : eto.getShopDescribe());
        repository.updateById(shop);
    }

    @Override
    public PCMerchShopVO.DetailVO detailShop(BaseDTO dto) {
        Shop shop = repository.getById(dto.getJwtShopId());
        PCMerchShopVO.DetailVO detailVo = new PCMerchShopVO.DetailVO();
        if(ObjectUtils.isEmpty(shop)){
            throw new BusinessException("没有数据");
        }
        BeanCopyUtils.copyProperties(shop, detailVo);
        Merchant me = iMerchantRepository.getById(dto.getJwtMerchantId());
        if (ObjectUtils.isNotEmpty(me)){
            detailVo.setLakalaNo(me.getLakalaNo());
        }
        detailVo.setShopDescribe(ObjectUtils.isEmpty(shop.getShopDesc())?"":shop.getShopDesc());
        return detailVo;
    }

    @Autowired
    private IShopDeliveryStyleRepository shopDeliveryStyleRepository;

    @Override
    public PCMerchSettingsVO.DeliveryStyleVO fetch(BaseDTO dto) {
        ShopDeliveryStyle deliveryStyle = shopDeliveryStyleRepository.getOne(new QueryWrapper<ShopDeliveryStyle>().eq("shop_id", dto.getJwtShopId()));
        PCMerchSettingsVO.DeliveryStyleVO vo = new PCMerchSettingsVO.DeliveryStyleVO();
        if (deliveryStyle != null && deliveryStyle.getDeliveryTypes() != null) {
            BeanCopyUtils.copyProperties(deliveryStyle, vo);
            List<Integer> types = Arrays.stream(deliveryStyle.getDeliveryTypes().split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
            vo.setDeliveryTypes(types);
        }
        return vo;
    }

    @Override
    public void set(PCMerchSettingsDTO.DeliveryStyleDTO dto) {
        ShopDeliveryStyle deliveryStyle = shopDeliveryStyleRepository.getOne(new QueryWrapper<ShopDeliveryStyle>().eq("shop_id", dto.getJwtShopId()));
        if (deliveryStyle == null) {
            deliveryStyle = new ShopDeliveryStyle();
        }
        BeanCopyUtils.copyProperties(dto, deliveryStyle);
        deliveryStyle.setDeliveryTypes(CollUtil.join(dto.getDeliveryTypes(), ","));
        shopDeliveryStyleRepository.saveOrUpdate(deliveryStyle);
    }

    @Override
    public Integer innerShopState(String shopId) {
        if(StringUtils.isBlank(shopId)){
            throw new BusinessException("店铺ID不能为空");
        }
        Shop shop =  repository.getById(shopId);
        if(null == shop){
            throw new BusinessException("店铺不存在");
        }
        return shop.getShopState();
    }

    @Override
    public PCMerchShopVO.ShopSimpleVO innerShopSimple(String shopId) {
        if(StringUtils.isBlank(shopId)){
            return null;
        }
        Shop shop =  repository.getById(shopId);
        if(null == shop){
            return null;
        }
        PCMerchShopVO.ShopSimpleVO shopSimpleVO = new PCMerchShopVO.ShopSimpleVO();
        BeanUtils.copyProperties(shop,shopSimpleVO);
        return shopSimpleVO;
    }

    @Override
    public Integer innPV(String shopId) {
        if (ObjectUtils.isNotEmpty(shopId)) {
            QueryWrapper<ShopVisits> query = MybatisPlusUtil.query();
            query.and(i -> i.eq("shop_id", shopId));
            return iShopVisitsRepository.getYesterDayPV(query);
        }
        return 0;
    }

    @Override
    public Integer innUV(String shopId) {
        if (ObjectUtils.isNotEmpty(shopId)){
            QueryWrapper<ShopVisits> query = MybatisPlusUtil.query();
            query.and(i->i.eq("shop_id",shopId));
            List<PCMerchShopVO.ListUV> uv = iShopVisitsRepository.getUV(query);
            if (ObjectUtils.isNotEmpty(uv)){
                return uv.size();
            }else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public PCMerchShopVO.ShopIdVO innerLikeSimple(String shopId) {
        if(StringUtils.isBlank(shopId)){
            return null;
        }
        QueryWrapper<Shop> query = MybatisPlusUtil.query();
        query.like("shopName",shopId);
        List<Shop> shop =  repository.list(query);
        if(null == shop){
            return null;
        }
        List<String> id = ListUtil.getIdList(Shop.class, shop, "id");
        PCMerchShopVO.ShopIdVO shopIdVO = new PCMerchShopVO.ShopIdVO();
        shopIdVO.setId(id);
        return shopIdVO;
    }

}
