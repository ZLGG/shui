package com.gs.lshly.rpc.api.bbb.h5.user;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;

/**
*
* @author xxfc
* @since 2020-10-27
*/
public interface IBbbH5UserRpc {

    /**
     * 会员信息
     * @param qto
     * @return
     */
    BbbH5UserVO.DetailVO getUserInfo(BbbH5UserQTO.QTO qto);


    /**
     * 会员信息
     * @param userId
     * @return
     */
    BbbH5UserVO.InnerUserInfoVO innerGetUserInfo(String userId);

    void editorUserInfo(BbbH5UserDTO.UserInfoETO dto);
}