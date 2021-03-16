package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentImgDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentImgQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentImgVO;

import java.util.List;

public interface ITradeCommentImgService {

    /**
     * 查询申诉图片凭证列表
     * @param qto
     * @return
     */
    List<TradeCommentImgVO.ListVO> listImageVO(TradeCommentImgQTO.QTO qto);

}