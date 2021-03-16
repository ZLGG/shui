package com.gs.lshly.biz.support.trade.rpc.bbb.pc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketActivityService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO.FlashsaleVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketActivityRpc;

/**
*
* @author zdf
* @since 2021-01-08
*/
@DubboService
public class PCBbbMarketActivityRpc implements IPCBbbMarketActivityRpc {
    @Autowired
    private IPCBbbMarketActivityService ipcBbbMarketActivityService;


    @Override
    public PageData<PCBbbMarketActivityVO.cutVO> cutList(PCBbbMarketActivityQTO.QTO qto) {
        return ipcBbbMarketActivityService.cutList(qto);
    }

    @Override
    public List<PCBbbMarketActivityVO.cutVO> viewFour(BaseDTO dto) {
        return ipcBbbMarketActivityService.viewFour(dto);
    }

    @Override
    public PageData<PCBbbMarketActivityVO.discountVO> discountList(PCBbbMarketActivityQTO.QTO qto) {
        return ipcBbbMarketActivityService.discountList(qto);
    }

    @Override
    public List<PCBbbMarketActivityVO.discountVO> discountViewFour(BaseDTO dto) {
        return ipcBbbMarketActivityService.discountViewFour(dto);
    }

    @Override
    public PageData<PCBbbMarketActivityVO.giftVO> giftList(PCBbbMarketActivityQTO.QTO qto) {
        return ipcBbbMarketActivityService.giftList(qto);
    }

    @Override
    public List<PCBbbMarketActivityVO.giftVO> giftViewFour(BaseDTO dto) {
        return ipcBbbMarketActivityService.giftViewFour(dto);
    }

    @Override
    public PageData<PCBbbMarketActivityVO.groupbuyVO> groupbuyList(PCBbbMarketActivityQTO.QTO qto) {
        return ipcBbbMarketActivityService.groupbuyList(qto);
    }

    @Override
    public List<PCBbbMarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto) {
        return ipcBbbMarketActivityService.groupbuyViewFour(dto);
    }

    @Override
    public PCBbbMarketActivityVO.activityVO activity(PCBbbMarketActivityQTO.IdQTO qto) {
        return ipcBbbMarketActivityService.activity(qto);
    }

    @Override
    public ResponseData<PCBbbMarketActivityVO.ListActivityVO> activityList(BbbMarketMerchantActivityDTO.IdDTO dto) {
        return ipcBbbMarketActivityService.activityList(dto);
    }

    @Override
    public List<PCBbbMarketActivityVO.merchantCard> merchantCard(BbbMarketMerchantActivityDTO.MerchantIdDTO dto) {
        return ipcBbbMarketActivityService.merchantCard(dto);
    }

    @Override
    public PCBbbMarketActivityVO.merchantCardSuccess userReciveCard(BbbMarketMerchantActivityDTO.CardIdDTO dto) {
        return ipcBbbMarketActivityService.userReciveCard(dto);
    }

    @Override
    public PCBbbMarketActivityVO.jurisdiction jurisdiction() {
        return ipcBbbMarketActivityService.jurisdiction();
    }

	@Override
	public FlashsaleVO listFlashsale(BaseDTO dto) {
		return ipcBbbMarketActivityService.listFlashsale(dto);
	}
}