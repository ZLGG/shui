package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsStockDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsStockQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsStockVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsStockRpc;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsStockService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
* 商品仓储物流相关远程服务实现
* @author Starry
* @since 2020-10-27
*/
@DubboService
public class PCMerchGoodsStockRpc implements IPCMerchGoodsStockRpc {
    @Autowired
    private IPCMerchGoodsStockService goodsStockService;


    @Override
    public void addSkuStockAlarm(PCMerchGoodsStockDTO.SkuAlarmETO eto){
        goodsStockService.addSkuStockAlarm(eto);
    }

    @Override
    public PCMerchGoodsStockVO.SkuAlarmDetailVO detailSkuStockAlarm(PCMerchGoodsStockQTO.SkuAlarmQTO qto){
        return  goodsStockService.detailSkuStockAlarm(qto);
    }

    @Override
    public Integer templateUsedCount(CommonStockTemplateDTO.IdDTO dto) {
        return goodsStockService.templateUsedCount(dto);
    }

    @Override
    public String queryStockAddress(String templateId) {
        return goodsStockService.queryStockAddress(templateId);
    }

}