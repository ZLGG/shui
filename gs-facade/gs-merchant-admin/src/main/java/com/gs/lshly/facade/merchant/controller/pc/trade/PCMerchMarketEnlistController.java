package com.gs.lshly.facade.merchant.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;

import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtActivityMerchantRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMarketActivityEnlistRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Starry
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/merchadmin/pc/Enlist")
@Api(tags = "活动报名")
@Module(code = "eventRegistration",parent = "marketing",name = "活动报名",index = 6)
public class PCMerchMarketEnlistController {

    @DubboReference
    private IPCMarketActivityEnlistRpc ipcMarketActivityEnlistRpc;
    @DubboReference
    private IPCMerchMarketPtActivityMerchantRpc ipcMerchMarketPtActivityMerchantRpc;

    @ApiOperation("活动列表")
    @GetMapping("/list")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<MarketPtActivityVO.MerchantActivity>> list(MarketPtActivityQTO.QTO qto) {
        return ResponseData.data(ipcMarketActivityEnlistRpc.activityPageData(qto));
    }
    @ApiOperation("我的报名")
    @GetMapping("/myList")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketPtActivityMerchantVO.MyMerchantActivity>> myList(PCMerchMarketPtActivityMerchantQTO.QTO qto) {
        return ResponseData.data(ipcMerchMarketPtActivityMerchantRpc.queryMyList(qto));
    }
    @ApiOperation("历史报名")
    @GetMapping("/historyList")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketPtActivityMerchantVO.MyMerchantActivity>> myhistoryList(PCMerchMarketPtActivityMerchantQTO.QTO qto) {
        return ResponseData.data(ipcMerchMarketPtActivityMerchantRpc.queryMyList(qto));
    }
    @ApiOperation("报名")
    @PostMapping("/activitySign")
    @Func(code = "add",name = "增")
    public  ResponseData<Void> activitySign(@RequestBody PCMerchMarketPtActivityGoodsSpuDTO.Sign dto) {
        //先保存活动记录表，保存商品spu，sku
        ipcMerchMarketPtActivityMerchantRpc.merchantActivitySign(dto);
        return ResponseData.success(MsgConst.SIGN_SUCCESS);
    }
    @ApiOperation("查看详情（我的报名/历史报名）")
    @GetMapping("/viewMyOrHistoryDetail")
    @Func(code = "view",name = "查")
    public  ResponseData<MarketPtActivityVO.MerchantViewDetails> viewMyOrHistoryDetails(PCMerchMarketPtActivityMerchantDTO.IdDTO dto) {
        //先保存活动记录表，保存商品spu，sku

        return ResponseData.data(ipcMerchMarketPtActivityMerchantRpc.viewMyOrHistoryDetails(dto));
    }
    @ApiOperation("查看详情（活动列表）")
    @GetMapping("/viewActivityListDetails")
    @Func(code = "view",name = "查")
    public  ResponseData<MarketPtActivityVO.MerchantViewDetails> viewActivityListDetails(MarketPtActivityDTO.IdDTO dto) {
        //先保存活动记录表，保存商品spu，sku

        return ResponseData.data(ipcMerchMarketPtActivityMerchantRpc.viewActivityListDetails(dto));
    }



}
