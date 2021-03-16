package com.gs.lshly.biz.support.user.rpc.bbb.h5;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserService;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-27
*/
@DubboService
public class BbbH5UserRpc implements IBbbH5UserRpc {

    @Autowired
    private IBbbH5UserService bbcUserService;


    @Override
    public BbbH5UserVO.DetailVO getUserInfo(BbbH5UserQTO.QTO qto) {

        return bbcUserService.getUserInfo(qto);
    }

    @Override
    public BbbH5UserVO.InnerUserInfoVO innerGetUserInfo(String userId) {
        return bbcUserService.innerGetUserInfo(userId);
    }

    @Override
    public void editorUserInfo(BbbH5UserDTO.UserInfoETO dto) {
        bbcUserService.editorUserInfo(dto);
    }


}