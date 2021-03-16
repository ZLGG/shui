package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.commodity.entity.SkuStockAlarm;
import com.gs.lshly.biz.support.commodity.mapper.GoodsTempalteMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsTempalteRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuStockAlarmRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsStockService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsStockDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsStockQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsStockVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
* <p>
*  商品仓储物流相关服务实现类
* </p>
* @author Starry
* @since 2020-10-27
*/
@Component
public class PCMerchGoodsStockServiceImpl implements IPCMerchGoodsStockService {

    @Autowired
    private ISkuStockAlarmRepository repository;

    @Autowired
    private IGoodsTempalteRepository goodsTempalteRepository;


    @Override
    public void addSkuStockAlarm(PCMerchGoodsStockDTO.SkuAlarmETO eto) {
        SkuStockAlarm skuStockAlarm = new SkuStockAlarm();
        BeanUtils.copyProperties(eto, skuStockAlarm);
        skuStockAlarm.setShopId(eto.getJwtShopId());
        skuStockAlarm.setMerchantId(eto.getJwtMerchantId());
        repository.saveOrUpdate(skuStockAlarm);
    }

    @Override
    public PCMerchGoodsStockVO.SkuAlarmDetailVO detailSkuStockAlarm(PCMerchGoodsStockQTO.SkuAlarmQTO qto) {
        if (qto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<SkuStockAlarm> boost = MybatisPlusUtil.query();
        boost.eq("shop_id",qto.getJwtShopId());

        SkuStockAlarm alarm = repository.getOne(boost);
        if (alarm != null){
            PCMerchGoodsStockVO.SkuAlarmDetailVO detailVO = new PCMerchGoodsStockVO.SkuAlarmDetailVO();
            BeanUtils.copyProperties(alarm,detailVO);
            return detailVO;
        }
        return null;
    }

    @Override
    public Integer templateUsedCount(CommonStockTemplateDTO.IdDTO dto) {
        return goodsTempalteRepository.baseMapper().getUsedCount(dto.getId());
    }

    @Override
    public String queryStockAddress(String id) {
        return goodsTempalteRepository.baseMapper().queryStockAddress(id);
    }

}
