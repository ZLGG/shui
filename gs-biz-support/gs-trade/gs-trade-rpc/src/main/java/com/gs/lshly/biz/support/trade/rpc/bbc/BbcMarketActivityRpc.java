package com.gs.lshly.biz.support.trade.rpc.bbc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketActivityService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.TopicVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO.QTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.Seckill;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillHome;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketActivityRpc;

/**
*
* @author oy
* @since 2020-11-20
*/
@DubboService
public class BbcMarketActivityRpc implements IBbcMarketActivityRpc {
    @Autowired
    private IBbcMarketActivityService iBbcMarketActivityService;

    @Override
    public ResponseData<BbcMarketActivityVO.ListVO> activityList(BbcMarketActivityDTO.DTO dto) {
        return iBbcMarketActivityService.activityList(dto);
    }

    @Override
    public  ResponseData<List<BbcMarketActivityVO.ListCardVO>> activityCardList(BbcMarketActivityDTO.DTO dto) {
        return iBbcMarketActivityService.activityCardList(dto);
    }

    @Override
    public PageData<BbcMarketActivityVO.cutVO> cutList(BbcMarketActivityQTO.QTO qto) {
        return iBbcMarketActivityService.cutList(qto);
    }

    @Override
    public List<BbcMarketActivityVO.cutVO> viewFour(BaseDTO dto) {
        return iBbcMarketActivityService.viewFour(dto);
    }

    @Override
    public PageData<BbcMarketActivityVO.discountVO> discountList(BbcMarketActivityQTO.QTO qto) {
        return iBbcMarketActivityService.discountList(qto);
    }

    @Override
    public List<BbcMarketActivityVO.discountVO> discountViewFour(BaseDTO dto) {
        return iBbcMarketActivityService.discountViewFour(dto);
    }

    @Override
    public PageData<BbcMarketActivityVO.giftVO> giftList(BbcMarketActivityQTO.QTO qto) {
        return iBbcMarketActivityService.giftList(qto);
    }

    @Override
    public List<BbcMarketActivityVO.giftVO> giftViewFour(BaseDTO dto) {
        return iBbcMarketActivityService.giftViewFour(dto);
    }

    @Override
    public PageData<BbcMarketActivityVO.groupbuyVO> groupbuyList(BbcMarketActivityQTO.QTO qto) {
        return iBbcMarketActivityService.groupbuyList(qto);
    }

    @Override
    public List<BbcMarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto) {
        return iBbcMarketActivityService.groupbuyViewFour(dto);
    }

    @Override
    public BbcMarketActivityVO.activityVO activity(BbcMarketActivityQTO.IdQTO qto) {
        return iBbcMarketActivityService.activity(qto);
    }

    @Override
    public List<BbcMarketActivityVO.merchantCard> merchantCard(BbcMarketMerchantActivityDTO.MerchantIdDTO dto) {
        return iBbcMarketActivityService.merchantCard(dto);
    }

    @Override
    public BbcMarketActivityVO.merchantCardSuccess userReciveCard(BbcMarketMerchantActivityDTO.CardIdDTO dto) {
        return iBbcMarketActivityService.userReciveCard(dto);
    }

    @Override
    public BbcMarketActivityVO.jurisdiction jurisdiction() {
        return iBbcMarketActivityService.jurisdiction();
    }

    @Override
    public List<BbcMarketActivityVO.merchantCard> activityCardGoodsInfo(BbcMarketMerchantActivityDTO.MerchantIdDTO dto) {
        return iBbcMarketActivityService.activityCardGoodsInfo(dto);
    }

    @Override
    public PageData<BbcMarketActivityVO.activityListPageVO> activityListPage(BbcMarketActivityQTO.QTO qto) {
        return iBbcMarketActivityService.activityListPage(qto);
    }
	@Override
	public TopicVO listFlashsale(BaseDTO dto) {
		return iBbcMarketActivityService.listFlashsale(dto);
	}

	@Override
	public PageData<BbcGoodsInfoVO.DetailVO> pageFlashsale(QTO qto) {
		return iBbcMarketActivityService.pageFlashsale(qto);
	}

	@Override
	public Seckill listSeckill(BaseQTO qto) {
		return iBbcMarketActivityService.listSeckill(qto);
	}

	@Override
	public SeckillHome seckillHome(BbcMarketActivityQTO.SeckillHomeQTO qto) {
		return iBbcMarketActivityService.seckillHome(qto);
	}
}