package com.gs.lshly.biz.support.trade.rpc.bbb.h5;

import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5MarketActivityService;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketActivityService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5MarketActivityRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketActivityRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zdf
* @since 2021-01-08
*/
@DubboService
public class BbbH5MarketActivityRpc implements IBbbH5MarketActivityRpc {
    @Autowired
    private IBbbH5MarketActivityService iBbbH5MarketActivityService;


    @Override
    public PageData<BbbH5MarketActivityVO.cutVO> cutList(BbbH5MarketActivityQTO.QTO qto) {
        return iBbbH5MarketActivityService.cutList(qto);
    }

    @Override
    public List<BbbH5MarketActivityVO.cutVO> viewFour(BaseDTO dto) {
        return iBbbH5MarketActivityService.viewFour(dto);
    }

    @Override
    public PageData<BbbH5MarketActivityVO.discountVO> discountList(BbbH5MarketActivityQTO.QTO qto) {
        return iBbbH5MarketActivityService.discountList(qto);
    }

    @Override
    public List<BbbH5MarketActivityVO.discountVO> discountViewFour(BaseDTO dto) {
        return iBbbH5MarketActivityService.discountViewFour(dto);
    }

    @Override
    public PageData<BbbH5MarketActivityVO.giftVO> giftList(BbbH5MarketActivityQTO.QTO qto) {
        return iBbbH5MarketActivityService.giftList(qto);
    }

    @Override
    public List<BbbH5MarketActivityVO.giftVO> giftViewFour(BaseDTO dto) {
        return iBbbH5MarketActivityService.giftViewFour(dto);
    }

    @Override
    public PageData<BbbH5MarketActivityVO.groupbuyVO> groupbuyList(BbbH5MarketActivityQTO.QTO qto) {
        return iBbbH5MarketActivityService.groupbuyList(qto);
    }

    @Override
    public List<BbbH5MarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto) {
        return iBbbH5MarketActivityService.groupbuyViewFour(dto);
    }

    @Override
    public BbbH5MarketActivityVO.activityVO activity(BbbH5MarketActivityQTO.IdQTO qto) {
        return iBbbH5MarketActivityService.activity(qto);
    }

    @Override
    public ResponseData<BbbH5MarketActivityVO.ListActivityVO> activityList(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        return iBbbH5MarketActivityService.activityList(dto);
    }

    @Override
    public List<BbbH5MarketActivityVO.merchantCard> merchantCard(BbbH5MarketMerchantActivityDTO.MerchantIdDTO dto) {
        return iBbbH5MarketActivityService.merchantCard(dto);
    }

    @Override
    public BbbH5MarketActivityVO.merchantCardSuccess userReciveCard(BbbH5MarketMerchantActivityDTO.CardIdDTO dto) {
        return iBbbH5MarketActivityService.userReciveCard(dto);
    }

    @Override
    public BbbH5MarketActivityVO.jurisdiction jurisdiction() {
       return iBbbH5MarketActivityService.jurisdiction();

    }
}