package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtSeckillService;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 下午5:15:46
 */
@DubboService
public class PCMerchMarketPtSeckillRpc implements IPCMerchMarketPtSeckillRpc {
    
	@Autowired
    private IPCMerchMarketPtSeckillService ipcMerchMarketPtSeckillService;

}