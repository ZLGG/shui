package com.gs.lshly.rpc.api.merchadmin.pc.stock;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockVO;

import java.util.List;

/**
*
* @author zhaozhigang
* @since 2020-10-22
*/
public interface IPCMerchStockRpc {

    PCMerchStockVO.StockAlarmGoodsIdListVO storeCall(String shopId, Integer changeQuantity);

    List<PCMerchStockVO.InnerRoliceVO>  innerStockRolice(BaseDTO baseDTO, Integer changeQuantity, String shopId);
}