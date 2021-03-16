package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRecordVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRightsRecordRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsRecordService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-17
*/
@DubboService
public class PCMerchTradeRightsRecordRpc implements IPCMerchTradeRightsRecordRpc{
    @Autowired
    private IPCMerchTradeRightsRecordService  pCMerchTradeRightsRecordService;

    @Override
    public PageData<PCMerchTradeRightsRecordVO.ListVO> pageData(PCMerchTradeRightsRecordQTO.QTO qto){
        return pCMerchTradeRightsRecordService.pageData(qto);
    }

    @Override
    public void addTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto){
        pCMerchTradeRightsRecordService.addTradeRightsRecord(eto);
    }

    @Override
    public void deleteTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto){
        pCMerchTradeRightsRecordService.deleteTradeRightsRecord(dto);
    }


    @Override
    public void editTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto){
        pCMerchTradeRightsRecordService.editTradeRightsRecord(eto);
    }

    @Override
    public PCMerchTradeRightsRecordVO.DetailVO detailTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto){
        return  pCMerchTradeRightsRecordService.detailTradeRightsRecord(dto);
    }

}