package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ITradeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class TradeRpc implements ITradeRpc{
    @Autowired
    private ITradeService  tradeService;


    @Override
    public PageData<TradeListVO.tradeVO> tradeListPageData(TradeQTO.TradeList qto) {
        return tradeService.tradeListPageData(qto);
    }

    @Override
    public TradeListVO.tradeVO detail(TradeDTO.IdDTO dto){
        return  tradeService.detail(dto);
    }
    @Override
    public ExportDataDTO export(TradeQTO.IdListQTO qo) throws Exception{
        return ExcelUtil.treatmentBean(tradeService.export(qo),TradeVO.ListVOExport.class);
    }

    @Override
    public TradeVO.TradeInfoVO findById(TradeGoodsVO.ListVO listVO) {
        return tradeService.findById(listVO);
    }

    @Override
    public Boolean platformCancel(TradeDTO.PlatformCancelDTO dto) {
        return tradeService.platformCancel(dto);
    }
}