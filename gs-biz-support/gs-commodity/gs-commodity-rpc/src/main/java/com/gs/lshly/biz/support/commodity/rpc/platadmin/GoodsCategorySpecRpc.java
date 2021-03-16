package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategorySpecDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategorySpecQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategorySpecVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategorySpecRpc;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsCategorySpecService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-27
*/
@DubboService
public class GoodsCategorySpecRpc implements IGoodsCategorySpecRpc{
    @Autowired
    private IGoodsCategorySpecService  GoodsCategorySpecService;

    @Override
    public PageData<GoodsCategorySpecVO.ListVO> pageData(GoodsCategorySpecQTO.QTO qto){
        return GoodsCategorySpecService.pageData(qto);
    }

    @Override
    public void addGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto){
        GoodsCategorySpecService.addGoodsCategorySpec(eto);
    }

    @Override
    public void deleteGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto){
        GoodsCategorySpecService.deleteGoodsCategorySpec(dto);
    }


    @Override
    public void editGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto){
        GoodsCategorySpecService.editGoodsCategorySpec(eto);
    }

    @Override
    public GoodsCategorySpecVO.DetailVO detailGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto){
        return  GoodsCategorySpecService.detailGoodsCategorySpec(dto);
    }

}