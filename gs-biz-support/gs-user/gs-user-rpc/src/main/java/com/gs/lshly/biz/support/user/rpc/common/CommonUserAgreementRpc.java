package com.gs.lshly.biz.support.user.rpc.common;

import com.gs.lshly.biz.support.user.service.common.ICommonUserAgreementService;
import com.gs.lshly.common.struct.common.dto.CommonUserAgreementDTO;
import com.gs.lshly.common.struct.common.qto.CommonUserAgreementQTO;
import com.gs.lshly.common.struct.common.vo.CommonUserAgreementVO;
import com.gs.lshly.rpc.api.common.ICommonUserAgreementRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-05
*/
@DubboService
public class CommonUserAgreementRpc implements ICommonUserAgreementRpc {

    @Autowired
    private ICommonUserAgreementService userAgreementService;


    @Override
    public void editUserAgreement(CommonUserAgreementDTO.ETO eto) {
        userAgreementService.editUserAgreement(eto);
    }

    @Override
    public CommonUserAgreementVO.DetailVO detailUserAgreement(CommonUserAgreementQTO.QTO dto) {
        return userAgreementService.detailUserAgreement(dto);
    }
}