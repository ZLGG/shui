package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBannerQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface IBbbH5SiteBannerRpc {

    List<BbbH5SiteBannerVO.ListVO> list(BbbH5SiteBannerQTO.QTO qto);

}