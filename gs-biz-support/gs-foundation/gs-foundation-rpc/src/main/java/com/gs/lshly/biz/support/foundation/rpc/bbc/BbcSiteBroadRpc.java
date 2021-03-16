package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBroadDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBroadQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBroadVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBroadRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteBroadService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
@DubboService
public class BbcSiteBroadRpc implements IBbcSiteBroadRpc{

    @Autowired
    private IBbcSiteBroadService  bbcSiteBroadService;

    @Override
    public List<BbcSiteBroadVO.ListVO> list(BbcSiteBroadQTO.QTO qto){
        return bbcSiteBroadService.list(qto);
    }

    @Override
    public BbcSiteBroadVO.DetailsVO details(BbcSiteBroadDTO.IdDTO dto) {
        return null;
    }

}