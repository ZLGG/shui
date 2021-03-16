package com.gs.lshly.biz.support.user.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLabelDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLabelDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLabelDictVO;

import java.util.List;

public interface IUserLabelDictService {

    PageData<UserLabelDictVO.ListVO> pageData(UserLabelDictQTO.QTO qto);

    List<UserLabelDictVO.ListVO> list();

    void addUserLabelDict(UserLabelDictDTO.ETO eto);

    void deleteBatchUserLabelDict(UserLabelDictDTO.IdListDTO dto);

    void editUserLabelDict(UserLabelDictDTO.ETO eto);

    void addUserLabel(UserLabelDictDTO.AddUserLabelDTO dto);

    List<UserLabelDictVO.UserLabelVO> getMoreUserLabel(UserLabelDictQTO.UserIdListQTO qto);

}