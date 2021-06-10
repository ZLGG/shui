package com.gs.lshly.facade.merchant.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillMerchantRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 秒杀秒杀参与记录管理
 *
 * 
 * @author hanly
 * @date 2021年6月10日 上午10:11:04
 */
@RestController
@RequestMapping("/merchadmin/marketPtSeckill")
@Api(tags = "商家秒杀活动管理管理-v1.1.0")
@SuppressWarnings("unchecked")
public class PCMerchMarketPtSeckillController {

    @DubboReference
    private IPCMerchMarketPtSeckillRpc ipcMerchMarketPtSeckillRpc;


}
