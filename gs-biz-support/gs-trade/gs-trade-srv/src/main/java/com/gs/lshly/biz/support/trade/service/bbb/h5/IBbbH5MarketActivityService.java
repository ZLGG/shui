package com.gs.lshly.biz.support.trade.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;

import java.util.List;

public interface IBbbH5MarketActivityService {


    PageData<BbbH5MarketActivityVO.cutVO> cutList(BbbH5MarketActivityQTO.QTO qto);


    List<BbbH5MarketActivityVO.cutVO> viewFour(BaseDTO dto);

    PageData<BbbH5MarketActivityVO.discountVO> discountList(BbbH5MarketActivityQTO.QTO qto);

    List<BbbH5MarketActivityVO.discountVO> discountViewFour(BaseDTO dto);

    PageData<BbbH5MarketActivityVO.giftVO> giftList(BbbH5MarketActivityQTO.QTO qto);

    List<BbbH5MarketActivityVO.giftVO> giftViewFour(BaseDTO dto);

    List<BbbH5MarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto);

    PageData<BbbH5MarketActivityVO.groupbuyVO> groupbuyList(BbbH5MarketActivityQTO.QTO qto);

    BbbH5MarketActivityVO.activityVO activity(BbbH5MarketActivityQTO.IdQTO qto);

    ResponseData<BbbH5MarketActivityVO.ListActivityVO> activityList(BbbH5MarketMerchantActivityDTO.IdDTO dto);

    List<BbbH5MarketActivityVO.merchantCard> merchantCard(BbbH5MarketMerchantActivityDTO.MerchantIdDTO dto);

    BbbH5MarketActivityVO.merchantCardSuccess userReciveCard(BbbH5MarketMerchantActivityDTO.CardIdDTO dto);

    BbbH5MarketActivityVO.jurisdiction jurisdiction();

}