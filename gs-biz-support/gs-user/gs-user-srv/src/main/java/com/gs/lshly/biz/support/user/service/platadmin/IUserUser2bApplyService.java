package com.gs.lshly.biz.support.user.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserUser2bApplyDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserUser2bApplyQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserUser2bApplyVO;

public interface IUserUser2bApplyService {

    PageData<UserUser2bApplyVO.ListVO> pageData(UserUser2bApplyQTO.QTO qto);

    void addUserUser2bApply(UserUser2bApplyDTO.ETO eto);

    void deleteUserUser2bApply(UserUser2bApplyDTO.IdDTO dto);

    void deleteBatchUserUser2bApply(UserUser2bApplyDTO.IdListDTO dto);

    void editUserUser2bApply(UserUser2bApplyDTO.ETO eto);

    UserUser2bApplyVO.DetailVO detailUserUser2bApply(UserUser2bApplyDTO.IdDTO dto);

    void applyUserUser2bApply(UserUser2bApplyDTO.ApplyDTO dto);

}