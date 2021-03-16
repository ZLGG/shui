package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsQaVO;

/**
 * @Author Starry
 * @Date 10:07 2020/10/17
 */
public interface IPCMerchAdminGoodsQaRpc {
    /**
     * 商品咨询分页列表
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsQaVO.ReplyListVO> pageMerchantGoodsQa(PCMerchGoodsQaQTO.GoodsQaQTO qto);

    /**
     * 商家回复该商品的咨询内容
     * @param eto
     */
   void replyGoodsQa(PCMerchGoodsQaDTO.MerchantReplyETO eto);

    /**
     * 是否将咨询内容显示在商品详情页
     * @param eto
     */
    void IsShowGoodsQaContent(PCMerchGoodsQaDTO.ShowContentETO eto);

    /**
     * 删除商家回复内容
     * @param dto
     */
    void deleteGoodsQaReply(PCMerchGoodsQaDTO.IdDTO dto);
}
