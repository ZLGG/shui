package com.gs.lshly.biz.support.foundation.service.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.dto.RemindPlatDTO;
import com.gs.lshly.common.struct.common.qto.RemindPlatQTO;
import com.gs.lshly.common.struct.common.vo.RemindPlatVO;

public interface IRemindPlatService {

    PageData<RemindPlatVO.ListVO> pageData(RemindPlatQTO.QTO qto);

    void deleteRemindPlat(RemindPlatDTO.IdDTO dto);

    void editRemindPlat(String id,RemindPlatDTO.ETO eto);


    RemindPlatVO.DetailVO detailRemindPlat(RemindPlatDTO.IdDTO dto);


    /**
     * 企业会员待审
     */
    void addRemindPlatForUser2bApply(RemindPlatDTO.JustDTO dto);

    /**
     * 商家入驻待审
     *
     */
    void addRemindPlatForMerchantApply(RemindPlatDTO.JustDTO dto);

    /**
     * 商品上架待审
     */
    void addRemindPlatForGoodsUpApply(RemindPlatDTO.JustDTO dto);

    /**
     * 申请类目待审核
     */
    void addRemindPlatForGoodsCategoryApply(RemindPlatDTO.JustDTO dto);

    /**
     * 商家文章待审核
     */
    void addRemindPlatForMerchantArticleApply(RemindPlatDTO.JustDTO dto);

    /**
     * 商家营销活动待审核
     */
    void addRemindPlatForMarket(RemindPlatDTO.JustDTO dto);

    /**
     * 商家报名活动待审核
     */
    void addRemindForJoinActivity(RemindPlatDTO.JustDTO dto);

    /**
     * 评论申诉待审核
     */
    void addRemindForCommentApply(RemindPlatDTO.JustDTO dto);

    /**
     * 订单投诉待审核
     */
    void addRemindForOrderComplain(RemindPlatDTO.JustDTO dto);

    /**
     * 售后申请待审核
     */
    void addRemindForAfterService(RemindPlatDTO.JustDTO dto);

    /**
     * 退款待审核
     */
    void addRemindForBackMoney(RemindPlatDTO.JustDTO dto);

    /**
     * 商家意见反馈待审核
     */
    void addRemindForMerchantFackback(RemindPlatDTO.JustDTO dto);

    /**
     * 会员意见反馈提醒
     */
    void addRemindForUserFackback(RemindPlatDTO.JustDTO dto);

    /**
     * 转账待审核
     */
    void addRemindForTransferMoney(RemindPlatDTO.JustDTO dto);

}