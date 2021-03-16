package com.gs.lshly.rpc.api.bbb.pc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteAdvertVO;


/**
*
* @author hyy
* @since 2020-11-03
*/
public interface IBbbSiteAdvertRpc {

   PageData<BbbSiteAdvertVO.SubjectListVO> pageList(BbbSiteAdvertQTO.QTO qto);


}