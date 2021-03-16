package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeCommentImgRpc;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentImgService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-11-17
*/
@DubboService
public class TradeCommentImgRpc implements ITradeCommentImgRpc{
    @Autowired
    private ITradeCommentImgService  TradeCommentImgService;


}