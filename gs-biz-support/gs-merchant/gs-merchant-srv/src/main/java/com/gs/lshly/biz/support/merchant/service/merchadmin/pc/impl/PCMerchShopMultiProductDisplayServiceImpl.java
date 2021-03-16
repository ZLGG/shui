package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopMultiProductDisplay;

import com.gs.lshly.biz.support.merchant.repository.IShopMultiProductDisplayRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopMultiProductDisplayService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopMultiProductDisplayDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopMultiProductDisplayQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopMultiProductDisplayVO;
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
public class PCMerchShopMultiProductDisplayServiceImpl implements IPCMerchShopMultiProductDisplayService {

    @Autowired
    private IShopMultiProductDisplayRepository repository;

    @Override
    public PageData<PCMerchShopMultiProductDisplayVO.ListVO> pageData(PCMerchShopMultiProductDisplayQTO.QTO qto) {
        QueryWrapper<ShopMultiProductDisplay> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",qto.getShopId());
        wrapper.eq("merchant_id",qto.getMerchantId());

        IPage<ShopMultiProductDisplay> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchShopMultiProductDisplayVO.ListVO.class, page);
    }

    @Override
    public void addShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.ADTO adto) {
        ShopMultiProductDisplay shopMultiProductDisplay = new ShopMultiProductDisplay();
        BeanCopyUtils.copyProperties(adto, shopMultiProductDisplay);
        repository.save(shopMultiProductDisplay);
    }


    @Override
    public void deleteShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editShopMultiProductDisplay( PCMerchShopMultiProductDisplayDTO.UDTO udto) {
        ShopMultiProductDisplay shopMultiProductDisplay = new ShopMultiProductDisplay();
        BeanCopyUtils.copyProperties(udto, shopMultiProductDisplay);
        repository.updateById(shopMultiProductDisplay);
    }

    @Override
    public PCMerchShopMultiProductDisplayVO.DetailVO detailShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.IdDTO dto) {
        ShopMultiProductDisplay shopMultiProductDisplay = repository.getById(dto.getId());
        PCMerchShopMultiProductDisplayVO.DetailVO detailVo = new PCMerchShopMultiProductDisplayVO.DetailVO();
        if(ObjectUtils.isEmpty(shopMultiProductDisplay)){
            throw new BusinessException("没有数据");
        }
        BeanCopyUtils.copyProperties(shopMultiProductDisplay, detailVo);
        return detailVo;
    }

    @Override
    public void changeShopMultiProductDisplay(PCMerchShopMultiProductDisplayDTO.CDTO cdto) {
        ShopMultiProductDisplay byId = repository.getById(cdto.getId());
        byId.setIdx(cdto.getIdx());
        repository.updateById(byId);
    }

}
