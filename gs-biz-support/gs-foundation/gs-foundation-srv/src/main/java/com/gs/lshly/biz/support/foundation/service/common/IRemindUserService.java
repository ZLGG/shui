package com.gs.lshly.biz.support.foundation.service.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindUserDTO;
import com.gs.lshly.common.struct.common.qto.RemindUserQTO;
import com.gs.lshly.common.struct.common.vo.RemindUserVO;

public interface IRemindUserService {

    PageData<RemindUserVO.ListVO> pageData(RemindUserQTO.QTO qto);

    void addRemindUser(RemindUserDTO.ETO eto);

    void deleteRemindUser(RemindUserDTO.IdDTO dto);

    void editRemindUser(String id,RemindUserDTO.ETO eto);

    RemindUserVO.DetailVO detailRemindUser(RemindUserDTO.IdDTO dto);

}