package com.gs.lshly.rpc.api.bbb.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketMerchantActivityDTO;

import java.util.List;

/**
*
* @author zdf
* @since 2021-01-08
*/
public interface IPCBbbMarketActivityRpc {


    PageData<PCBbbMarketActivityVO.cutVO> cutList(PCBbbMarketActivityQTO.QTO qto);

    List<PCBbbMarketActivityVO.cutVO> viewFour(BaseDTO dto);

    PageData<PCBbbMarketActivityVO.discountVO> discountList(PCBbbMarketActivityQTO.QTO qto);

    List<PCBbbMarketActivityVO.discountVO> discountViewFour(BaseDTO dto);

    PageData<PCBbbMarketActivityVO.giftVO> giftList(PCBbbMarketActivityQTO.QTO qto);

    List<PCBbbMarketActivityVO.giftVO> giftViewFour(BaseDTO dto);

    PageData<PCBbbMarketActivityVO.groupbuyVO> groupbuyList(PCBbbMarketActivityQTO.QTO qto);

    List<PCBbbMarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto);

    PCBbbMarketActivityVO.activityVO activity(PCBbbMarketActivityQTO.IdQTO qto);

    ResponseData<PCBbbMarketActivityVO.ListActivityVO> activityList(BbbMarketMerchantActivityDTO.IdDTO dto);

    List<PCBbbMarketActivityVO.merchantCard> merchantCard(BbbMarketMerchantActivityDTO.MerchantIdDTO dto);

    PCBbbMarketActivityVO.merchantCardSuccess userReciveCard(BbbMarketMerchantActivityDTO.CardIdDTO dto);

    PCBbbMarketActivityVO.jurisdiction jurisdiction();


    PageData<PCBbbMarketActivityVO.activityListPageVO> activityListPage(PCBbbMarketActivityQTO.QTO qto);

    List<PCBbbMarketActivityVO.merchantCard> activityCardGoodsInfo(BbcMarketMerchantActivityDTO.MerchantIdDTO dto);
}