package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorGoodsVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorGoodsService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
@DubboService
public class SiteFloorGoodsRpc implements ISiteFloorGoodsRpc{

    @Autowired
    private ISiteFloorGoodsService siteFloorGoodsService;

    @Override
    public PageData<SiteFloorGoodsVO.ListVO> pageData(SiteFloorGoodsQTO.QTO qto){
        return siteFloorGoodsService.pageData(qto);
    }

    @Override
    public void addSiteFloorGoods(SiteFloorGoodsDTO.ADTO eto){
        siteFloorGoodsService.addSiteFloorGoods(eto);
    }

    @Override
    public void deleteSiteFloorGoods(SiteFloorGoodsDTO.IdDTO dto){
        siteFloorGoodsService.deleteSiteFloorGoods(dto);
    }

}