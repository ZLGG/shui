package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamsDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamsVO;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsParamsService;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsParamsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-09-29
*/
@DubboService
public class GoodsParamsRpc implements IGoodsParamsRpc{

    @Autowired
    private IGoodsParamsService goodsParamsService;


    @Override
    public List<GoodsParamsVO.ParamsListVO> listGoodsParams(GoodsCategoryDTO.IdDTO dto) {
        return goodsParamsService.listGoodsParams(dto);
    }

    @Override
    public void addGoodsParams(GoodsCategoryDTO.IdDTO dto,List<GoodsParamsDTO.ETO> eto){

        goodsParamsService.addGoodsParams(dto, eto);
    }

    @Override
    public void deleteGoodsParams(GoodsParamsDTO.IdDTO dto){
        goodsParamsService.deleteGoodsParams(dto);
    }

    @Override
    public void editGoodsParams(GoodsParamsDTO.ETO eto){
        goodsParamsService.editGoodsParams(eto);
    }

    @Override
    public GoodsParamsVO.DetailVO detailGoodsParams(GoodsParamsDTO.IdDTO dto){
        return goodsParamsService.detailGoodsParams(dto);
    }

}