package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeMarginDetailRpc;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeMarginDetailService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2020-12-10
*/
@DubboService
public class TradeMarginDetailRpc implements ITradeMarginDetailRpc{
    @Autowired
    private ITradeMarginDetailService  TradeMarginDetailService;

    @Override
    public PageData<TradeMarginDetailVO.ListVO> pageData(TradeMarginDetailQTO.QTO qto){
        return TradeMarginDetailService.pageData(qto);
    }

    @Override
    public ExportDataDTO export(TradeMarginDetailQTO.IdListQTO qo) throws Exception {
        return ExcelUtil.treatmentBean(TradeMarginDetailService.exportMarginDetailData(qo), TradeMarginDetailVO.ListVO.class);
    }
}