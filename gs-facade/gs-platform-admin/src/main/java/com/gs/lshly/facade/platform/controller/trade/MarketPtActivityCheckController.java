package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtActivityCheckRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/platadmin/marketPtActivityCheck")
@Api(tags = "平台活动审核")
//@Module(code = "activityRegistrationReview",parent = "marketing",name = "活动报名审核",index = 7)
public class MarketPtActivityCheckController {
    @DubboReference
    private IMarketPtActivityCheckRpc iMarketPtActivityCheckRpc;

    @ApiOperation("审核列表")
    @GetMapping("")
    //@Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketPtActivityMerchantVO.platformCheck>> noList(MarketPtActivityQTO.QTO qto) {
        return ResponseData.data(iMarketPtActivityCheckRpc.noChcekList(qto));
    }
    @ApiOperation("查看")
    @GetMapping("/view")
    //@Func(code = "view",name = "查")
    public ResponseData<PCMerchMarketPtActivityMerchantVO.platformCheckView> view(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto) {
        return ResponseData.data(iMarketPtActivityCheckRpc.checkView(dto));
    }
    @ApiOperation("审核通过")
    @GetMapping("/CheckSuccess")
    //@Func(code = "edit",name = "改")
    public ResponseData<Void> CheckSuccess(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto) {
        iMarketPtActivityCheckRpc.checkSuccess(dto);
        return ResponseData.data(MsgConst.APPLY_SUCCESS);
    }
    @ApiOperation("审核驳回")
    @PostMapping("/CheckFail")
    //@Func(code = "edit",name = "改")
    public ResponseData<Void> CheckFail(@Valid @RequestBody PCMerchMarketPtActivityMerchantDTO.idRecordRejectionDTO dto) {
        iMarketPtActivityCheckRpc.CheckFail(dto);
        return ResponseData.data(MsgConst.APPLY_Fail);
    }


}

