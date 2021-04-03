package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCancelVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchantVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月31日 下午2:12:55
 */
public interface IPCMerchantRpc {

    PCMerchantVO.DetailVO getMerchant(PCMerchantDTO.DTO dto);
    
    
    void modifyMerchant(PCMerchantDTO.ETO eto);

}