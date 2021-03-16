package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsQaService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsQaVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsQaRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Starry
 * @Date 10:04 2020/10/17
 */
@DubboService
public class PCMerchAdminGoodsQaRpc implements IPCMerchAdminGoodsQaRpc {
    @Autowired
    private IPCMerchGoodsQaService merchGoodsQaService;

    @Override
    public PageData<PCMerchGoodsQaVO.ReplyListVO> pageMerchantGoodsQa(PCMerchGoodsQaQTO.GoodsQaQTO qto) {
        return merchGoodsQaService.pageMerchantGoodsQa(qto);
    }

    @Override
    public void replyGoodsQa(PCMerchGoodsQaDTO.MerchantReplyETO eto) {
        merchGoodsQaService.replyGoodsQa(eto);
    }

    @Override
    public void IsShowGoodsQaContent(PCMerchGoodsQaDTO.ShowContentETO eto) {
        merchGoodsQaService.IsShowGoodsQaContent(eto);
    }

    @Override
    public void deleteGoodsQaReply(PCMerchGoodsQaDTO.IdDTO dto) {
        merchGoodsQaService.deleteGoodsQaReply(dto);
    }
}
