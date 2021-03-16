package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutGoodsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantCutGoodsRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCutGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-08
*/
@DubboService
public class PCMerchMarketMerchantCutGoodsRpc implements IPCMerchMarketMerchantCutGoodsRpc{
    @Autowired
    private IPCMerchMarketMerchantCutGoodsService  pCMerchMarketMerchantCutGoodsService;

    @Override
    public PageData<PCMerchMarketMerchantCutGoodsVO.ListVO> pageData(PCMerchMarketMerchantCutGoodsQTO.QTO qto){
        return pCMerchMarketMerchantCutGoodsService.pageData(qto);
    }

    @Override
    public void addMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto){
        pCMerchMarketMerchantCutGoodsService.addMarketMerchantCutGoods(eto);
    }

    @Override
    public void deleteMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto){
        pCMerchMarketMerchantCutGoodsService.deleteMarketMerchantCutGoods(dto);
    }


    @Override
    public void editMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto){
        pCMerchMarketMerchantCutGoodsService.editMarketMerchantCutGoods(eto);
    }

    @Override
    public PCMerchMarketMerchantCutGoodsVO.DetailVO detailMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto){
        return  pCMerchMarketMerchantCutGoodsService.detailMarketMerchantCutGoods(dto);
    }

}