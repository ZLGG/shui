package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsExtendParamsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsExtendParamsService;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsExtendParamsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-09
*/
@DubboService
public class GoodsExtendParamsRpc implements IGoodsExtendParamsRpc{

    @Autowired
    private IGoodsExtendParamsService goodsExtendParamsService;

    @Override
    public PageData<PCMerchGoodsExtendParamsVO.ListVO> pageData(PCMerchGoodsExtendParamsQTO.QTO qto){
        return goodsExtendParamsService.pageData(qto);
    }

    @Override
    public void addGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto){
        goodsExtendParamsService.addGoodsExtendParams(eto);
    }

    @Override
    public void deleteGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto){
        goodsExtendParamsService.deleteGoodsExtendParams(dto);
    }


    @Override
    public void editGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto){
        goodsExtendParamsService.editGoodsExtendParams(eto);
    }

    @Override
    public PCMerchGoodsExtendParamsVO.DetailVO detailGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto){
        return goodsExtendParamsService.detailGoodsExtendParams(dto);
    }

}