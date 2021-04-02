package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ITradePayService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.trade.ITradePayRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class TradePayRpc implements ITradePayRpc{
    @Autowired
    private ITradePayService  TradePayService;

    @Override
    public PageData<TradePayVO.ListVO> pageData(TradePayQTO.QTO qto){
        return TradePayService.pageData(qto);
    }

    @Override
    public TradePayVO.DetailVO detailTradePay(TradePayDTO.IdDTO dto){
        return  TradePayService.detailTradePay(dto);
    }

    @Override
    public PageData<TradePayVO.RelationDetailVO> relationList(TradePayQTO.RelationQTO qto) {
        return TradePayService.relationList(qto);
    }

    @Override
    public TradePayVO.DetailVO relationGet(TradePayDTO.IdDTO idDTO) {
        return TradePayService.relationGet(detailTradePay(idDTO));
    }

    @Override
    public ExportDataDTO export(TradePayDTO.IdsDTO qo) throws Exception{
        return ExcelUtil.treatmentBean(TradePayService.export(qo),TradePayVO.RelationDetailExport.class);
    }

    @Override
    public ExportDataDTO payExport(TradePayQTO.IdListQTO qo) throws Exception{
        return  ExcelUtil.treatmentBean(TradePayService.payExport(qo),TradePayVO.ListVOExport.class);
    }

    @Override
    public void delete(TradePayQTO.IdListQTO ids) {
        TradePayService.delete(ids);
    }


}