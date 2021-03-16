package com.gs.lshly.biz.support.user.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLeveDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLeveDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLeveDictVO;

import java.util.List;

public interface IUserLeveDictService {

    PageData<UserLeveDictVO.ListVO> pageData(UserLeveDictQTO.QTO qto);

    void addUserLeveDict(UserLeveDictDTO.ETO eto);

    void deleteBatchUserLeveDict(UserLeveDictDTO.IdListDTO dto);

    void editUserLeveDict(UserLeveDictDTO.ETO eto);

    UserLeveDictVO.DetailVO detailUserLeveDict(UserLeveDictDTO.IdDTO dto);

    /**
     * 会员等级列表
     * @param dto
     * @return
     */
    List<UserLeveDictVO.LevelListVO> lisLevelListVO(UserLeveDictDTO.UsingTypeDTO dto);

}