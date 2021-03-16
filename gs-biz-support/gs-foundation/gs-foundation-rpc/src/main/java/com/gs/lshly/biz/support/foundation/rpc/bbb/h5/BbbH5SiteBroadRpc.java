package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteBroadService;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5SiteBroadDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBroadQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBroadVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteBroadRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
@DubboService
public class BbbH5SiteBroadRpc implements IBbbH5SiteBroadRpc {

    @Autowired
    private IBbbH5SiteBroadService bbbH5SiteBroadService;

    @Override
    public List<BbbH5SiteBroadVO.ListVO> list(BbbH5SiteBroadQTO.QTO qto){
        return bbbH5SiteBroadService.list(qto);
    }

    @Override
    public BbbH5SiteBroadVO.DetailsVO details(BbbH5SiteBroadDTO.IdDTO dto) {
        return null;
    }

}