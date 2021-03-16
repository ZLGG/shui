package com.gs.lshly.biz.support.stock.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockQTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockVO;

import java.util.List;

public interface IPCMerchStockService {

    PCMerchStockVO.StockAlarmGoodsIdListVO storeCall(String shopId, Integer changeQuantity);

    List<PCMerchStockVO.InnerRoliceVO>  innerStockRolice(BaseDTO baseDTO, Integer changeQuantity, String shopId);

}