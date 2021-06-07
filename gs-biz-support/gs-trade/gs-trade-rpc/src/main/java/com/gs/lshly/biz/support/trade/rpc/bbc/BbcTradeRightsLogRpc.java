package com.gs.lshly.biz.support.trade.rpc.bbc;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsLogService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsLogQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsLogVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRightsLogRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRightsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hanly
 * @since 2021-06-07
 */
@DubboService
public class BbcTradeRightsLogRpc implements IBbcTradeRightsLogRpc {
    @Autowired
    private IBbcTradeRightsLogService tradeRightsLogService;

    @Override
    public List<BbcTradeRightsLogVO.listVO> rightsLog(BbcTradeRightsLogQTO.QTO qto) {
        return tradeRightsLogService.rightsLog(qto);
    }
}