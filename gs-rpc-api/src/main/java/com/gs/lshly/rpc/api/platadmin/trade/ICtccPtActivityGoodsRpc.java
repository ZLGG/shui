package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;

import java.util.List;

/**
 * @Author hanly
 * @create 2021/6/2 9:35
 */

public interface ICtccPtActivityGoodsRpc {

    List<String> getList();
}
