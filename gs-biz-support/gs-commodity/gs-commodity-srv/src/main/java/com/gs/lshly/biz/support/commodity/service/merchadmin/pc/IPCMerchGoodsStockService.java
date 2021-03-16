package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;
import com.gs.lshly.biz.support.commodity.mapper.GoodsTempalteMapper;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsStockDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsStockQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsStockVO;

import java.util.List;
import java.util.Map;

/**
 * 商品仓储物流相关服务
 */
public interface IPCMerchGoodsStockService {

    /**
     * 设置商家店铺商品库存报警
     * @param eto
     */
    void addSkuStockAlarm(PCMerchGoodsStockDTO.SkuAlarmETO eto);


    /**
     * 展示商家店铺的商品库存报警数
     * @param qto
     * @return
     */
    PCMerchGoodsStockVO.SkuAlarmDetailVO detailSkuStockAlarm(PCMerchGoodsStockQTO.SkuAlarmQTO qto);

    /**
     * 物流模板被使用的次数
     * @param dto
     * @return
     */
    Integer templateUsedCount(CommonStockTemplateDTO.IdDTO dto);

    /**
     * 配送区域
     * @param id
     * @return
     */
    String queryStockAddress(String id);

}