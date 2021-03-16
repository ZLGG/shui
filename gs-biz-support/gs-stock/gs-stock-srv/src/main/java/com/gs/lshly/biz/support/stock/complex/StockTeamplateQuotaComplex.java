package com.gs.lshly.biz.support.stock.complex;

import com.gs.lshly.biz.support.stock.entity.StockTemplateQuota;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import lombok.Data;

@Data
public class StockTeamplateQuotaComplex {

    private CommonStockTemplateDTO.QuotaDTO quotaDTO;

    private StockTemplateQuota stockTemplateQuota;

}
