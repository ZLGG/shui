package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtActivityGoodsSpuService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityGoodsSpuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityGoodsSpuVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtActivityGoodsSpuRpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-01
*/
@DubboService
public class PCMerchMarketPtActivityGoodsSpuRpc implements IPCMerchMarketPtActivityGoodsSpuRpc{
    @Autowired
    private IPCMerchMarketPtActivityGoodsSpuService pCMerchMarketPtActivityGoodsSpuService;

    @Override
    public PageData<PCMerchMarketPtActivityGoodsSpuVO.ListVO> pageData(PCMerchMarketPtActivityGoodsSpuQTO.QTO qto){
        return pCMerchMarketPtActivityGoodsSpuService.pageData(qto);
    }

    @Override
    public void addMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.ETO eto){
        pCMerchMarketPtActivityGoodsSpuService.addMarketPtActivityGoodsSpu(eto);
    }

    @Override
    public void deleteMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.IdDTO dto){
        pCMerchMarketPtActivityGoodsSpuService.deleteMarketPtActivityGoodsSpu(dto);
    }


    @Override
    public void editMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.ETO eto){
        pCMerchMarketPtActivityGoodsSpuService.editMarketPtActivityGoodsSpu(eto);
    }

    @Override
    public PCMerchMarketPtActivityGoodsSpuVO.DetailVO detailMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.IdDTO dto){
        return  pCMerchMarketPtActivityGoodsSpuService.detailMarketPtActivityGoodsSpu(dto);
    }

}