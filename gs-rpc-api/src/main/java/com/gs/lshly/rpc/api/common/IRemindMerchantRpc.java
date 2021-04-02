package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;

/**
*
* @author xxfc
* @since 2021-02-05
*/
public interface IRemindMerchantRpc {

    PageData<RemindMerchantVO.ListVO> merchantPageData(RemindMerchantQTO.QTO qto);

    PageData<RemindMerchantVO.ListVO> platPageData(RemindMerchantQTO.QTO qto);

    void deleteRemindMerchant(RemindMerchantDTO.IdDTO dto);

    void editRemindMerchant(String id,RemindMerchantDTO.ETO eto);

    RemindMerchantVO.DetailVO detailRemindMerchant(RemindMerchantDTO.IdDTO dto);

    /**
     * 待发货提醒
     */
    void addRemindMerchantForSendGoods(RemindMerchantDTO.JustDTO dto);


    /**
     * 用户催单提醒
     */
    void addRemindMerchantForAskOrder(RemindMerchantDTO.JustDTO dto);


    /**
     * 取消订单待审核
     */
    void addRemindMerchantForCloseOrder(RemindMerchantDTO.JustDTO dto);

    /**
     * 活动报名提醒
     */
    void addRemindMerchantForActivity(RemindMerchantDTO.JustDTO dto);

    /**
     * 退换货待审核
     */
    void addRemindMerchantForBackChangeGoods(RemindMerchantDTO.JustDTO dto);

    /**
     * 咨询待回复提醒
     */
    void addRemindMerchantForAskTalk(RemindMerchantDTO.JustDTO dto);

    /**
     * 平台-》商家通知提醒
     */
    void addRemindMerchantForPlatNotic(RemindMerchantDTO.NoticAcctAllDTO dto);

    void addRemindMerchantForPlatNotic(RemindMerchantDTO.NoticAcctDTO dto);
}