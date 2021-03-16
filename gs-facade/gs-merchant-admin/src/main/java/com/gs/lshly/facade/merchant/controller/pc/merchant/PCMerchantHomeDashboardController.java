package com.gs.lshly.facade.merchant.controller.pc.merchant;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMerchantHomeDashboardRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchadmin/home")
@Api(tags = "首页仪表盘")
public class PCMerchantHomeDashboardController {

    @DubboReference
    private IPCMerchMerchantHomeDashboardRpc ipcMerchMerchantHomeDashboardRpc;

    @ApiOperation("首页仪表盘")
    @GetMapping("")
    public ResponseData<MerchantHomeDashboardVO.ListVO> HomeDashboard(MerchantHomeDashboardDTO.ETO dto) {
        return ResponseData.data(ipcMerchMerchantHomeDashboardRpc.HomeDashboard(dto));
    }
}
