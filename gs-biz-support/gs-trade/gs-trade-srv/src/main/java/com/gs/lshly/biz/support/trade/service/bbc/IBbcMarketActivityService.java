package com.gs.lshly.biz.support.trade.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

import java.util.List;


public interface IBbcMarketActivityService {


    ResponseData<BbcMarketActivityVO.ListVO> activityList(BbcMarketActivityDTO.DTO dto);

    ResponseData<List<BbcMarketActivityVO.ListCardVO>> activityCardList(BbcMarketActivityDTO.DTO dto);

    PageData<BbcMarketActivityVO.cutVO> cutList(BbcMarketActivityQTO.QTO qto);

    List<BbcMarketActivityVO.cutVO> viewFour(BaseDTO dto);

    PageData<BbcMarketActivityVO.discountVO> discountList(BbcMarketActivityQTO.QTO qto);

    List<BbcMarketActivityVO.discountVO> discountViewFour(BaseDTO dto);

    PageData<BbcMarketActivityVO.giftVO> giftList(BbcMarketActivityQTO.QTO qto);

    List<BbcMarketActivityVO.giftVO> giftViewFour(BaseDTO dto);

    PageData<BbcMarketActivityVO.groupbuyVO> groupbuyList(BbcMarketActivityQTO.QTO qto);

    List<BbcMarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto);

    List<BbcMarketActivityVO.merchantCard> merchantCard(BbcMarketMerchantActivityDTO.MerchantIdDTO dto);

    BbcMarketActivityVO.activityVO activity(BbcMarketActivityQTO.IdQTO qto);

    BbcMarketActivityVO.merchantCardSuccess userReciveCard(BbcMarketMerchantActivityDTO.CardIdDTO dto);

    BbcMarketActivityVO.jurisdiction jurisdiction();

    /**
     * 秒杀列表
     * @param dto
     * @return
     */
    BbcSiteTopicVO.TopicVO listFlashsale(BaseDTO dto);
    
    /**
     * 分页查询秒杀 产品
     * @param qto
     * @return
     */
    PageData<GoodsInfoVO.DetailVO> pageFlashsale(BbcMarketActivityQTO.QTO qto);
}