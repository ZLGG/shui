package com.gs.lshly.rpc.api.bbb.pc.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.LegalDictVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-27
*/
public interface IBbbUserRpc {

    BbbUserVO.EditorUserInfoVO getEditorUserInfo(BaseDTO dto);

    void editorUserInfo(BbbUserDTO.UserInfoETO dto);

    void editorPassword(BbbUserDTO.EditorPasswordETO dto);

    void editorPayPassword(BbbUserDTO.EditorPayPasswordDTO dto);

    void editorUserName(BbbUserDTO.EditorUserNameDTO dto);

    void bindEmail(BbbUserDTO.BindEmailDTO dto);

    void bindPhone(BbbUserDTO.BindPhoneDTO dto);

    BbbUserVO.CheckPasswordVO checkPassword(BbbUserDTO.CheckPasswordETO dto);

    BbbUserVO.DetailVO getUserInfo(BbbUserQTO.QTO qto);

    BbbUserVO.UserTypeVO userType(BaseDTO dto);

    BbbUserVO.UserIntegralVO integral(BaseDTO dto);

    PageData<BbbUserVO.UserIntegralRecordVO> integralLog(BbbUserQTO.IntegralLogQTO qto);

    BbbUserVO.InnerUserInfoVO innerGetUserInfo(String userId);

    /**
     * 获取会员在私域店铺中的会员类型
     * @param shopId
     * @param userId
     * @return
     */
    BbbUserVO.PrivateUserInfoVO oneShopPrivateUserInfo(String shopId, String userId);


    void signInIntegralLog(BaseDTO dto);

    BbbUserVO.UserIntegralStatusVO signInIntegralLogState(BaseDTO dto);

    BbbUserVO.InnerUserInfoVO innerUserVo(BaseDTO dto);

    void getEmailNum(BbbUserDTO.BandEmailDTO qto);
    
    /**
     * 客服授权信息
     * @return
     */
    String customerAuthorize(BaseDTO dto);
}