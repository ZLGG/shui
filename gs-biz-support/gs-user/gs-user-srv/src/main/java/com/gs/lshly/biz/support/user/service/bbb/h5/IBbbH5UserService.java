package com.gs.lshly.biz.support.user.service.bbb.h5;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;

public interface IBbbH5UserService {

    /**
     * 会员信息
     * @param qto
     * @return
     */
    BbbH5UserVO.DetailVO getUserInfo(BbbH5UserQTO.QTO qto);


    BbbH5UserVO.InnerUserInfoVO innerGetUserInfo(String userId);

    void editorUserInfo(BbbH5UserDTO.UserInfoETO dto);
}