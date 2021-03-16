package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsSpecInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecInfoVO;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecInfoService;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsSpecInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-09
*/
@DubboService
public class GoodsSpecInfoRpc implements IGoodsSpecInfoRpc{

    @Autowired
    private IGoodsSpecInfoService goodsSpecInfoService;

    @Override
    public PageData<PCMerchGoodsSpecInfoVO.ListVO> pageData(PCMerchGoodsSpecInfoQTO.QTO qto){
        return goodsSpecInfoService.pageData(qto);
    }

    @Override
    public void addGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto){
        goodsSpecInfoService.addGoodsSpecInfo(eto);
    }

    @Override
    public void deleteGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto){
        goodsSpecInfoService.deleteGoodsSpecInfo(dto);
    }


    @Override
    public void editGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto){
        goodsSpecInfoService.editGoodsSpecInfo(eto);
    }

    @Override
    public PCMerchGoodsSpecInfoVO.DetailVO detailGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto){
        return goodsSpecInfoService.detailGoodsSpecInfo(dto);
    }

}