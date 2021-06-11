package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchSkuGoodInfoService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchSkuGoodInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Starry
 * @since 2020-10-08
 */
@Component
public class PCMerchSkuGoodInfoServiceImpl implements IPCMerchSkuGoodInfoService {

    @Autowired
    private ISkuGoodInfoRepository repository;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @Override
    public PageData<PCMerchSkuGoodInfoVO.ListVO> pageData(PCMerchSkuGoodInfoQTO.QTO qto) {
        QueryWrapper<SkuGoodInfo> wq = new QueryWrapper<>();
        IPage<SkuGoodInfo> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, PCMerchSkuGoodInfoVO.ListVO.class, page);
    }

    @Override
    public void addSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto) {
        SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
        BeanUtils.copyProperties(eto, skuGoodInfo);
        eto.setShopId(eto.getJwtShopId());
        eto.setMerchantId(eto.getJwtMerchantId());
        repository.save(skuGoodInfo);
    }


    @Override
    public void deleteSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto) {
        SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
        BeanUtils.copyProperties(eto, skuGoodInfo);
        repository.updateById(skuGoodInfo);
    }

    @Override
    public PCMerchSkuGoodInfoVO.DetailVO detailSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto) {
        SkuGoodInfo skuGoodInfo = repository.getById(dto.getId());
        PCMerchSkuGoodInfoVO.DetailVO detailVo = new PCMerchSkuGoodInfoVO.DetailVO();
        if (ObjectUtils.isEmpty(skuGoodInfo)) {
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(skuGoodInfo, detailVo);
        return detailVo;
    }

    @Override
    public List<PCMerchSkuGoodInfoVO.DetailVO> getByGoodsId(PCMerchSkuGoodInfoDTO.GoodIdDTO goodId) {
        QueryWrapper<SkuGoodInfo> skuBoost = MybatisPlusUtil.query();
        skuBoost.eq("good_id", goodId.getGoodId());
        List<SkuGoodInfo> skuGoodInfos = repository.list(skuBoost);
        if (ObjectUtils.isNotEmpty(skuGoodInfos)) {
            List<PCMerchSkuGoodInfoVO.DetailVO> detailVOS = new ArrayList<>();
            for (SkuGoodInfo skuGoodInfo : skuGoodInfos) {
                PCMerchSkuGoodInfoVO.DetailVO detailVO = new PCMerchSkuGoodInfoVO.DetailVO();
                BeanUtils.copyProperties(skuGoodInfo, detailVO);
                //获取sku存数量
                detailVO.setSkuStock(getSkuStockNum(goodId.getJwtShopId(), skuGoodInfo.getId()));
                detailVOS.add(detailVO);
            }
            return detailVOS;
        }
        return null;
    }

    @Override
    public void updateSkuInfo(SkuGoodInfo skuGoodInfo) {
        QueryWrapper<SkuGoodInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id", skuGoodInfo.getGoodId());
        repository.update(skuGoodInfo, wrapper);
    }

    @Override
    public void addSkuInfo(SkuGoodInfo skuGoodInfo) {
        repository.save(skuGoodInfo);
    }

    @Override
    public String selectSkuImg(String skuId) {
        if (StrUtil.isNotEmpty(skuId)) {
            SkuGoodInfo byId = repository.getById(skuId);
            if (ObjectUtil.isNotEmpty(byId)) {
                if (StrUtil.isNotEmpty(byId.getImage())) {
                    return byId.getImage();
                }
            }
        }
        return null;
    }

    @Override
    public List<PCMerchMarketPtSeckillVO.AllSkuVO> selectByGoodsId(String goodsId) {
        if (StrUtil.isNotEmpty(goodsId)) {
            throw new BusinessException("goodsId为空");
        }
        QueryWrapper<SkuGoodInfo> query = MybatisPlusUtil.query();
        query.eq("goods_id", goodsId);
        List<SkuGoodInfo> skuGoodInfoList = repository.list(query);
        if (CollUtil.isEmpty(skuGoodInfoList)) {
            return null;
        }
        List<PCMerchMarketPtSeckillVO.AllSkuVO> allSkuVOList = new ArrayList<>();
        for (SkuGoodInfo skuGoodInfo : skuGoodInfoList) {
            PCMerchMarketPtSeckillVO.AllSkuVO allSkuVO = new PCMerchMarketPtSeckillVO.AllSkuVO();
            BeanUtils.copyProperties(skuGoodInfo, allSkuVO);
            allSkuVO.setSkuId(skuGoodInfo.getId());
            allSkuVO.setSaleSkuPrice(skuGoodInfo.getPointPrice());
            allSkuVOList.add(allSkuVO);
        }
        return allSkuVOList;
    }

    private Integer getSkuStockNum(String shopId, String skuId) {
        CommonStockVO.InnerStockVO stockVO = commonStockRpc.queryStock(null, shopId, skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null) {
            return stockVO.getQuantity();
        }
        return 0;
    }

}
