package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;

/**
*
* @author xxfc
* @since 2020-12-25
*/
public interface IPCMerchMerchantHomeDashboardRpc {


    MerchantHomeDashboardVO.ListVO HomeDashboard(MerchantHomeDashboardDTO.ETO dto);
}