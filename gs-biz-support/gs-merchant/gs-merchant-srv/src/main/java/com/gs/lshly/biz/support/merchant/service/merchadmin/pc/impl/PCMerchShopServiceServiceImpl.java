package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.entity.ShopService;
import com.gs.lshly.biz.support.merchant.repository.IShopServiceRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopServiceService;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopServiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopServiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopServiceVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-09
*/
@Component
public class PCMerchShopServiceServiceImpl implements IPCMerchShopServiceService {

    @Autowired
    private IShopServiceRepository repository;

    @Override
    public List<PCMerchShopServiceVO.ListVO> list(PCMerchShopServiceQTO.QTO qto) {
        QueryWrapper<ShopService> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",qto.getJwtShopId());
        wrapper.orderByDesc("cdate");
        List<ShopService> shopServiceList=repository.list(wrapper);
        return ListUtil.listCover(PCMerchShopServiceVO.ListVO.class,shopServiceList);
    }

    @Override
    public void addSiteCustomerService(PCMerchShopServiceDTO.ETO eto) {
        QueryWrapper queryWrapper = MybatisPlusUtil.queryContainShopId(eto);
        ShopService shopService = repository.getOne(queryWrapper);
        if(null == shopService){
            shopService = new ShopService();
            BeanCopyUtils.copyProperties(eto, shopService);
            repository.save(shopService);
        }else{
            ShopService service = new ShopService();
            service.setState(eto.getState());
            service.setAccount(eto.getAccount());
            service.setId(shopService.getId());
            repository.updateById(service);
        }

    }

    @Override
    public List<PCMerchShopServiceVO.PhoneVO> listPhone(PCMerchShopServiceQTO.QTO qto) {
        QueryWrapper<ShopService> wrapper = MybatisPlusUtil.queryContainShopId(qto);
        List<ShopService> shopServiceList=repository.list( wrapper);
        return ListUtil.listCover(PCMerchShopServiceVO.PhoneVO.class,shopServiceList);
    }

    @Override
    public void addSiteCustomerServicePhone(PCMerchShopServiceDTO.ETOPhone eto) {
        QueryWrapper queryWrapper = MybatisPlusUtil.queryContainShopId(eto);
        ShopService shopService = repository.getOne(queryWrapper);
        if(null == shopService){
            shopService = new ShopService();
            BeanCopyUtils.copyProperties(eto, shopService);
            repository.save(shopService);
        }else{
            ShopService service = new ShopService();
            service.setPhone(eto.getPhone());
            service.setPhoneState(eto.getPhoneState());
            service.setId(shopService.getId());
            repository.updateById(service);
        }
    }
}
