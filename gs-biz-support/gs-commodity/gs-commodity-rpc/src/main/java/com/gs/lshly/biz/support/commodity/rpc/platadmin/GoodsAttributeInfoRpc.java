package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsAttributeInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeInfoVO;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeInfoService;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsAttributeInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-09
*/
@DubboService
public class GoodsAttributeInfoRpc implements IGoodsAttributeInfoRpc{

    @Autowired
    private IGoodsAttributeInfoService goodsAttributeInfoService;

    @Override
    public PageData<PCMerchGoodsAttributeInfoVO.ListVO> pageData(PCMerchGoodsAttributeInfoQTO.QTO qto){
        return goodsAttributeInfoService.pageData(qto);
    }

    @Override
    public void addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO eto){
         goodsAttributeInfoService.addGoodsAttributeInfo(eto);
    }

    @Override
    public void deleteGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto){
        goodsAttributeInfoService.deleteGoodsAttributeInfo(dto);
    }


    @Override
    public void editGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO eto){
        goodsAttributeInfoService.editGoodsAttributeInfo(eto);
    }

    @Override
    public PCMerchGoodsAttributeInfoVO.DetailVO detailGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto){
        return goodsAttributeInfoService.detailGoodsAttributeInfo(dto);
    }

}