package com.gs.lshly.rpc.api.bbb.pc.foundation;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO;


/**
 * 
 * 公告
 *
 * 
 * @author yingjun
 * @date 2021年3月11日 下午4:03:55
 */
public interface IBbbSiteNoticeRpc {

   List<BbbSiteNoticeVO.ListVO> list(BbbSiteNoticeQTO.QTO qto);


}