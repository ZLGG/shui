package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteFloorService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteFloorRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbbH5SiteFloorRpc implements IBbbH5SiteFloorRpc {
    
    @Autowired
    private IBbbH5SiteFloorService bbbH5SiteFloorService;

    @Override
    public List<BbbH5SiteFloorVO.ListVO> list(BbbH5SiteFloorQTO.QTO qto){
        return bbbH5SiteFloorService.list(qto);
    }


    @Override
    public PageData<BbbH5SiteFloorVO.GoodsListVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto) {
        return bbbH5SiteFloorService.goodsList(qto);
    }


}