package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantGiftGoodsRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGiftGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-09
*/
@DubboService
public class PCMerchMarketMerchantGiftGoodsRpc implements IPCMerchMarketMerchantGiftGoodsRpc{
    @Autowired
    private IPCMerchMarketMerchantGiftGoodsService  pCMerchMarketMerchantGiftGoodsService;

    @Override
    public PageData<PCMerchMarketMerchantGiftGoodsVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsQTO.QTO qto){
        return pCMerchMarketMerchantGiftGoodsService.pageData(qto);
    }

    @Override
    public void addMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto){
        pCMerchMarketMerchantGiftGoodsService.addMarketMerchantGiftGoods(eto);
    }

    @Override
    public void deleteMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto){
        pCMerchMarketMerchantGiftGoodsService.deleteMarketMerchantGiftGoods(dto);
    }


    @Override
    public void editMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto){
        pCMerchMarketMerchantGiftGoodsService.editMarketMerchantGiftGoods(eto);
    }

    @Override
    public PCMerchMarketMerchantGiftGoodsVO.DetailVO detailMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto){
        return  pCMerchMarketMerchantGiftGoodsService.detailMarketMerchantGiftGoods(dto);
    }

}