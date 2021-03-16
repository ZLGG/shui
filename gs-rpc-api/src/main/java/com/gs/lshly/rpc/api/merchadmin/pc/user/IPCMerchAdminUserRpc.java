package com.gs.lshly.rpc.api.merchadmin.pc.user;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;

/**
*
* @author xxfc
* @since 2020-10-05
*/
public interface IPCMerchAdminUserRpc {

    /**
     * 私域会员分页列表
     */
    void pageListForPrivateUserList();

    /**
     * 私域会员审核
     */
    void applyForPrivateUser();

    /**
     * 会员详情
     * @param dto
     * @return
     */
    UserVO.DetailVO detailForPrivateUser(UserDTO.IdDTO dto);

}