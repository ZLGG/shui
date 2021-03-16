package com.gs.lshly.rpc.api.platadmin.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserUser2bApplyDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserUser2bApplyQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserUser2bApplyVO;

/**
*
* @author xxfc
* @since 2020-10-06
*/
public interface IUserUser2bApplyRpc {

    /**
     * 2b会员申请分页列表
     * @param qto
     * @return
     */
    PageData<UserUser2bApplyVO.ListVO> pageData(UserUser2bApplyQTO.QTO qto);

    void deleteUserUser2bApply(UserUser2bApplyDTO.IdDTO dto);

    /**
     * 批量删除会员申请
     * @param dto
     */
    void deleteBatchUserUser2bApply(UserUser2bApplyDTO.IdListDTO dto);

    /**
     * 编辑会员申请
     * @param eto
     */
    void editUserUser2bApply(UserUser2bApplyDTO.ETO eto);

    /**
     * 查询会员申请
     * @param dto
     * @return
     */
    UserUser2bApplyVO.DetailVO detailUserUser2bApply(UserUser2bApplyDTO.IdDTO dto);

    /**
     * 审核会员申请
     * @param dto
     */
    void applyUserUser2bApply(UserUser2bApplyDTO.ApplyDTO dto);


}