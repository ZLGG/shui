package com.gs.lshly.biz.support.stock.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockShopLogisticsCorpDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockShopLogisticsCorpQTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockShopLogisticsCorpVO;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockShopLogisticsCorpRpc;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchStockShopLogisticsCorpService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author R
* @since 2020-10-24
*/
@DubboService
public class PCMerchStockShopLogisticsCorpRpc implements IPCMerchStockShopLogisticsCorpRpc {

    @Autowired
    private IPCMerchStockShopLogisticsCorpService pCMerchStockShopLogisticsCorpService;


    @Override
    public List<PCMerchStockShopLogisticsCorpVO.ListVO> pageData(PCMerchStockShopLogisticsCorpQTO.QTO qto) {


        return pCMerchStockShopLogisticsCorpService.pageData(qto);
    }

    @Override
    public List<PCMerchStockShopLogisticsCorpVO.ListVO> enableList(BaseDTO dto) {
        return pCMerchStockShopLogisticsCorpService.enableList(dto);
    }

    @Override
    public void enable(PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO) {
        pCMerchStockShopLogisticsCorpService.enable(idDTO);
    }

    @Override
    public void disable(PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO) {
        pCMerchStockShopLogisticsCorpService.disable(idDTO);
    }


}