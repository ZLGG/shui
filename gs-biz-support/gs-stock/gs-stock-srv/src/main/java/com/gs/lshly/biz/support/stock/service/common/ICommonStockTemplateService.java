package com.gs.lshly.biz.support.stock.service.common;

import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

import java.util.List;
import java.util.Map;

public interface ICommonStockTemplateService {

    CommonStockTemplateVO.ListDetailVO getDetail(String templdateId);

    void fillComplexTypeTemplate(CommonStockTemplateVO.ListDetailVO listDetailVO);

    /**
     * 快递运费模板与地址转换运费-计算参数
     * @param sku
     * @param stockTemplateDetail
     * @param addressId
     * @return
     */
    CommonDeliveryCostCalcParam parseToDeliveryCostCalcParam(CommonStockTemplateVO.SkuAmountAndPriceVO sku, CommonStockTemplateVO.ListDetailVO stockTemplateDetail, String addressId);

    /**
     * 物流模板使用次数
     * @param idDTO
     * @return
     */
    Integer usedCount(CommonStockTemplateDTO.IdDTO idDTO);

    /**
     * 物流模板配送地址
     * @param templateId
     * @return
     */
    String queryStockAddress(String templateId);
}
