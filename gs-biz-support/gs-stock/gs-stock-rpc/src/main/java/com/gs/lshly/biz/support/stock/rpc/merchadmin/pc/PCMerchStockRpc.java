package com.gs.lshly.biz.support.stock.rpc.merchadmin.pc;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchStockService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockVO;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zhaozhigang
* @since 2020-10-22
*/
@DubboService
public class PCMerchStockRpc implements IPCMerchStockRpc{
    @Autowired
    private IPCMerchStockService  pCMerchStockService;

    @Override
    public PCMerchStockVO.StockAlarmGoodsIdListVO storeCall(String shopId, Integer changeQuantity) {
        PCMerchStockVO.StockAlarmGoodsIdListVO stockAlarmGoodsIdListVO = pCMerchStockService.storeCall(shopId, changeQuantity);
        return stockAlarmGoodsIdListVO;
    }


    @Override
    public List<PCMerchStockVO.InnerRoliceVO> innerStockRolice(BaseDTO baseDTO, Integer changeQuantity, String shopId) {
        return pCMerchStockService.innerStockRolice(baseDTO, changeQuantity,shopId);
    }


}