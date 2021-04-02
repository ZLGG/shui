package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.dto.RemindUserDTO;
import com.gs.lshly.common.struct.common.qto.RemindUserQTO;
import com.gs.lshly.common.struct.common.vo.RemindUserVO;
import io.swagger.annotations.ApiModelProperty;

/**
*
* @author xxfc
* @since 2021-02-05
*/
public interface IRemindUserRpc {

    PageData<RemindUserVO.ListVO> clientPageData(RemindUserQTO.QTO qto);

    PageData<RemindUserVO.ListVO> platPageData(RemindUserQTO.QTO qto);

    void deleteRemindUser(RemindUserDTO.IdDTO dto);

    void editRemindUser(String id,RemindUserDTO.ETO eto);

    void readRemindMerchant(String id, BaseDTO dto);

    RemindUserVO.DetailVO detailRemindUser(RemindUserDTO.IdDTO dto);

    /**
     * 物流状态通知提醒 2c
     */
    void addRemindUserForLogisticsStates2C(RemindUserDTO.LogisticsStatesExtDTO dto);

    /**
     * 物流状态通知提醒 2b
     */
    void addRemindUserForLogisticsStates2B(RemindUserDTO.LogisticsStatesExtDTO dto);
}