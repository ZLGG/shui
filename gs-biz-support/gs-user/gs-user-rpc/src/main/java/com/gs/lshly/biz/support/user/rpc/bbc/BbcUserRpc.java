package com.gs.lshly.biz.support.user.rpc.bbc;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO.DetailVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO.UserTypeVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-27
*/
@DubboService
public class BbcUserRpc implements IBbcUserRpc {

    @Autowired
    private IBbcUserService bbcUserService;


    @Override
    public BbcUserVO.DetailVO getUserInfo(BbcUserQTO.QTO qto) {

        return bbcUserService.getUserInfo(qto);
    }

    @Override
    public BbcUserVO.InnerUserInfoVO innerGetUserInfo(String userId) {
        return bbcUserService.innerGetUserInfo(userId);
    }

    @Override
    public BbcUserVO.UserIntegralVO integral(BaseDTO dto) {
        return bbcUserService.integral(dto);
    }

    @Override
    public BbcUserVO.MyIntegralVO myIntegral(String userId) {
        return bbcUserService.myIntegral(userId);
    }

    @Override
    public List<BbcUserVO.UserIntegralRecordVO> integralLog(BbcUserDTO.IntegralLogQTO qto) {
        return bbcUserService.integralLog(qto);
    }

	@Override
	public DetailVO getUserInfoNoLogin(BaseDTO dto) {
		// TODO Auto-generated method stub
		return bbcUserService.getUserInfoNoLogin(dto);
	}

	@Override
	public UserTypeVO getUserType(BaseDTO dto) {
		return bbcUserService.getUserType(dto);
	}


}