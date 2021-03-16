package com.gs.lshly.biz.support.trade.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeRightsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeRightsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-2-1
*/
@DubboService
public class H5MerchTradeRightsRpc implements IH5MerchTradeRightsRpc {
    @Autowired
    private IH5MerchTradeRightsService ih5MerchTradeRightsService;

    @Override
    public PageData<H5MerchTradeRightsVO.RightList> pageData(H5MerchTradeRightsQTO.QTO qto){
        return ih5MerchTradeRightsService.pageData(qto);
    }

    @Override
    public void addTradeRights(PCMerchTradeRightsDTO.ETO eto){
        ih5MerchTradeRightsService.addTradeRights(eto);
    }

    @Override
    public void deleteTradeRights(PCMerchTradeRightsDTO.IdDTO dto){
        ih5MerchTradeRightsService.deleteTradeRights(dto);
    }


    @Override
    public void editTradeRights(PCMerchTradeRightsDTO.ETO eto){
        ih5MerchTradeRightsService.editTradeRights(eto);
    }
    @Override
    public H5MerchTradeRightsVO.DetailVO detailTradeRights(H5MerchTradeRightsDTO.IdDTO dto){
        return  ih5MerchTradeRightsService.detailTradeRights(dto);
    }

    @Override
    public void check(H5MerchTradeRightsDTO.IdCheckDTO dto) {
        ih5MerchTradeRightsService.check(dto);
    }

    @Override
    public void receivedGet(H5MerchTradeRightsDTO.IdDTO qto) {
        ih5MerchTradeRightsService.receivedGet(qto);
    }

    @Override
    public void send(H5MerchTradeRightsDTO.SendDTO dto) {
        ih5MerchTradeRightsService.send(dto);
    }


}