package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuGoodsVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorMenuGoodsService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorMenuGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
@DubboService
public class SiteFloorMenuGoodsRpc implements ISiteFloorMenuGoodsRpc{

    @Autowired
    private ISiteFloorMenuGoodsService siteFloorMenuGoodsService;

    @Override
    public PageData<SiteFloorMenuGoodsVO.ListVO> pageData(SiteFloorMenuGoodsQTO.QTO qto){
        return siteFloorMenuGoodsService.pageData(qto);
    }

    @Override
    public void addSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto){
        siteFloorMenuGoodsService.addSiteFloorMenuGoods(eto);
    }

    @Override
    public void deleteSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.IdDTO dto){
        siteFloorMenuGoodsService.deleteSiteFloorMenuGoods(dto);
    }

    @Override
    public void editSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto){
        siteFloorMenuGoodsService.editSiteFloorMenuGoods(eto);
    }



    @Override
    public List<SiteFloorMenuGoodsVO.GoodsIdVO> selectByFloorMenuId(SiteFloorMenuGoodsQTO.FloorMenuIdQTO qto) {
        return siteFloorMenuGoodsService.selectByFloorMenuId(qto);
    }

}