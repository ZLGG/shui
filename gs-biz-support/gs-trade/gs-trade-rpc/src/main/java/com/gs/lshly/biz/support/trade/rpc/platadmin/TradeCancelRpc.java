package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCancelService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCancelDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeCancelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-21
*/
@DubboService
public class TradeCancelRpc implements ITradeCancelRpc{
    @Autowired
    private ITradeCancelService  TradeCancelService;

    @Override
    public PageData<TradeCancelVO.ListVO> pageData(TradeCancelQTO.QTO qto){
        return TradeCancelService.pageData(qto);
    }

    @Override
    public TradeCancelVO.DetailVO detailTradeCancel(TradeCancelDTO.IdDTO dto){
        return  TradeCancelService.detailTradeCancel(dto);
    }

    @Override
    public ExportDataDTO export(TradeCancelQTO.IdListQTO qo) throws Exception {
        return ExcelUtil.treatmentBean(TradeCancelService.export(qo),TradeCancelVO.ListVOExport.class);
    }

    @Override
    public void delete(TradeCancelQTO.IdListQTO ids) {
        TradeCancelService.delete(ids);
    }

}