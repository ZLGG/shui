package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindUserDTO;
import com.gs.lshly.common.struct.common.qto.RemindUserQTO;
import com.gs.lshly.common.struct.common.vo.RemindUserVO;

/**
*
* @author xxfc
* @since 2021-02-05
*/
public interface IRemindUserRpc {

    PageData<RemindUserVO.ListVO> pageData(RemindUserQTO.QTO qto);

    void addRemindUser(RemindUserDTO.ETO eto);

    void deleteRemindUser(RemindUserDTO.IdDTO dto);

    void editRemindUser(String id,RemindUserDTO.ETO eto);

    RemindUserVO.DetailVO detailRemindUser(RemindUserDTO.IdDTO dto);

}