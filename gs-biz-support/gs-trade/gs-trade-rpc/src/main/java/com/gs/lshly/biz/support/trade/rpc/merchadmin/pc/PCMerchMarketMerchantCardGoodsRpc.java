package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantCardGoodsRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCardGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-04
*/
@DubboService
public class PCMerchMarketMerchantCardGoodsRpc implements IPCMerchMarketMerchantCardGoodsRpc{
    @Autowired
    private IPCMerchMarketMerchantCardGoodsService  pCMerchMarketMerchantCardGoodsService;

    @Override
    public PageData<PCMerchMarketMerchantCardGoodsVO.ListVO> pageData(PCMerchMarketMerchantCardGoodsQTO.QTO qto){
        return pCMerchMarketMerchantCardGoodsService.pageData(qto);
    }

    @Override
    public void addMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto){
        pCMerchMarketMerchantCardGoodsService.addMarketMerchantCardGoods(eto);
    }

    @Override
    public void deleteMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto){
        pCMerchMarketMerchantCardGoodsService.deleteMarketMerchantCardGoods(dto);
    }


    @Override
    public void editMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto){
        pCMerchMarketMerchantCardGoodsService.editMarketMerchantCardGoods(eto);
    }

    @Override
    public PCMerchMarketMerchantCardGoodsVO.DetailVO detailMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto){
        return  pCMerchMarketMerchantCardGoodsService.detailMarketMerchantCardGoods(dto);
    }

}