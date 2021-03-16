package com.gs.lshly.biz.support.user.service.common;
import com.gs.lshly.common.struct.common.dto.CommonUserAgreementDTO;
import com.gs.lshly.common.struct.common.qto.CommonUserAgreementQTO;
import com.gs.lshly.common.struct.common.vo.CommonUserAgreementVO;

public interface ICommonUserAgreementService {

    void editUserAgreement(CommonUserAgreementDTO.ETO eto);

    CommonUserAgreementVO.DetailVO detailUserAgreement(CommonUserAgreementQTO.QTO dto);

}