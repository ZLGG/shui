package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;

/**
*
* @author xxfc
* @since 2021-02-05
*/
public interface IRemindMerchantRpc {

    PageData<RemindMerchantVO.ListVO> pageData(RemindMerchantQTO.QTO qto);

    void addRemindMerchant(RemindMerchantDTO.ETO eto);

    void deleteRemindMerchant(RemindMerchantDTO.IdDTO dto);

    void editRemindMerchant(String id,RemindMerchantDTO.ETO eto);

    RemindMerchantVO.DetailVO detailRemindMerchant(RemindMerchantDTO.IdDTO dto);

}