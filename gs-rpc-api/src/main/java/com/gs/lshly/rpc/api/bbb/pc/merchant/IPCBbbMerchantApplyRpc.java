package com.gs.lshly.rpc.api.bbb.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.PCBbbMerchantApplyDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.qto.PCBbbMerchantApplyQTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantApplyVO;

/**
*
* @author xxfc
* @since 2020-10-22
*/
public interface IPCBbbMerchantApplyRpc {

    void addMerchantApply(PCBbbMerchantApplyDTO.ETO eto);

}