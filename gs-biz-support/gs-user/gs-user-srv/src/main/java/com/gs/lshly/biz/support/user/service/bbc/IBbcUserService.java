package com.gs.lshly.biz.support.user.service.bbc;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;

import java.util.List;


public interface IBbcUserService {

    /**
     * 会员信息
     * @param qto
     * @return
     */
    BbcUserVO.DetailVO getUserInfo(BbcUserQTO.QTO qto);


    BbcUserVO.InnerUserInfoVO innerGetUserInfo(String userId);

    BbcUserVO.UserIntegralVO integral(BaseDTO dto);

    List<BbcUserVO.UserIntegralRecordVO> integralLog(BbcUserDTO.IntegralLogQTO qto);
}