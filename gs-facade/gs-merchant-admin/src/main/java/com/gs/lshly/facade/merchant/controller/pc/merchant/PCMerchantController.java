package com.gs.lshly.facade.merchant.controller.pc.merchant;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchantVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchantRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/merchant/info")
@Api(tags = "商家基础信息-v1.1.0")
public class PCMerchantController {

    @DubboReference
    private IPCMerchantRpc pcmerchantRpc;

    @ApiOperation("获取当前商户基础信息-v1.1.0")
    @GetMapping("/get")
    public ResponseData<PCMerchantVO.DetailVO> getMerchantInfo(PCMerchantDTO.DTO dto) {
        return ResponseData.data(pcmerchantRpc.getMerchant(dto));
    }
    
    @ApiOperation("修改当前商户基础信息-v1.1.0")
    @PostMapping("/modify")
    public ResponseData<Void> modifyMerchantInfo(@Valid @RequestBody PCMerchantDTO.ETO eto) {
    	pcmerchantRpc.modifyMerchant(eto);
        return ResponseData.data(MsgConst.SUBMIT_SUCCESS);
    }
}
