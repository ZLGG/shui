package com.gs.lshly.rpc.api.bbc.user;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-27
*/
public interface IBbcUserRpc {

    /**
     * 会员信息
     * @param qto
     * @return
     */
    BbcUserVO.DetailVO getUserInfo(BbcUserQTO.QTO qto);

    /**
     * 会员信息
     * @param qto
     * @return
     */
    BbcUserVO.UserTypeVO getUserType(BaseDTO dto);


    /**
     * 会员信息
     * @param userId
     * @return
     */
    BbcUserVO.InnerUserInfoVO innerGetUserInfo(String userId);

    BbcUserVO.UserIntegralVO integral(BaseDTO dto);

    List<BbcUserVO.UserIntegralRecordVO> integralLog(BbcUserDTO.IntegralLogQTO qto);

    BbcUserVO.MyIntegralVO myIntegral(String userId);

    /**
     * 无需登录，获取用户信息
     * @param dto
     * @return
     */
    BbcUserVO.DetailVO getUserInfoNoLogin(BaseDTO dto);

    String getUserPhone(String userId);
}
