package com.gs.lshly.rpc.api.platadmin.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLeveDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLeveDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLeveDictVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-05
*/
public interface IUserLeveDictRpc {

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