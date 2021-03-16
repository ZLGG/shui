package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryAttributeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryAttributeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryAttributeVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryAttributeRpc;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsCategoryAttributeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-27
*/
@DubboService
public class GoodsCategoryAttributeRpc implements IGoodsCategoryAttributeRpc{
    @Autowired
    private IGoodsCategoryAttributeService  GoodsCategoryAttributeService;

    @Override
    public PageData<GoodsCategoryAttributeVO.ListVO> pageData(GoodsCategoryAttributeQTO.QTO qto){
        return GoodsCategoryAttributeService.pageData(qto);
    }

    @Override
    public void addGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto){
        GoodsCategoryAttributeService.addGoodsCategoryAttribute(eto);
    }

    @Override
    public void deleteGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto){
        GoodsCategoryAttributeService.deleteGoodsCategoryAttribute(dto);
    }


    @Override
    public void editGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto){
        GoodsCategoryAttributeService.editGoodsCategoryAttribute(eto);
    }

    @Override
    public GoodsCategoryAttributeVO.DetailVO detailGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto){
        return  GoodsCategoryAttributeService.detailGoodsCategoryAttribute(dto);
    }

}