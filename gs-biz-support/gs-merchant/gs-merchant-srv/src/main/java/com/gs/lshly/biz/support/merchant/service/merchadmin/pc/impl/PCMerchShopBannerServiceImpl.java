package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.ShopBanner;
import com.gs.lshly.biz.support.merchant.repository.IShopBannerRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopBannerService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopBannerDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopBannerQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopBannerVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-23
*/
@Component
public class PCMerchShopBannerServiceImpl implements IPCMerchShopBannerService {

    @Autowired
    private IShopBannerRepository repository;


    @Override
    public PageData<PCMerchShopBannerVO.ListVO> pageData(PCMerchShopBannerQTO.QTO qto) {
        return null;
    }


    @Override
    public List<PCMerchShopBannerVO.H5ListVO> h5List(PCMerchShopBannerQTO.H5QTO qto) {
        QueryWrapper<ShopBanner> queryWrapper  = MybatisPlusUtil.query();
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.eq("pc_show", PcH5Enum.NO.getCode());
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        queryWrapper.orderByDesc("cdate");
        List<ShopBanner> shopBannerList = repository.list(queryWrapper);
        return ListUtil.listCover(PCMerchShopBannerVO.H5ListVO.class,shopBannerList);
    }

    @Override
    public void h5Editor(PCMerchShopBannerDTO.H5ETO eto) {

        if(ObjectUtils.isNotEmpty(eto.getItemList())){
            List<ShopBanner> batchList = new ArrayList<>();
            for(PCMerchShopBannerDTO.H5ItemETO h5ItemETO:eto.getItemList()){
                ShopBanner shopBanner = new ShopBanner();
                BeanCopyUtils.copyProperties(h5ItemETO,shopBanner);
                shopBanner.setPcShow(PcH5Enum.NO.getCode());
                shopBanner.setTerminal(eto.getTerminal());
                shopBanner.setShopId(eto.getJwtShopId());
                shopBanner.setMerchantId(eto.getJwtMerchantId());
                if(TrueFalseEnum.是.getCode().equals(h5ItemETO.getIsNew())){
                    shopBanner.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
                        if(eto.getRemoveList().contains(h5ItemETO.getId())){
                            throw new BusinessException("保存的数据与删除的数据冲突");
                        }
                    }
                }
                batchList.add(shopBanner);
            }
            repository.saveOrUpdateBatch(batchList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
            QueryWrapper<ShopBanner> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.in("id",eto.getRemoveList());
            repository.remove(queryWrapper);
        }
    }

    @Override
    public List<PCMerchShopBannerVO.PCListVO> pcList(PCMerchShopBannerQTO.PCQTO qto) {
        QueryWrapper<ShopBanner> queryWrapper  = MybatisPlusUtil.query();
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        queryWrapper.orderByAsc("idx");
        List<ShopBanner> shopBannerList = repository.list(queryWrapper);
        return ListUtil.listCover(PCMerchShopBannerVO.PCListVO.class,shopBannerList);
    }

    @Override
    public void pcEditor(PCMerchShopBannerDTO.PCETO eto) {
        if(ObjectUtils.isNotEmpty(eto.getBannerList())){
            List<ShopBanner> batchList = new ArrayList<>();
            for(PCMerchShopBannerDTO.PCBanner pcBanner:eto.getBannerList()){
                ShopBanner shopBanner = new ShopBanner();
                BeanCopyUtils.copyProperties(pcBanner,shopBanner);
                shopBanner.setPcShow(PcH5Enum.YES.getCode());
                shopBanner.setTerminal(eto.getTerminal());
                shopBanner.setShopId(eto.getJwtShopId());
                shopBanner.setMerchantId(eto.getJwtMerchantId());
                if(TrueFalseEnum.是.getCode().equals(pcBanner.getIsNew())){
                    shopBanner.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
                        if(eto.getRemoveList().contains(pcBanner.getId())){
                            throw new BusinessException("保存的数据与删除的数据冲突");
                        }
                    }
                }
                batchList.add(shopBanner);
            }
            repository.saveOrUpdateBatch(batchList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
            QueryWrapper<ShopBanner> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.in("id",eto.getRemoveList());
            repository.remove(queryWrapper);
        }
    }
}
