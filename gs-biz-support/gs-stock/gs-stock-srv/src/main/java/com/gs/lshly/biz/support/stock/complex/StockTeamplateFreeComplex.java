package com.gs.lshly.biz.support.stock.complex;

import com.gs.lshly.biz.support.stock.entity.StockTemplateFree;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import lombok.Data;

@Data
public class StockTeamplateFreeComplex {

    private CommonStockTemplateDTO.FreeDTO freeDTO;

    private StockTemplateFree stockTemplateFree;

}
