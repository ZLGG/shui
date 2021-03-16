package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeCommentRecordRpc;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentRecordService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-11-17
*/
@DubboService
public class TradeCommentRecordRpc implements ITradeCommentRecordRpc{
    @Autowired
    private ITradeCommentRecordService  TradeCommentRecordService;

}