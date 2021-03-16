package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBannerDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBannerRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteBannerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbcSiteBannerRpc implements IBbcSiteBannerRpc{

    @Autowired
    private IBbcSiteBannerService  bbcSiteBannerService;

    @Override
    public List<BbcSiteBannerVO.ListVO> list(BbcSiteBannerQTO.QTO qto){
        return bbcSiteBannerService.list(qto);
    }



}