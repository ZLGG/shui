package com.gs.lshly.biz.support.stock.complex;

import com.gs.lshly.biz.support.stock.entity.StockTemplateUnit;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import lombok.Data;

@Data
public class StockTeamplateUnitComplex {

    private CommonStockTemplateDTO.UnitDTO unitDTO;

    private StockTemplateUnit stockTemplateUnit;

    private CommonStockTemplateVO.UnitVO  unitVO;


}
