package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5SiteBroadDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBroadQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBroadVO;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface IBbbH5SiteBroadRpc {

    List<BbbH5SiteBroadVO.ListVO> list(BbbH5SiteBroadQTO.QTO qto);

    BbbH5SiteBroadVO.DetailsVO details(BbbH5SiteBroadDTO.IdDTO dto);
}