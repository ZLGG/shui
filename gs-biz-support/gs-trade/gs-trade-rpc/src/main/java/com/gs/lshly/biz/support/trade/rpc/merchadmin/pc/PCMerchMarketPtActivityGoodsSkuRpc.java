package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtActivityGoodsSkuService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSkuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityGoodsSkuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityGoodsSkuVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtActivityGoodsSkuRpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-02
*/
@DubboService
public class PCMerchMarketPtActivityGoodsSkuRpc implements IPCMerchMarketPtActivityGoodsSkuRpc{
    @Autowired
    private IPCMerchMarketPtActivityGoodsSkuService pCMerchMarketPtActivityGoodsSkuService;

    @Override
    public PageData<PCMerchMarketPtActivityGoodsSkuVO.ListVO> pageData(PCMerchMarketPtActivityGoodsSkuQTO.QTO qto){
        return pCMerchMarketPtActivityGoodsSkuService.pageData(qto);
    }

    @Override
    public void addMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto){
        pCMerchMarketPtActivityGoodsSkuService.addMarketPtActivityGoodsSku(eto);
    }

    @Override
    public void deleteMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto){
        pCMerchMarketPtActivityGoodsSkuService.deleteMarketPtActivityGoodsSku(dto);
    }


    @Override
    public void editMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto){
        pCMerchMarketPtActivityGoodsSkuService.editMarketPtActivityGoodsSku(eto);
    }

    @Override
    public PCMerchMarketPtActivityGoodsSkuVO.DetailVO detailMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto){
        return  pCMerchMarketPtActivityGoodsSkuService.detailMarketPtActivityGoodsSku(dto);
    }

}