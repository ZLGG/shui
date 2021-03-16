package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;

public interface IPCMerchMerchantHomeDashboardService {
    MerchantHomeDashboardVO.ListVO HomeDashboard(MerchantHomeDashboardDTO.ETO dto);
}
