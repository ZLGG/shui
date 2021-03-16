package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsImgVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRightsImgRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsImgService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-17
*/
@DubboService
public class PCMerchTradeRightsImgRpc implements IPCMerchTradeRightsImgRpc{
    @Autowired
    private IPCMerchTradeRightsImgService  pCMerchTradeRightsImgService;

    @Override
    public PageData<PCMerchTradeRightsImgVO.ListVO> pageData(PCMerchTradeRightsImgQTO.QTO qto){
        return pCMerchTradeRightsImgService.pageData(qto);
    }

    @Override
    public void addTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto){
        pCMerchTradeRightsImgService.addTradeRightsImg(eto);
    }

    @Override
    public void deleteTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto){
        pCMerchTradeRightsImgService.deleteTradeRightsImg(dto);
    }


    @Override
    public void editTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto){
        pCMerchTradeRightsImgService.editTradeRightsImg(eto);
    }

    @Override
    public PCMerchTradeRightsImgVO.DetailVO detailTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto){
        return  pCMerchTradeRightsImgService.detailTradeRightsImg(dto);
    }

}