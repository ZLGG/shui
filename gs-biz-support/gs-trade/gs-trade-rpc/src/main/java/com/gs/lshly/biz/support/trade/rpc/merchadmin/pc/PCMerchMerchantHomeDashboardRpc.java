package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;


import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMerchantHomeDashboardService;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMerchantHomeDashboardRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PCMerchMerchantHomeDashboardRpc implements IPCMerchMerchantHomeDashboardRpc {
    @Autowired
    private IPCMerchMerchantHomeDashboardService ipcMerchMerchantHomeDashboardService;

    @Override
    public MerchantHomeDashboardVO.ListVO HomeDashboard(MerchantHomeDashboardDTO.ETO dto) {
        return ipcMerchMerchantHomeDashboardService.HomeDashboard(dto);
    }
}
