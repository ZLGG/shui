package com.gs.lshly.rpc.api.bbc.foundation;
import java.util.List;

import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO;

/**
 * 积分商城首页
 *
 * 
 * @author yingjun
 * @date 2021年4月12日 上午12:09:33
 */
public interface IBbcPointHomeRpc {

    List<BbcPointHomePageVO.ListVO> getHome(BbcPointHomeQTO.QTO qto);

}