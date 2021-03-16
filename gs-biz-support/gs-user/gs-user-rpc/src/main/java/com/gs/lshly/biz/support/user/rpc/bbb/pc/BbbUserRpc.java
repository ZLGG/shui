package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-27
*/
@DubboService
public class BbbUserRpc implements IBbbUserRpc {

    @Autowired
    private IBbbUserService bbbUserService;

    @Override
    public BbbUserVO.DetailVO getUserInfo(BbbUserQTO.QTO qto) {

        return bbbUserService.getUserInfo(qto);
    }

    @Override
    public BbbUserVO.UserTypeVO userType(BaseDTO dto) {
        return bbbUserService.userType(dto);
    }

    @Override
    public BbbUserVO.UserIntegralVO integral(BaseDTO dto) {
        return bbbUserService.integral(dto);
    }

    @Override
    public PageData<BbbUserVO.UserIntegralRecordVO> integralLog(BbbUserQTO.IntegralLogQTO qto) {
        return bbbUserService.integralLog(qto);
    }

    @Override
    public BbbUserVO.EditorUserInfoVO getEditorUserInfo(BaseDTO dto) {
        return bbbUserService.getEditorUserInfo(dto);
    }

    @Override
    public void editorUserInfo(BbbUserDTO.UserInfoETO dto) {
        bbbUserService.editorUserInfo(dto);
    }

    @Override
    public void editorPassword(BbbUserDTO.EditorPasswordETO dto) {
        bbbUserService.editorPassword(dto);
    }

    @Override
    public void editorPayPassword(BbbUserDTO.EditorPayPasswordDTO dto) {
        bbbUserService.editorPayPassword(dto);
    }

    @Override
    public void editorUserName(BbbUserDTO.EditorUserNameDTO dto) {
        bbbUserService.editorUserName(dto);
    }

    @Override
    public void bindEmail(BbbUserDTO.BindEmailDTO dto) {
        bbbUserService.bindEmail(dto);
    }

    @Override
    public void bindPhone(BbbUserDTO.BindPhoneDTO dto) {
        bbbUserService.bindPhone(dto);
    }

    @Override
    public BbbUserVO.CheckPasswordVO checkPassword(BbbUserDTO.CheckPasswordETO dto) {
        return bbbUserService.checkPassword(dto);
    }

    @Override
    public BbbUserVO.InnerUserInfoVO innerGetUserInfo(String userId) {
        return bbbUserService.innerGetUserInfo(userId);
    }

    @Override
    public BbbUserVO.PrivateUserInfoVO oneShopPrivateUserInfo(String shopId, String userId) {
        return bbbUserService.oneShopPrivateUserInfo(shopId,userId);
    }

    @Override
    public void signInIntegralLog(BaseDTO dto) {
        bbbUserService.signInIntegralLog(dto);
    }

    @Override
    public BbbUserVO.UserIntegralStatusVO signInIntegralLogState(BaseDTO dto) {
        return bbbUserService.signInIntegralLogState(dto);
    }

    @Override
    public BbbUserVO.InnerUserInfoVO innerUserVo(BaseDTO dto) {
        return bbbUserService.innerUserVo(dto);
    }


}