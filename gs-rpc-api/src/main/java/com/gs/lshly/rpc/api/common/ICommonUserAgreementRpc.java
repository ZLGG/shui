package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.struct.common.dto.CommonUserAgreementDTO;
import com.gs.lshly.common.struct.common.qto.CommonUserAgreementQTO;
import com.gs.lshly.common.struct.common.vo.CommonUserAgreementVO;

/**
*
* @author xxfc
* @since 2020-10-05
*/
public interface ICommonUserAgreementRpc {

    void editUserAgreement(CommonUserAgreementDTO.ETO eto);

    CommonUserAgreementVO.DetailVO detailUserAgreement(CommonUserAgreementQTO.QTO dto);

}