package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyGoodsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantGroupbuyGoodsRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGroupbuyGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-10
*/
@DubboService
public class PCMerchMarketMerchantGroupbuyGoodsRpc implements IPCMerchMarketMerchantGroupbuyGoodsRpc{
    @Autowired
    private IPCMerchMarketMerchantGroupbuyGoodsService  pCMerchMarketMerchantGroupbuyGoodsService;

    @Override
    public PageData<PCMerchMarketMerchantGroupbuyGoodsVO.ListVO> pageData(PCMerchMarketMerchantGroupbuyGoodsQTO.QTO qto){
        return pCMerchMarketMerchantGroupbuyGoodsService.pageData(qto);
    }

    @Override
    public void addMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto){
        pCMerchMarketMerchantGroupbuyGoodsService.addMarketMerchantGroupbuyGoods(eto);
    }

    @Override
    public void deleteMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto){
        pCMerchMarketMerchantGroupbuyGoodsService.deleteMarketMerchantGroupbuyGoods(dto);
    }


    @Override
    public void editMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto){
        pCMerchMarketMerchantGroupbuyGoodsService.editMarketMerchantGroupbuyGoods(eto);
    }

    @Override
    public PCMerchMarketMerchantGroupbuyGoodsVO.DetailVO detailMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto){
        return  pCMerchMarketMerchantGroupbuyGoodsService.detailMarketMerchantGroupbuyGoods(dto);
    }

}