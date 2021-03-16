package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ITradePayOfflineService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayOfflineDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayOfflineQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayOfflineVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.trade.ITradePayOfflineRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author tangxu
 * @version 1.0
 * @date 2020/12/11 10:08
 */
@DubboService
public class TradePayOfflineOfflineRpc implements ITradePayOfflineRpc {

    @Autowired
    private ITradePayOfflineService iTradePayOfflineService;

    @Override
    public PageData<TradePayOfflineVO.ListVO> pageData(TradePayOfflineQTO.QTO qto) {
        return iTradePayOfflineService.pageData(qto);
    }

    @Override
    public TradePayOfflineVO.DetailVO showDetail(String id) {
        return iTradePayOfflineService.showDetail(id);
    }

    @Override
    public Boolean verify(TradePayOfflineDTO.DTO dto) {
        return iTradePayOfflineService.verify(dto);
    }

    @Override
    public void updateByRefuse(TradePayOfflineDTO.RefuseDTO dto) {
        iTradePayOfflineService.updateByRefuse(dto);
    }

    @Override
    public ExportDataDTO export(TradePayOfflineQTO.IdListQTO qo) throws Exception {
        return ExcelUtil.treatmentBean(iTradePayOfflineService.exportPayOfficeData(qo), TradePayOfflineVO.ListVO.class);
    }
}
