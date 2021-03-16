package com.gs.lshly.biz.support.commodity.rpc.merchadmin.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.dto.H5MerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.qto.H5MerchGoodsQaQTO;
import com.gs.lshly.common.struct.merchadmin.h5.commodity.vo.H5MerchGoodsQaVO;
import com.gs.lshly.rpc.api.merchadmin.h5.commodity.IH5MerchGoodsQaRpc;
import com.gs.lshly.biz.support.commodity.service.merchadmin.h5.IH5MerchGoodsQaService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-01-22
*/
@DubboService
public class H5MerchGoodsQaRpc implements IH5MerchGoodsQaRpc{
    @Autowired
    private IH5MerchGoodsQaService  h5MerchGoodsQaService;

    @Override
    public PageData<H5MerchGoodsQaVO.ReplyListVO> pageMerchantH5GoodsQa(H5MerchGoodsQaQTO.GoodsQaQTO qto){
        return h5MerchGoodsQaService.pageMerchantH5GoodsQa(qto);
    }

    @Override
    public void replyGoodsQa(H5MerchGoodsQaDTO.MerchantReplyETO eto) {
        h5MerchGoodsQaService.replyGoodsQa(eto);
    }

    @Override
    public void IsShowGoodsQaContent(H5MerchGoodsQaDTO.ShowContentETO eto) {
        h5MerchGoodsQaService.IsShowGoodsQaContent(eto);
    }

    @Override
    public void deleteGoodsQaReply(H5MerchGoodsQaDTO.IdDTO dto) {
        h5MerchGoodsQaService.deleteGoodsQaReply(dto);
    }
}