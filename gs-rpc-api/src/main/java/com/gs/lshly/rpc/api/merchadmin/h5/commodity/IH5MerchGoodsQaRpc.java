package com.gs.lshly.rpc.api.merchadmin.h5.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.dto.H5MerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.qto.H5MerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.vo.H5MerchGoodsQaVO;

/**
*
* @author zst
* @since 2021-01-22
*/
public interface IH5MerchGoodsQaRpc {

    /**
     * 商品咨询分页列表
     * @param qto
     * @return
     */
    PageData<H5MerchGoodsQaVO.ReplyListVO> pageMerchantH5GoodsQa(H5MerchGoodsQaQTO.GoodsQaQTO qto);

    /**
     * 商家回复该商品的咨询内容
     * @param eto
     */
    void replyGoodsQa(H5MerchGoodsQaDTO.MerchantReplyETO eto);

    /**
     * 是否将咨询内容显示在商品详情页
     * @param eto
     */
    void IsShowGoodsQaContent(H5MerchGoodsQaDTO.ShowContentETO eto);

    /**
     * 删除商家回复内容
     * @param dto
     */
    void deleteGoodsQaReply(H5MerchGoodsQaDTO.IdDTO dto);
}