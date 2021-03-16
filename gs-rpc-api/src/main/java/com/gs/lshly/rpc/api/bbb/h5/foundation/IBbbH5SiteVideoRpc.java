package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteVideoQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteVideoVO;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
public interface IBbbH5SiteVideoRpc {

    List<BbbH5SiteVideoVO.ListVO> video(BbbH5SiteVideoQTO.QTO qto);
}